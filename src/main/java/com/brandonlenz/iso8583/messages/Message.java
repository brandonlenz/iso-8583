package com.brandonlenz.iso8583.messages;

import com.brandonlenz.iso8583.definitions.messages.MessageDefinition;
import com.brandonlenz.iso8583.fields.DataField;
import java.util.List;

public interface Message {
    byte[] getRawData();

    MessageDefinition getDefinition();

    List<DataField> getDataFields();
}
