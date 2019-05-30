package com.brandonlenz.iso8583.building.fields;

import com.brandonlenz.iso8583.definitions.fields.FieldDefinition;
import com.brandonlenz.iso8583.fields.DataField;
import com.brandonlenz.iso8583.validation.DataFieldValidator;

public class DataFieldBuilder<D extends FieldDefinition, F extends DataField> {

    private static final DataFieldValidator datafieldValidator = new DataFieldValidator(); //TODO: could break on vli (since VLI and Datafield may have different formats)
    private final D fieldDefinition;
    private final F dataField;

    public DataFieldBuilder(D fieldDefinition, F dataField) {
        this.fieldDefinition = fieldDefinition;
        this.dataField = dataField;
    }

    public F build() {
        return this.dataField;
    }

    public DataFieldBuilder<D, F> setRawData(byte[] rawData) {
        dataField.setRawData(rawData);
        if (!datafieldValidator.dataFieldIsValid(dataField)) {
            throw new IllegalArgumentException("The supplied data was not valid");
        }
        return this;
    }

    public DataFieldBuilder<D, F> setData(String data) {
        dataField.setRawData(fieldDefinition.getEncoding().encode(data));
        return this;
    }
}
