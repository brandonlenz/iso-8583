package com.brandonlenz.iso8583.fields;

import com.brandonlenz.generic.structure.content.ContentType;
import com.brandonlenz.generic.structure.encoding.Encoding;
import com.brandonlenz.generic.structure.format.Format;
import com.brandonlenz.iso8583.definitions.fields.names.FieldName;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractFixedFieldDefinition<F extends AbstractFixedField> extends FieldDefinition<F> {

    private final int length;

    AbstractFixedFieldDefinition(FieldName name,
                                 int length,
                                 Encoding encoding,
                                 ContentType contentType) {
        this(name, length, encoding, contentType, new ArrayList<>());
    }

    AbstractFixedFieldDefinition(FieldName name,
                                 int length,
                                 Encoding encoding,
                                 ContentType contentType,
                                 List<FieldDefinition> subfieldDefinitions) {
        super(name, Format.FIXED, encoding, contentType, subfieldDefinitions);
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public int getByteLength() {
        return getEncoding().getEncodingHandler().getByteLength(length);
    }

}
