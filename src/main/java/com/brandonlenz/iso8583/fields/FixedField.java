package com.brandonlenz.iso8583.fields;

public class FixedField extends AbstractFixedField {

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
