package com.brandonlenz.iso8583.building.fields;

import com.brandonlenz.iso8583.definitions.fields.FixedFieldDefinition;
import com.brandonlenz.iso8583.fields.FixedField;

public class FixedFieldBuilder extends DataFieldBuilder {

    private final FixedFieldDefinition fieldDefinition;
    private final FixedField dataField;

    public FixedFieldBuilder(FixedFieldDefinition fieldDefinition) {
        super(fieldDefinition);
        this.fieldDefinition = fieldDefinition;
        this.dataField = new FixedField(fieldDefinition);
    }

    @Override
    public FixedField build() {
        return this.dataField;
    }

    @Override
    public FixedFieldBuilder setRawData(byte[] rawData) {
        //TODO: Validator?
        dataField.setRawData(rawData);
        return this;
    }

    @Override
    public FixedFieldBuilder setData(String data) {
        setRawData(fieldDefinition.getEncoding().encode(data));
        return this;
    }
}