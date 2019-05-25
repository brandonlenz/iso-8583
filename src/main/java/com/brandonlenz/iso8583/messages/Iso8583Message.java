package com.brandonlenz.iso8583.messages;

import com.brandonlenz.iso8583.building.DataFieldBuilder;
import com.brandonlenz.iso8583.definitions.fields.FieldDefinition;
import com.brandonlenz.iso8583.definitions.messages.Iso8583MessageDefinition;
import com.brandonlenz.iso8583.definitions.messages.MessageDefinition;
import com.brandonlenz.iso8583.definitions.names.FieldName;
import com.brandonlenz.iso8583.fields.Bitmap;
import com.brandonlenz.iso8583.fields.DataField;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Iso8583Message implements Message {

    private static final int PRIMARY_BITMAP_START_FIELD_INDEX = 1;
    private static final int SECONDARY_BITMAP_START_FIELD_INDEX = 65;
    private static final int TERTIARY_BITMAP_START_FIELD_INDEX = 128;

    private final MessageDefinition definition;
    private DataField primaryBitmap;
    private DataField messageTypeIndicator;
    private List<DataField> dataFields;

    public Iso8583Message(Iso8583MessageDefinition definition) {
        this.definition = definition;
        this.primaryBitmap = new DataFieldBuilder(definition.getPrimaryBitmapDefinition(),
                new byte[definition.getPrimaryBitmapDefinition().getLength()]).getDataField();
        this.dataFields = createDataFieldsFromDefinition(definition);
    }

    public Iso8583Message(Iso8583MessageDefinition definition, byte[] messageTypeIndicatorRawData) {
        this(definition);
        this.messageTypeIndicator = new DataFieldBuilder(definition.getMessageTypeIndicatorDefinition(),
                messageTypeIndicatorRawData).getDataField();
    }

    public Iso8583Message(Iso8583MessageDefinition definition, String messageTypeIndicatorData) {
        this(definition, definition.getMessageTypeIndicatorDefinition().getEncoding().encode(messageTypeIndicatorData));
    }

    @Override
    public byte[] getRawData() {
        try (ByteArrayOutputStream bytes = new ByteArrayOutputStream()) {
            if (messageTypeIndicator != null && messageTypeIndicator.getRawData() != null) {
                bytes.write(messageTypeIndicator.getRawData());
            }
            if (primaryBitmap != null && primaryBitmap.getRawData() != null) {
                bytes.write(primaryBitmap.getRawData());
            }
            for (DataField dataField : dataFields) {
                if (dataField.getRawData() != null) {
                    bytes.write(dataField.getRawData());
                }
            }
            return (bytes).toByteArray();
        } catch (IOException e) {
            System.out.println("An error occurred while trying to get the raw message data: ");
            e.printStackTrace();
            return new byte[0];
        }
    }

    public String getData() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(messageTypeIndicator.getData());
        stringBuilder.append(primaryBitmap.getData());
        for (DataField dataField : dataFields) {
            stringBuilder.append(dataField.getData());
        }
        return stringBuilder.toString();
    }

    @Override
    public MessageDefinition getDefinition() {
        return definition;
    }

    @Override
    public List<DataField> getDataFields() {
        List<DataField> allDataFields = new ArrayList<>();
        allDataFields.add(messageTypeIndicator);
        allDataFields.add(primaryBitmap);
        allDataFields.addAll(dataFields);
        return allDataFields;
    }

    public DataField getMessageTypeIndicator() {
        return messageTypeIndicator;
    }

    public void setMessageTypeIndicator(DataField messageTypeIndicator) {
        this.messageTypeIndicator = messageTypeIndicator;
    }

    public void setPrimaryBitmap(DataField primaryBitmap) {
        this.primaryBitmap = primaryBitmap;
    }

    public DataField getPrimaryBitmapField() {
        return primaryBitmap;
    }

    public Bitmap getPrimaryBitmap() {
        return primaryBitmap.asBitmap(PRIMARY_BITMAP_START_FIELD_INDEX);
    }

    public DataField getSecondaryBitmapField() {
        return getDataField(FieldName.SECONDARY_BITMAP);
    }

    public Bitmap getSecondaryBitmap() {
        return getSecondaryBitmapField().asBitmap(SECONDARY_BITMAP_START_FIELD_INDEX);
    }

    public DataField getTertiaryBitmapField() {
        return getDataField(FieldName.TERTIARY_BITMAP);
    }

    public Bitmap getTertiaryBitmap() {
        return getTertiaryBitmapField().asBitmap(TERTIARY_BITMAP_START_FIELD_INDEX);
    }

    public boolean dataFieldBitIsSet(int dataFieldNumber) {
        return getCorrespondingBitmap(dataFieldNumber).bitIsSet(dataFieldNumber);
    }

    @Override
    public DataField getDataField(int dataFieldNumber) {
        return dataFields.get(dataFieldNumber - 1);
    }


    public DataField getDataField(FieldName fieldName) {
        return dataFields.stream()
                .filter(f -> f.getFieldName().equals(fieldName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Message does not contain DataField with name: " + fieldName.getName() + "."));
    }

    public void setDataField(int dataFieldNumber, DataField dataField) {
        //TODO: If the corresponding bitmap bit is not set (ie bit 1 of field 1 for secondary bitmap), set it and initialize empty bitmap
        Bitmap bitmap = getCorrespondingBitmap(dataFieldNumber);
        dataFields.set(dataFieldNumber - 1, dataField);
        bitmap.setBit(dataFieldNumber);
    }

    public void removeDataField(int dataFieldNumber) {
        Bitmap bitmap = getCorrespondingBitmap(dataFieldNumber);
        dataFields.get(dataFieldNumber - 1).setRawData(null); //TODO: Explore re-working datafields as a whole, this is gross
        bitmap.unsetBit(dataFieldNumber);
    }

    private Bitmap getCorrespondingBitmap(int dataFieldNumber) {
        if (dataFieldNumber < SECONDARY_BITMAP_START_FIELD_INDEX) {
            return getPrimaryBitmap();
        } else if (dataFieldNumber < TERTIARY_BITMAP_START_FIELD_INDEX) {
            return getSecondaryBitmap();
        } else if (dataFieldNumber <= getTertiaryBitmap().getEndFieldIndex()) {
            return getTertiaryBitmap();
        } else {
            throw new IllegalArgumentException("ISO8583Messages do not support field number: " + dataFieldNumber);
        }
    }

    private List<DataField> createDataFieldsFromDefinition(Iso8583MessageDefinition definition) {
        dataFields = new ArrayList<>();

        for (FieldDefinition fieldDefinition : definition.getFieldDefinitions()) {
            dataFields.add(new DataField(fieldDefinition));
        }

        return dataFields;
    }

    @Override
    public String toString() {
        return getData();
    }
}