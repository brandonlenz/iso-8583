package com.brandonlenz.iso8583.parsing.fields;

import com.brandonlenz.iso8583.definitions.fields.FieldDefinition;
import com.brandonlenz.iso8583.fields.DataField;
import java.io.IOException;
import java.io.InputStream;

public abstract class DataFieldParser<D extends FieldDefinition, F extends DataField> {

    protected final D fieldDefinition;

    public DataFieldParser(D fieldDefinition) {
        this.fieldDefinition = fieldDefinition;
    }

    public abstract F parseFromStream(InputStream is);

    protected byte[] getFieldBytes(InputStream is, int fieldByteLength) {
        byte[] fieldBytes = new byte[fieldByteLength];

        try {
            int bytesRead = is.read(fieldBytes, 0, fieldByteLength);
            if (bytesRead < fieldByteLength) {
                throw new IllegalArgumentException("Prematurely reached end of message InputStream");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while trying to parse field " + fieldDefinition.getFieldName());
            e.printStackTrace();
        }

        return fieldBytes;
    }
}
