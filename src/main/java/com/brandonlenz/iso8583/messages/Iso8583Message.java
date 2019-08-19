package com.brandonlenz.iso8583.messages;

import com.brandonlenz.generic.messages.Message;
import com.brandonlenz.iso8583.definitions.messages.Iso8583MessageDefinition;
import com.brandonlenz.iso8583.fields.Bitmap;
import com.brandonlenz.iso8583.fields.DataField;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.SortedMap;
import java.util.TreeMap;

public class Iso8583Message implements Message {

    private final Iso8583MessageDefinition definition;
    private DataField messageTypeIndicator;
    private Bitmap primaryBitmap;
    private final SortedMap<Integer, DataField> dataFields;

    Iso8583Message(Iso8583MessageDefinition definition) {
        this.definition = definition;
        this.dataFields = new TreeMap<>();
    }

    void setMessageTypeIndicator(DataField messageTypeIndicator) {
        this.messageTypeIndicator = messageTypeIndicator;
    }

    void setPrimaryBitmap(Bitmap primaryBitmap) {
        this.primaryBitmap = primaryBitmap;
    }

    void setField(int fieldNumber, DataField dataField) {
        dataFields.put(fieldNumber, dataField);
    }

    void removeField(int fieldNumber) {
        dataFields.remove(fieldNumber);
    }

    @Override
    public Iso8583MessageDefinition getDefinition() {
        return definition;
    }

    @Override
    public byte[] getRawData() {
        try (ByteArrayOutputStream bytes = new ByteArrayOutputStream()) {
            bytes.write(messageTypeIndicator.getRawData());
            bytes.write(primaryBitmap.getRawData());
            for (DataField dataField : dataFields.values()) {
                bytes.write(dataField.getRawData());
            }
            return (bytes).toByteArray();
        } catch (IOException e) {
            System.out.println("An error occurred while trying to get the raw message data: ");
            e.printStackTrace();
            return new byte[0];
        }
    }

    @Override
    public String getData() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(messageTypeIndicator.getData());
        stringBuilder.append(primaryBitmap.getData());
        for (DataField dataField : dataFields.values()) {
            stringBuilder.append(dataField.getData());
        }
        return stringBuilder.toString();
    }

    public DataField getMessageTypeIndicator() {
        return messageTypeIndicator;
    }

    public Bitmap getPrimaryBitmap() {
        return primaryBitmap;
    }

    public boolean dataFieldIsSet(int fieldNumber) {
        return dataFields.containsKey(fieldNumber);
    }

    public boolean dataFieldBitIsSet(int fieldNumber) {
        Optional<Bitmap> bitmap = getCorrespondingBitmap(fieldNumber);
        return bitmap.isPresent() && bitmap.get().bitIsSet(fieldNumber);
    }

    private Optional<Bitmap> getCorrespondingBitmap(int fieldNumber) {
        if (definition.primaryBitmapGovernsBit(fieldNumber)) {
            return Optional.of(primaryBitmap);
        } else if (definition.secondaryBitmapGovernsBit(fieldNumber)) {
            return getSecondaryBitmap();
        } else if (definition.tertiaryBitmapGovernsBit(fieldNumber)) {
            return getTertiaryBitmap();
        } else {
            throw new IllegalArgumentException(
                    "DataField number " + fieldNumber + " is not governed by any defined bitmap");
        }
    }

    Optional<Bitmap> getSecondaryBitmap() {
        return getBitmapField(definition.getSecondaryBitmapFieldNumber());
    }

    Optional<Bitmap> getTertiaryBitmap() {
        return getBitmapField(definition.getTertiaryBitmapFieldNumber());
    }

    private Optional<Bitmap> getBitmapField(int bitmapFieldNumber) {
        if (dataFields.containsKey(bitmapFieldNumber)) {
            return Optional.of((Bitmap) getDataField(bitmapFieldNumber));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public DataField getDataField(int fieldNumber) {
        if (dataFields.containsKey(fieldNumber)) {
            return dataFields.get(fieldNumber);
        } else {
            throw new IllegalArgumentException("DataField " + fieldNumber + " is not set");
        }
    }

    @Override
    public Map<Integer, DataField> getDataFields() {
        return dataFields;
    }

    @Override
    public String toString() {
        return getData();
    }

    public static Iso8583MessageBuilder builder(Iso8583MessageDefinition definition, DataField messageTypeIndicator) {
        return new Iso8583MessageBuilder(definition, messageTypeIndicator);
    }

    public static Iso8583MessageParser parser(Iso8583MessageDefinition definition) {
        return new Iso8583MessageParser(definition);
    }
}