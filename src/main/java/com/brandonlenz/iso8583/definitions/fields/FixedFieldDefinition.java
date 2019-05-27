package com.brandonlenz.iso8583.definitions.fields;

import com.brandonlenz.iso8583.building.fields.DataFieldBuilder;
import com.brandonlenz.iso8583.definitions.names.FieldName;
import com.brandonlenz.iso8583.fields.FixedField;
import com.brandonlenz.iso8583.structure.Format;
import com.brandonlenz.iso8583.structure.content.ContentType;
import com.brandonlenz.iso8583.structure.encoding.Encoding;
import java.util.ArrayList;
import java.util.List;

public class FixedFieldDefinition extends FieldDefinition {

    private int length;

    public FixedFieldDefinition(FieldName name, int length, Encoding encoding, ContentType contentType) {
        this(name, length, encoding, contentType, new ArrayList<>());
    }

    public FixedFieldDefinition(FieldName name, int length, Encoding encoding, ContentType contentType,
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

    @Override
    public DataFieldBuilder<? extends FixedFieldDefinition, ? extends FixedField> getDataFieldBuilder() {
        FixedField fixedField = new FixedField(this);
        return new DataFieldBuilder<>(this, fixedField);
    }
}
