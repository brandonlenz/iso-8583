package com.brandonlenz.generic.fields;

public final class VariableLengthIndicator extends AbstractFixedField {

    private VariableLengthIndicator(VariableLengthIndicatorDefinition definition) {
        super(definition);
    }

    public static Builder builder(VariableLengthIndicatorDefinition definition) {
        return new Builder(definition);
    }

    public static Parser parser(VariableLengthIndicatorDefinition definition) {
        return new Parser(definition);
    }

    public int getIndicatedLength() {
        return Integer.parseInt(getData());
    }

    protected static class Builder extends AbstractFixedFieldBuilder<VariableLengthIndicator> {

        Builder(VariableLengthIndicatorDefinition definition) {
            super(new VariableLengthIndicator(definition));
        }
    }

    static class Parser extends AbstractFixedFieldParser<VariableLengthIndicator> {

        Parser(VariableLengthIndicatorDefinition definition) {
            super(new VariableLengthIndicator(definition));
        }
    }
}
