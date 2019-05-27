package com.brandonlenz.iso8583.fields;

import com.brandonlenz.iso8583.definitions.fields.VliDefinition;

public class Vli extends FixedField {

    private VliDefinition definition;

    public Vli(VliDefinition fieldDefinition) {
        super(fieldDefinition);
        this.definition = fieldDefinition;
    }

    @Override
    public VliDefinition getDefinition() {
        return definition;
    }

    public int getIndicatedLength() {
        return Integer.parseInt(definition.getEncoding().decode(getRawData()));
    }
}
