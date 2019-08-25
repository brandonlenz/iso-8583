package com.brandonlenz.iso8583.fields;

import com.brandonlenz.generic.fields.VariableLengthIndicatorDefinition;
import com.brandonlenz.generic.fields.VariableLengthIndicatedFieldDefinition;
import com.brandonlenz.generic.structure.content.ContentType;
import com.brandonlenz.generic.structure.encoding.Encoding;
import com.brandonlenz.iso8583.definitions.fields.names.FieldName;

public final class LLVarDefinition extends VariableLengthIndicatedFieldDefinition {

    public LLVarDefinition(FieldName name,
                           Encoding encoding,
                           ContentType contentType,
                           Encoding vliEncoding,
                           ContentType vliContentType) {
        super(name, encoding, contentType,
                new VariableLengthIndicatorDefinition(2, vliEncoding, vliContentType));
    }
}
