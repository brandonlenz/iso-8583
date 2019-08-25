package com.brandonlenz.generic.fields;

import com.brandonlenz.generic.structure.format.Format;
import com.brandonlenz.iso8583.definitions.fields.names.FieldName;

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

    public abstract FieldDefinition getDefinition();

    public byte[] getRawData() {
        return this.rawData;
    }

    void setRawData(byte[] rawData) {
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