package com.brandonlenz.iso8583.fields;

import com.brandonlenz.generic.structure.format.Format;
import com.brandonlenz.iso8583.definitions.fields.names.FieldName;

//TODO: Move to generic (including subclasses?)
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

    void setRawData(byte[] rawData) {
        this.rawData = rawData;
    }

    public String getData() {
        return definition.getEncoding().decode(rawData);
    }

    public static <F extends DataField> DataFieldBuilder<F> builder(FieldDefinition<F> fieldDefinition) {
        return fieldDefinition.getDataFieldBuilder();
    }

    @Override
    public String toString() {
        return this.getData();
    }
}