package com.brandonlenz.iso8583.definitions.fields;

import com.brandonlenz.iso8583.building.fields.DataFieldBuilder;
import com.brandonlenz.iso8583.definitions.fields.names.FieldName;
import com.brandonlenz.iso8583.fields.FixedField;
import com.brandonlenz.iso8583.parsing.fields.FixedFieldParser;
import com.brandonlenz.iso8583.structure.content.ContentType;
import com.brandonlenz.iso8583.structure.encoding.Encoding;
import com.brandonlenz.iso8583.structure.format.Format;
import java.util.ArrayList;
import java.util.List;

public class FixedFieldDefinition extends FieldDefinition<FixedField> {

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
    public DataFieldBuilder<FixedField> getDataFieldBuilder() {
        return new DataFieldBuilder<>(() -> new FixedField(this));
    }

    @Override
    public FixedFieldParser getDataFieldParser() {
        return new FixedFieldParser(this);
    }

    @Override
    public String toString() {
        return "Field Name: " + this.getFieldName().toString() +
                ", Format: " + this.getFormat() +
                ", Length: " + this.getLength() +
                ", Encoding: " + this.getEncoding() +
                ", Content Type: " + this.getContentType();
    }
}