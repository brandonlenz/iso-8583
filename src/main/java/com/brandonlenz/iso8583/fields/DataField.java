package com.brandonlenz.iso8583.fields;

import com.brandonlenz.iso8583.definitions.fields.FieldDefinition;
import com.brandonlenz.iso8583.definitions.names.FieldName;
import com.brandonlenz.iso8583.structure.format.Format;

public abstract class DataField {

    private final FieldDefinition definition;
    private byte[] rawData;

    public DataField(FieldDefinition fieldDefinition) {
        this.definition = fieldDefinition;
    }

    public FieldName getName() {
        return definition.getFieldName();
    }

    public Format getFormat() {
        return definition.getFormat();
    }

    public FieldDefinition getDefinition() {
        return definition;
    }

    public byte[] getRawData() {
        return this.rawData;
    }

    public void setRawData(byte[] rawData) {
        this.rawData = rawData;
    }

    public String getData() {
        return definition.getEncoding().decode(rawData);
    }

    @Override
    public String toString() {
        return this.getData();
    }
}
