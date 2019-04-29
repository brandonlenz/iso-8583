package com.brandonlenz.iso8583.definitions;

import com.brandonlenz.iso8583.definitions.fields.FieldDefinition;
import java.util.List;

public interface MessageDefinition {
    List<FieldDefinition> getFieldDefinitions();
    FieldDefinition getFieldDefinition(int fieldNumber);
}
