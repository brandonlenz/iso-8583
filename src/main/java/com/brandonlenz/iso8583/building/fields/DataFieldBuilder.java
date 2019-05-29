package com.brandonlenz.iso8583.building.fields;

import com.brandonlenz.iso8583.definitions.fields.FieldDefinition;
import com.brandonlenz.iso8583.fields.DataField;

public class DataFieldBuilder<D extends FieldDefinition, F extends DataField> {

    private final D fieldDefinition;
    private final F dataField;

    public DataFieldBuilder(D fieldDefinition, F dataField) {
        this.fieldDefinition = fieldDefinition;
        this.dataField = dataField;
    }

    public F build() {
        return this.dataField;
    }

    public DataFieldBuilder<D, F> setRawData(byte[] rawData) {
        dataField.setRawData(rawData);
        return this;
    }

    public DataFieldBuilder<D, F> setData(String data) {
        dataField.setRawData(fieldDefinition.getEncoding().encode(data));
        return this;
    }
}
