package com.brandonlenz.iso8583.parsing.fields;

import com.brandonlenz.iso8583.definitions.fields.FixedFieldDefinition;
import com.brandonlenz.iso8583.fields.FixedField;
import java.io.InputStream;

public class FixedFieldParser extends DataFieldParser<FixedFieldDefinition, FixedField> {

    public FixedFieldParser(FixedFieldDefinition fieldDefinition) {
        super(fieldDefinition);
    }

    @Override
    public FixedField parseFromStream(InputStream is) {
        int bytesToRead = fieldDefinition.getByteLength();

        return fieldDefinition.getDataFieldBuilder()
                .setRawData(getFieldBytes(is, bytesToRead))
                .build();
    }
}
