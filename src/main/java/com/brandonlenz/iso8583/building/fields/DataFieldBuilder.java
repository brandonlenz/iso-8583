package com.brandonlenz.iso8583.building.fields;

import com.brandonlenz.iso8583.definitions.fields.FieldDefinition;
import com.brandonlenz.iso8583.fields.DataField;

public class DataFieldBuilder<F extends FieldDefinition, D extends DataField> {

    private final F fieldDefinition;
    private final D dataField;

    public DataFieldBuilder(F fieldDefinition, D dataField) {
        this.fieldDefinition = fieldDefinition;
        this.dataField = dataField;
    }

    public D build() {
        return this.dataField;
    }

    public DataFieldBuilder<F, D> setRawData(byte[] rawData) {
        dataField.setRawData(rawData);
        return this;
    }

    public DataFieldBuilder<F, D> setData(String data) {
        dataField.setRawData(fieldDefinition.getEncoding().encode(data));
        return this;
    }
}
