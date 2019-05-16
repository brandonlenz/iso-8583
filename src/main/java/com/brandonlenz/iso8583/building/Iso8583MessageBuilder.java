package com.brandonlenz.iso8583.building;

import com.brandonlenz.iso8583.definitions.messages.Iso8583MessageDefinition;
import com.brandonlenz.iso8583.definitions.messages.MessageDefinition;
import com.brandonlenz.iso8583.messages.Iso8583Message;
import com.brandonlenz.iso8583.messages.Message;

public class Iso8583MessageBuilder implements MessageBuilder {
    private Iso8583Message message;

    public Iso8583MessageBuilder(Iso8583MessageDefinition iso8583MessageDefinition) {
        this.message = new Iso8583Message(iso8583MessageDefinition);
    }

    @Override
    public void setField(int dataFieldNumber, byte[] data) {
        DataFieldBuilder dataFieldBuilder = new DataFieldBuilder(message.getDefinition().getFieldDefinition(dataFieldNumber), data);
        message.setDataField(dataFieldNumber, dataFieldBuilder.getDataField());
    }

    @Override
    public void setField(int dataFieldNumber, String data) {
        DataFieldBuilder dataFieldBuilder = new DataFieldBuilder(getMessageDefinition().getFieldDefinition(dataFieldNumber), data);
        message.setDataField(dataFieldNumber, dataFieldBuilder.getDataField());
    }

    @Override
    public void removeField(int dataFieldNumber) {
        message.removeDataField(dataFieldNumber);
    }

    @Override
    public Message getMessage() {
        return message;
    }

    public Iso8583Message getIso8583Message() {
        return message;
    }

    @Override
    public MessageDefinition getMessageDefinition() {
        return this.message.getDefinition();
    }
}
