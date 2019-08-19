package com.brandonlenz.generic.messages;

import com.brandonlenz.iso8583.definitions.messages.MessageDefinition;
import com.brandonlenz.iso8583.fields.DataField;
import java.util.Map;


public interface Message { //TODO: For non index-driven messages, rework inheritance

    MessageDefinition getDefinition();

    byte[] getRawData();

    String getData();

    DataField getDataField(int fieldIndex);

    Map<Integer, DataField> getDataFields();
}
