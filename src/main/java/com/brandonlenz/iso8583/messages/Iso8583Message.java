package com.brandonlenz.iso8583.messages;

import com.brandonlenz.iso8583.definitions.fields.BitmapDefinition;
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
    private final SortedMap<Integer, DataField> dataFields;
    private DataField messageTypeIndicator;
    private Bitmap primaryBitmap;

    private Iso8583Message(Builder builder) {
        this.definition = builder.definition;
        this.messageTypeIndicator = builder.messageTypeIndicator;
        this.primaryBitmap = builder.primaryBitmap;
        this.dataFields = builder.dataFields;
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

    private Optional<Bitmap> getSecondaryBitmap() {
        return getBitmapField(definition.getSecondaryBitmapFieldNumber());
    }

    private Optional<Bitmap> getTertiaryBitmap() {
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

    public static class Builder {

        private final Iso8583MessageDefinition definition;
        private final DataField messageTypeIndicator;
        private final Bitmap primaryBitmap;
        private final SortedMap<Integer, DataField> dataFields = new TreeMap<>();

        public Builder(Iso8583MessageDefinition iso8583MessageDefinition, DataField messageTypeIndicator) {
            this.definition = iso8583MessageDefinition;
            this.messageTypeIndicator = messageTypeIndicator;
            this.primaryBitmap = initializeEmptyBitmap(definition.getPrimaryBitmapDefinition());
        }

        public Builder setField(int fieldNumber, byte[] rawData) {
            DataField dataField = definition.getFieldDefinition(fieldNumber)
                    .getDataFieldBuilder()
                    .build(rawData);
            return setField(fieldNumber, dataField);
        }

        public Builder setField(int fieldNumber, String data) {
            DataField dataField = definition.getFieldDefinition(fieldNumber)
                    .getDataFieldBuilder()
                    .build(data);
            return setField(fieldNumber, dataField);
        }

        public Builder setField(int fieldNumber, DataField dataField) {
            //TODO: Do not allow setting of bitmap fields, as these are automatic
            if (!definition.getFieldDefinitions().containsKey(fieldNumber)) {
                throw new IllegalArgumentException(
                        "DataField number " + fieldNumber + " is not defined for this Message");
            }

            setCorrespondingBitmapBit(fieldNumber);
            dataFields.put(fieldNumber, dataField);

            return this;
        }

        private void setCorrespondingBitmapBit(int fieldNumber) {
            if (definition.primaryBitmapGovernsBit(fieldNumber)) {
                primaryBitmap.setBit(fieldNumber);
            } else if (definition.secondaryBitmapGovernsBit(fieldNumber)) {
                setSecondaryBitmapBit(fieldNumber);
            } else if (definition.tertiaryBitmapGovernsBit(fieldNumber)) {
                setTertiaryBitmapBit(fieldNumber);
            } else {
                throw new IllegalArgumentException(
                        "DataField number " + fieldNumber + " is not governed by any defined bitmap");
            }
        }

        private void setSecondaryBitmapBit(int fieldNumber) {
            setBitmapFieldBit(definition.getSecondaryBitmapFieldNumber(), fieldNumber);
        }

        private void setTertiaryBitmapBit(int fieldNumber) {
            setBitmapFieldBit(definition.getSecondaryBitmapFieldNumber(), fieldNumber);
        }

        private void setBitmapFieldBit(int bitmapFieldNumber, int fieldNumber) {
            if (dataFields.containsKey(bitmapFieldNumber)) {
                Bitmap bitmap = (Bitmap) dataFields.get(bitmapFieldNumber);
                bitmap.setBit(fieldNumber);
            } else {
                Bitmap bitmap = initializeEmptyBitmapField(bitmapFieldNumber);
                bitmap.setBit(fieldNumber);
                setField(bitmapFieldNumber, bitmap);
            }
        }

        private Bitmap initializeEmptyBitmapField(int bitMapFieldNumber) {
            BitmapDefinition bitmapDefinition = (BitmapDefinition) definition
                    .getFieldDefinition(bitMapFieldNumber);
            return initializeEmptyBitmap(bitmapDefinition);
        }

        private Bitmap initializeEmptyBitmap(BitmapDefinition bitmapDefinition) {
            return bitmapDefinition.getDataFieldBuilder().build(new byte[bitmapDefinition.getByteLength()]);
        }

        public Builder removeField(int fieldNumber) {
            //TODO: Do not allow removal of bitmap fields, as these are automatic
            if (!dataFields.containsKey(fieldNumber)) {
                throw new IllegalArgumentException("Message does not contain DataField number " + fieldNumber);
            }

            unsetCorrespondingBitmapBit(fieldNumber);
            dataFields.remove(fieldNumber);

            return this;
        }

        private void unsetCorrespondingBitmapBit(int fieldNumber) {
            if (definition.primaryBitmapGovernsBit(fieldNumber)) {
                primaryBitmap.unsetBit(fieldNumber);
            } else if (definition.secondaryBitmapGovernsBit(fieldNumber)) {
                unsetSecondaryBitmapBit(fieldNumber);
            } else if (definition.tertiaryBitmapGovernsBit(fieldNumber)) {
                unsetTertiaryBitmapBit(fieldNumber);
            } else {
                throw new IllegalArgumentException(
                        "DataField number " + fieldNumber + " is not governed by any defined bitmap");
            }
        }

        private void unsetSecondaryBitmapBit(int fieldNumber) {
            unsetBitmapFieldBit(definition.getSecondaryBitmapFieldNumber(), fieldNumber);
        }

        private void unsetTertiaryBitmapBit(int fieldNumber) {
            unsetBitmapFieldBit(definition.getTertiaryBitmapFieldNumber(), fieldNumber);
        }

        private void unsetBitmapFieldBit(int bitmapFieldNumber, int fieldNumber) {
            Bitmap bitmap = (Bitmap) dataFields.get(bitmapFieldNumber);
            bitmap.unsetBit(fieldNumber);
            if (bitmap.isEmpty()) {
                removeField(bitmapFieldNumber);
            }
        }

        public Iso8583Message build() {
            return new Iso8583Message(this);
        }
    }
}