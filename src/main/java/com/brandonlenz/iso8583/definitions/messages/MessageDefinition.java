package com.brandonlenz.iso8583.definitions.messages;

import com.brandonlenz.iso8583.definitions.fields.FieldDefinition;
import java.util.Map;

public interface MessageDefinition {
    Map<Integer, FieldDefinition> getFieldDefinitions();
    FieldDefinition getFieldDefinition(int fieldNumber);
}
