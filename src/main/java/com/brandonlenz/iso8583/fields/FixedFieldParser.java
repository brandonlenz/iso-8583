package com.brandonlenz.iso8583.fields;

import java.io.InputStream;

public class FixedFieldParser extends DataFieldParser<FixedField> {

    private final FixedFieldDefinition fieldDefinition;

    public FixedFieldParser(FixedFieldDefinition fieldDefinition) {
        this.fieldDefinition = fieldDefinition;
    }

    @Override
    public FixedField parseFromStream(InputStream is) {
        int bytesToRead = fieldDefinition.getByteLength();
        byte[] rawData = parseBytesFromStream(is, bytesToRead);
        return fieldDefinition.getDataFieldBuilder()
                .build(rawData);
    }
}
