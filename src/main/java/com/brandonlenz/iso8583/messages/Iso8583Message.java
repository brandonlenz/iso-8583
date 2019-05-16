package com.brandonlenz.iso8583.messages;

import com.brandonlenz.iso8583.definitions.fields.FieldDefinition;
import com.brandonlenz.iso8583.definitions.messages.Iso8583MessageDefinition;
import com.brandonlenz.iso8583.definitions.messages.MessageDefinition;
import com.brandonlenz.iso8583.fields.DataField;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Iso8583Message implements Message {

    private final Iso8583MessageDefinition definition;
    private DataField messageTypeIndicator;
    private DataField primaryBitmap;
    private List<DataField> dataFields;

    public Iso8583Message(Iso8583MessageDefinition definition) {
        this.definition = definition;
        this.messageTypeIndicator = new DataField(definition.getMessageTypeIndicatorDefinition()); //TODO: Probably set this from the start and make final
        this.primaryBitmap = new DataField(definition.getPrimaryBitmapDefinition());
        this.primaryBitmap.setRawData(new byte[definition.getPrimaryBitmapDefinition().getLength()]); //TODO: Maybe extract to bitmap class extending DataField? For now just set to all 0's
        this.dataFields = createDataFieldsFromDefinition(definition);
    }

    @Override
    public byte[] getRawData() {
        try(ByteArrayOutputStream bytes = new ByteArrayOutputStream()) {
            bytes.write(messageTypeIndicator.getRawData());
            bytes.write(primaryBitmap.getRawData());
            for(DataField dataField : dataFields) {
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

    public DataField getPrimaryBitmap() {
        return primaryBitmap;
    }

    public DataField getDataField(int dataFieldNumber) {
        return dataFields.get(dataFieldNumber - 1);
    }

    public void setDataField(int dataFieldNumber, DataField dataField) {
        dataFields.set(dataFieldNumber - 1, dataField);
        setBitmapBit(dataFieldNumber);
    }

    public void removeDataField(int dataFieldNumber) {
        dataFields.remove(dataFieldNumber - 1);
        unsetBitmapBit(dataFieldNumber);
    }

    void setBitmapBit(int dataFieldNumber) {
        if (!bitIsSet(dataFieldNumber)) {
            flipBitmapBit(dataFieldNumber);
        } else {
            throw new IllegalArgumentException("Bit corresponding to field number " + dataFieldNumber + " is already set");
        }
    }

    void unsetBitmapBit(int dataFieldNumber) {
        if (bitIsSet(dataFieldNumber)) {
            flipBitmapBit(dataFieldNumber);
        } else {
            throw new IllegalArgumentException("Bit corresponding to field number " + dataFieldNumber + " is not yet set");
        }
    }

    private boolean bitIsSet(int dataFieldNumber) {
        int byteIndex = getByteIndexFromDataFieldNumber(dataFieldNumber);
        int bitIndex = getBitIndexFromDataFieldNumber(dataFieldNumber);

        return ((primaryBitmap.getRawData()[byteIndex] & 0xFF) & (1 << (bitIndex - 1))) >= 1;
    }

    private void flipBitmapBit(int dataFieldNumber) {
        int byteIndex = getByteIndexFromDataFieldNumber(dataFieldNumber);
        int bitIndex = getBitIndexFromDataFieldNumber(dataFieldNumber);

        primaryBitmap.getRawData()[byteIndex] = (byte) ((primaryBitmap.getRawData()[byteIndex] & 0xFF) ^ (1 << (bitIndex - 1)));
    }

    private int getByteIndexFromDataFieldNumber(int dataFieldNumber) {
        return (dataFieldNumber - 1) / 8;
    }

    private int getBitIndexFromDataFieldNumber(int dataFieldNumber) {
        return 8 - ((dataFieldNumber - 1) % 8);
    }

    private List<DataField> createDataFieldsFromDefinition(Iso8583MessageDefinition definition) {
        dataFields = new ArrayList<>();

        for (FieldDefinition fieldDefinition : definition.getFieldDefinitions()) {
            dataFields.add(new DataField(fieldDefinition));
        }

        return dataFields;
    }
}
