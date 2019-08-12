package com.brandonlenz.iso8583.messages;

import com.brandonlenz.iso8583.definitions.messages.MessageDefinition;
import com.brandonlenz.iso8583.fields.DataField;
import java.util.Map;

public interface Message { //TODO: For non index-driven messages, rework inheritance

    byte[] getRawData();

    String getData();

    MessageDefinition getDefinition();

    Map<Integer, DataField> getDataFields();

    DataField getDataField(int fieldIndex);
}
