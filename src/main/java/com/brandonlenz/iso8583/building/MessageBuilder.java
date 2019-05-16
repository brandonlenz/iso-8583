package com.brandonlenz.iso8583.building;

import com.brandonlenz.iso8583.definitions.messages.MessageDefinition;
import com.brandonlenz.iso8583.messages.Message;

public interface MessageBuilder {
    Message getMessage();
    MessageDefinition getMessageDefinition();
    void setField(int fieldIndex, byte[] data);
    void setField(int fieldIndex, String data);
    void removeField(int fieldIndex);
}
