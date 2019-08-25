package com.brandonlenz.generic.fields;

public class FixedField extends AbstractFixedField {

    private final FixedFieldDefinition definition;

    private FixedField(FixedFieldDefinition fieldDefinition) {
        super(fieldDefinition);
        this.definition = fieldDefinition;
    }

    public static Builder builder(FixedFieldDefinition definition) {
        return new Builder(definition);
    }

    public static Parser parser(FixedFieldDefinition definition) {
        return new Parser(definition);
    }

    @Override
    public FixedFieldDefinition getDefinition() {
        return definition;
    }

    public static class Builder extends AbstractFixedFieldBuilder<FixedField> {

        Builder(FixedFieldDefinition definition) {
            super(new FixedField(definition));
        }
    }

    public static class Parser extends AbstractFixedFieldParser<FixedField> {

        Parser(FixedFieldDefinition definition) {
            super(new FixedField(definition));
        }
    }
}
