package com.brandonlenz.generic.fields;

import com.brandonlenz.generic.structure.content.ContentType;
import com.brandonlenz.generic.structure.encoding.Encoding;
import com.brandonlenz.iso8583.definitions.fields.names.FieldName;
import java.util.ArrayList;
import java.util.List;

public class FixedFieldDefinition extends AbstractFixedFieldDefinition<FixedField> {

    public FixedFieldDefinition(FieldName name,
                                int length,
                                Encoding encoding,
                                ContentType contentType) {
        this(name, length, encoding, contentType, new ArrayList<>());
    }

    public FixedFieldDefinition(FieldName name,
                                int length,
                                Encoding encoding,
                                ContentType contentType,
                                List<FieldDefinition> subfieldDefinitions) {
        super(name, length, encoding, contentType, subfieldDefinitions);
    }

    @Override
    public DataFieldBuilder<FixedField> getDataFieldBuilder() {
        return FixedField.builder(this);
    }

    @Override
    public DataFieldParser<FixedField> getDataFieldParser() {
        return FixedField.parser(this);
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