package com.brandonlenz.iso8583.messages;

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

    private final MessageDefinition definition;
    private DataField messageTypeIndicator;
    private Bitmap primaryBitmap;
    private List<DataField> dataFields;

    public Iso8583Message(Iso8583MessageDefinition definition) {
        this.definition = definition;
        this.primaryBitmap = definition.getPrimaryBitmapDefinition().getDataFieldBuilder()
                .setRawData(new byte[definition.getPrimaryBitmapDefinition().getLength()])
                .build();
        this.dataFields = createDataFieldsFromDefinition(definition);
    }

    public Iso8583Message(Iso8583MessageDefinition definition, DataField messageTypeIndicator) {
        this(definition);
        this.messageTypeIndicator = messageTypeIndicator;
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
        return dataFields;
    }

    public DataField getMessageTypeIndicator() {
        return messageTypeIndicator;
    }

    public void setMessageTypeIndicator(DataField messageTypeIndicator) {
        this.messageTypeIndicator = messageTypeIndicator;
    }

    public DataField getPrimaryBitmapField() {
        return primaryBitmap;
    }

    public Bitmap getPrimaryBitmap() {
        return primaryBitmap;
    }

    public void setPrimaryBitmap(Bitmap primaryBitmap) {
        this.primaryBitmap = primaryBitmap;
    }

    public Bitmap getSecondaryBitmap() {
        return (Bitmap) getDataField(FieldName.SECONDARY_BITMAP);
    }

    public Bitmap getTertiaryBitmap() {
        return (Bitmap) getDataField(FieldName.TERTIARY_BITMAP);
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
                .filter(f -> f.getName().equals(fieldName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Message does not contain DataField with name: " + fieldName.getName() + "."));
    }

    public void setDataField(int dataFieldNumber, DataField dataField) { //TODO: Refactor.
        if (dataFieldNumber < 1 || dataFieldNumber > definition.getFieldDefinitions().size()) {
            throw new IllegalArgumentException("Message does not contain FieldDefinition for DataField number " + dataFieldNumber);
        }

        Bitmap bitmap = getCorrespondingBitmap(dataFieldNumber);
        //Note: If bitmap is the primary bitmap and somehow its data is null, we have a problem.

        //recursively set bitmaps as necessary
        if (dataFields.indexOf(bitmap) != -1) {
            int bitmapFieldNumber = dataFields.indexOf(bitmap) + 1;
            if (!dataFieldBitIsSet(bitmapFieldNumber)) {
                bitmap = bitmap.getDefinition().getDataFieldBuilder()
                        .setRawData(new byte[bitmap.getDefinition().getByteLength()])
                        .build();
                setDataField(bitmapFieldNumber, bitmap);
            }
        }

        dataFields.set(dataFieldNumber - 1, dataField);
        bitmap.setBit(dataFieldNumber);
    }

    public void removeDataField(int dataFieldNumber) {
        if (dataFieldNumber < 1 || dataFieldNumber > definition.getFieldDefinitions().size()) {
            throw new IllegalArgumentException("Message does not contain FieldDefinition for DataField number " + dataFieldNumber);
        }

        Bitmap bitmap = getCorrespondingBitmap(dataFieldNumber);
        DataField emptyDataField = dataFields.get(dataFieldNumber - 1).getDefinition().getDataFieldBuilder().build(); //initialize an empty field
        dataFields.set(dataFieldNumber - 1, emptyDataField);
        bitmap.unsetBit(dataFieldNumber);
        if (dataFields.indexOf(bitmap) != -1 && bitmap.getSetBits().isEmpty()) {
            removeDataField(dataFields.indexOf(bitmap) + 1);
        }
    }

    private Bitmap getCorrespondingBitmap(int dataFieldNumber) {
        if (dataFieldNumber < getSecondaryBitmap().getStartFieldIndex()) {
            return getPrimaryBitmap();
        } else if (dataFieldNumber < getTertiaryBitmap().getStartFieldIndex()) {
            return getSecondaryBitmap();
        } else if (dataFieldNumber <= getTertiaryBitmap().getEndFieldIndex()) {
            return getTertiaryBitmap();
        } else {
            throw new IllegalArgumentException("ISO8583Messages do not support field number: " + dataFieldNumber);
        }
    }

    private static List<DataField> createDataFieldsFromDefinition(Iso8583MessageDefinition definition) {
        List<DataField> dataFields = new ArrayList<>();

        for (FieldDefinition fieldDefinition : definition.getFieldDefinitions()) {
            dataFields.add(fieldDefinition.getDataFieldBuilder().build());
        }

        return dataFields;
    }

    @Override
    public String toString() {
        return getData();
    }
}