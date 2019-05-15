package com.brandonlenz.iso8583.buidling;

import com.brandonlenz.iso8583.messages.Message;

public interface MessageBuilder {
    Message getMessage();
    void setField(int fieldIndex, byte[] data);
    void removeField(int fieldIndex);
}
