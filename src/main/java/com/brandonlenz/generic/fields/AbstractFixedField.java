package com.brandonlenz.generic.fields;

import java.io.InputStream;

class AbstractFixedField extends DataField {

    private final AbstractFixedFieldDefinition definition;

    AbstractFixedField(AbstractFixedFieldDefinition definition) {
        super(definition);
        this.definition = definition;
    }

    @Override
    public AbstractFixedFieldDefinition getDefinition() {
        return definition;
    }

    static class AbstractFixedFieldBuilder<F extends AbstractFixedField> implements DataFieldBuilder<F> {

        private final F dataField;

        AbstractFixedFieldBuilder(F dataField) {
            this.dataField = dataField;
        }

        public F build(byte[] rawData) {
            dataField.setRawData(rawData);
            return dataField;
        }

        public F build(String data) {
            byte[] rawData = dataField.getDefinition().getEncoding().encode(data);
            dataField.setRawData(rawData);
            return dataField;
        }
    }

    static class AbstractFixedFieldParser<F extends AbstractFixedField> implements DataFieldParser<F> {

        private final F dataField;

        public AbstractFixedFieldParser(F dataField) {
            this.dataField = dataField;
        }

        @Override
        public F parseFromStream(InputStream is) {
            byte[] rawData = parseBytesFromStream(is, dataField.getDefinition().getByteLength());
            dataField.setRawData(rawData);
            return dataField;
        }
    }
}
