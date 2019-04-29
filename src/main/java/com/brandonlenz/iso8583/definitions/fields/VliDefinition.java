package com.brandonlenz.iso8583.definitions.fields;

import com.brandonlenz.iso8583.definitions.names.FieldName;
import com.brandonlenz.iso8583.structure.ContentType;
import com.brandonlenz.iso8583.structure.Encoding;

public class VliDefinition extends FixedFieldDefinition {

    public VliDefinition(int length, Encoding encoding) {
        super(FieldName.VARIABLE_LENGTH_INDICATOR, length, encoding, ContentType.NUMERIC);
    }
}
