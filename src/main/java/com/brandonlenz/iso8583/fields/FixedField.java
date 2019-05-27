package com.brandonlenz.iso8583.fields;

import com.brandonlenz.iso8583.definitions.fields.FixedFieldDefinition;

public class FixedField extends DataField {

    private final FixedFieldDefinition definition;

    public FixedField(FixedFieldDefinition fieldDefinition) {
        super(fieldDefinition);
        this.definition = fieldDefinition;
    }

    @Override
    public FixedFieldDefinition getDefinition() {
        return definition;
    }
}
