package com.brandonlenz.iso8583.building.fields;

import com.brandonlenz.iso8583.definitions.fields.FieldDefinition;
import com.brandonlenz.iso8583.fields.DataField;

public abstract class DataFieldBuilder {

    private final FieldDefinition fieldDefinition;

    DataFieldBuilder(FieldDefinition fieldDefinition) {
        this.fieldDefinition = fieldDefinition;
    }

    public abstract DataField build();

    public abstract DataFieldBuilder setRawData(byte[] rawData);

    public abstract DataFieldBuilder setData(String data);
}
