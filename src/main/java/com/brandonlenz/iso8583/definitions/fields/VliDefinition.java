package com.brandonlenz.iso8583.definitions.fields;

import com.brandonlenz.iso8583.definitions.fields.names.FieldName;
import com.brandonlenz.generic.structure.content.ContentType;
import com.brandonlenz.generic.structure.encoding.Encoding;

public final class VliDefinition extends FixedFieldDefinition {

    public VliDefinition(int length, Encoding encoding, ContentType contentType) {
        //TODO: only allow initialization of VliDefinition with Numeric or Bytes ContentTypes
        super(FieldName.VARIABLE_LENGTH_INDICATOR, length, encoding, contentType);
    }
}