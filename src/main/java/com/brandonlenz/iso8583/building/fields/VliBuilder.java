package com.brandonlenz.iso8583.building.fields;

import com.brandonlenz.iso8583.definitions.fields.VliDefinition;
import com.brandonlenz.iso8583.fields.Vli;

public class VliBuilder extends DataFieldBuilder {

    private final VliDefinition fieldDefinition;
    private final Vli vli;

    public VliBuilder(VliDefinition fieldDefinition) {
        super(fieldDefinition);
        this.fieldDefinition = fieldDefinition;
        this.vli = new Vli(fieldDefinition);
    }

    @Override
    public Vli build() {
        return this.vli;
    }

    @Override
    public VliBuilder setRawData(byte[] rawData) {
        //TODO: Validator?
        vli.setRawData(rawData);
        return this;
    }

    @Override
    public VliBuilder setData(String data) {
        setRawData(fieldDefinition.getEncoding().encode(data));
        return this;
    }
}