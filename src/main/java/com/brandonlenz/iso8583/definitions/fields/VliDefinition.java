package com.brandonlenz.iso8583.definitions.fields;

import com.brandonlenz.iso8583.building.fields.VliBuilder;
import com.brandonlenz.iso8583.definitions.names.FieldName;
import com.brandonlenz.iso8583.structure.content.ContentType;
import com.brandonlenz.iso8583.structure.encoding.Encoding;

public final class VliDefinition extends FixedFieldDefinition {

    public VliDefinition(int length, Encoding encoding) {
        super(FieldName.VARIABLE_LENGTH_INDICATOR, length, encoding, ContentType.NUMERIC);
    }

    @Override
    public VliBuilder getDataFieldBuilder() {
        return new VliBuilder(this);
    }
}