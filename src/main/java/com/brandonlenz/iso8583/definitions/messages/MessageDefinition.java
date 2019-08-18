package com.brandonlenz.iso8583.definitions.messages;

import com.brandonlenz.iso8583.definitions.fields.FieldDefinition;
import com.brandonlenz.iso8583.definitions.names.FieldName;
import java.util.List;
import java.util.Map;

public interface MessageDefinition { //TODO: rename as ProtocolDefinition? Abstract MessageDefinition seperately?
    Map<Integer, FieldDefinition> getFieldDefinitions();
    FieldDefinition getFieldDefinition(int fieldNumber);
    FieldDefinition getFieldDefinition(FieldName fieldName);
}
