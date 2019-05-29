package com.brandonlenz.iso8583.building.messages;

import com.brandonlenz.iso8583.definitions.messages.Iso8583MessageDefinition;
import com.brandonlenz.iso8583.fields.Bitmap;
import com.brandonlenz.iso8583.fields.DataField;
import com.brandonlenz.iso8583.messages.Iso8583Message;

public class Iso8583MessageBuilder implements MessageBuilder {

    private final Iso8583Message message;
    private final Iso8583MessageDefinition messageDefinition;

    public Iso8583MessageBuilder(Iso8583MessageDefinition iso8583MessageDefinition) {
        this.messageDefinition = iso8583MessageDefinition;
        this.message = new Iso8583Message(iso8583MessageDefinition);
    }

    public void setMessageTypeIndicator(byte[] rawData) {
        DataField messageTypeIndicator = messageDefinition.getMessageTypeIndicatorDefinition().getDataFieldBuilder()
                .setRawData(rawData)
                .build();
        message.setMessageTypeIndicator(messageTypeIndicator);
    }

    public void setMessageTypeIndicator(String data) {
        DataField messageTypeIndicator = messageDefinition.getMessageTypeIndicatorDefinition().getDataFieldBuilder()
                .setData(data)
                .build();
        message.setMessageTypeIndicator(messageTypeIndicator);
    }

    public void setPrimaryBitmap(byte[] rawData) {
        Bitmap bitmap = messageDefinition.getPrimaryBitmapDefinition().getDataFieldBuilder()
                .setRawData(rawData)
                .build();
        message.setPrimaryBitmap(bitmap);
    }

    public Bitmap getPrimaryBitmap() {
        return message.getPrimaryBitmap();
    }

    public Bitmap getSecondaryBitmap() {
        return message.getSecondaryBitmap();
    }

    public Bitmap getTertiaryBitmap() {
        return message.getTertiaryBitmap();
    }

    @Override
    public void setField(int dataFieldNumber, byte[] rawData) {
        DataField dataField = messageDefinition.getFieldDefinition(dataFieldNumber).getDataFieldBuilder()
                .setRawData(rawData)
                .build();
        message.setDataField(dataFieldNumber, dataField);

    }

    @Override
    public void setField(int dataFieldNumber, String data) {
        byte[] encodedData = messageDefinition.getFieldDefinition(dataFieldNumber).getEncoding().encode(data);
        setField(dataFieldNumber, encodedData);
    }

    @Override
    public void removeField(int dataFieldNumber) {
        message.removeDataField(dataFieldNumber);
    }

    @Override
    public Iso8583Message getMessage() {
        return message;
    }

    @Override
    public Iso8583MessageDefinition getMessageDefinition() {
        return this.message.getDefinition();
    }
}