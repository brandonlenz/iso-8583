package com.brandonlenz.generic.fields;

import java.io.InputStream;

public class VariableLengthIndicatedField extends DataField {

    private final VariableLengthIndicatedFieldDefinition definition;
    private final VariableLengthIndicator vli;

    protected VariableLengthIndicatedField(VariableLengthIndicatedFieldDefinition fieldDefinition,
            VariableLengthIndicator vli) {
        super(fieldDefinition);
        this.definition = fieldDefinition;
        this.vli = vli;
    }

    public static Builder builder(VariableLengthIndicatedFieldDefinition definition) {
        return new Builder(definition);
    }

    public static Parser parser(VariableLengthIndicatedFieldDefinition definition) {
        return new Parser(definition);
    }

    @Override
    public VariableLengthIndicatedFieldDefinition getDefinition() {
        return definition;
    }

    @Override
    public byte[] getRawData() {
        if (vli.getRawData() == null && super.getRawData() == null) {
            return null;
        }

        byte[] vliRawData = vli.getRawData();
        byte[] vliAndFieldRawData = new byte[vliRawData.length + super.getRawData().length];

        System.arraycopy(vliRawData, 0, vliAndFieldRawData, 0, vliRawData.length);
        System.arraycopy(super.getRawData(), 0, vliAndFieldRawData, vliRawData.length, super.getRawData().length);

        return vliAndFieldRawData;
    }

    @Override
    public String getData() {
        return vli.getData() + super.getData();
    }

    public VariableLengthIndicator getVli() {
        return vli;
    }

    static class Builder implements DataFieldBuilder<VariableLengthIndicatedField> {

        private final VariableLengthIndicatedFieldDefinition definition;

        Builder(VariableLengthIndicatedFieldDefinition definition) {
            this.definition = definition;
        }

        @Override
        public VariableLengthIndicatedField build(byte[] rawData) {
            return buildFromRawData(rawData);
        }

        @Override
        public VariableLengthIndicatedField build(String data) {
            byte[] rawData = definition.getEncoding().encode(data);
            return buildFromRawData(rawData);
        }

        private VariableLengthIndicatedField buildFromRawData(byte[] rawData) {
            VariableLengthIndicator vli = buildVli(rawData);
            VariableLengthIndicatedField vliField = new VariableLengthIndicatedField(definition, vli);
            vliField.setRawData(rawData);
            return vliField;
        }

        private VariableLengthIndicator buildVli(byte[] rawData) {
            VariableLengthIndicatorDefinition vliDefinition = definition.getVariableLengthIndicatorDefinition();
            byte[] vliRawData = createVliRawData(rawData.length, vliDefinition);
            return VariableLengthIndicator.builder(vliDefinition).build(vliRawData);
        }

        private byte[] createVliRawData(int indicatedLength, VariableLengthIndicatorDefinition vliDefinition) {
            return vliDefinition.getEncoding().encode(indicatedLength, vliDefinition.getByteLength());
        }
    }

    static class Parser implements DataFieldParser<VariableLengthIndicatedField> {

        private final VariableLengthIndicatedFieldDefinition definition;

        Parser(VariableLengthIndicatedFieldDefinition definition) {
            this.definition = definition;
        }

        @Override
        public VariableLengthIndicatedField parseFromStream(InputStream is) {
            VariableLengthIndicator vli = parseVliFromStream(is);
            VariableLengthIndicatedField vliField = new VariableLengthIndicatedField(definition, vli);

            byte[] fieldBytes = parseBytesFromStream(is, vli.getIndicatedLength());
            vliField.setRawData(fieldBytes);

            return vliField;
        }

        private VariableLengthIndicator parseVliFromStream(InputStream is) {
            VariableLengthIndicatorDefinition vliDefinition = definition.getVariableLengthIndicatorDefinition();
            return VariableLengthIndicator.parser(vliDefinition).parseFromStream(is);
        }
    }
}
