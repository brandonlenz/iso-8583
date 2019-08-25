package com.brandonlenz.iso8583.messages;

import static com.brandonlenz.generic.fields.Bitmap.initializeEmptyBitmap;

import com.brandonlenz.generic.fields.BitmapDefinition;
import com.brandonlenz.iso8583.definitions.messages.Iso8583MessageDefinition;
import com.brandonlenz.generic.fields.Bitmap;
import com.brandonlenz.generic.fields.DataField;

public final class Iso8583MessageBuilder {

    private final Iso8583MessageDefinition definition;
    private final Iso8583Message message;

    Iso8583MessageBuilder(Iso8583MessageDefinition definition, DataField messageTypeIndicator) {
        this.definition = definition;
        this.message = new Iso8583Message(definition);
        message.setMessageTypeIndicator(messageTypeIndicator);
        message.setPrimaryBitmap(initializeEmptyBitmap(this.definition.getPrimaryBitmapDefinition()));
    }

    public Iso8583MessageBuilder setField(int fieldNumber, byte[] rawData) {
        DataField dataField = definition.getFieldDefinition(fieldNumber)
                .getDataFieldBuilder()
                .build(rawData);
        return setField(fieldNumber, dataField);
    }

    public Iso8583MessageBuilder setField(int fieldNumber, String data) {
        DataField dataField = definition.getFieldDefinition(fieldNumber)
                .getDataFieldBuilder()
                .build(data);
        return setField(fieldNumber, dataField);
    }

    public Iso8583MessageBuilder setField(int fieldNumber, DataField dataField) {
        //TODO: Do not allow setting of bitmap fields, as these are automatic
        if (!definition.getFieldDefinitions().containsKey(fieldNumber)) {
            throw new IllegalArgumentException("DataField number " + fieldNumber + " is not defined for this Message");
        }

        setCorrespondingBitmapBit(fieldNumber);
        message.setField(fieldNumber, dataField);

        return this;
    }

    private void setCorrespondingBitmapBit(int fieldNumber) {
        if (definition.primaryBitmapGovernsBit(fieldNumber)) {
            message.getPrimaryBitmap().setBit(fieldNumber);
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
        if (message.getDataFields().containsKey(bitmapFieldNumber)) {
            Bitmap bitmap = (Bitmap) message.getDataFields().get(bitmapFieldNumber);
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

    public Iso8583MessageBuilder removeField(int fieldNumber) {
        //TODO: Do not allow removal of bitmap fields, as these are automatic
        if (!message.getDataFields().containsKey(fieldNumber)) {
            throw new IllegalArgumentException("Message does not contain DataField number " + fieldNumber);
        }

        unsetCorrespondingBitmapBit(fieldNumber);
        message.removeField(fieldNumber);

        return this;
    }

    private void unsetCorrespondingBitmapBit(int fieldNumber) {
        if (definition.primaryBitmapGovernsBit(fieldNumber)) {
            message.getPrimaryBitmap().unsetBit(fieldNumber);
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
        Bitmap bitmap = (Bitmap) message.getDataField(bitmapFieldNumber);
        bitmap.unsetBit(fieldNumber);
        if (bitmap.isEmpty()) {
            removeField(bitmapFieldNumber);
        }
    }

    public Iso8583Message build() {
        return message;
    }
}

