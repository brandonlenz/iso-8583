package com.brandonlenz.iso8583.building;

import com.brandonlenz.iso8583.definitions.fields.FieldDefinition;
import com.brandonlenz.iso8583.fields.DataField;
import com.brandonlenz.iso8583.validation.DataFieldValidator;

public class DataFieldBuilder {

    private DataField dataField;

    public DataFieldBuilder(FieldDefinition fieldDefinition) {
        this.dataField = new DataField(fieldDefinition);
    }

    public DataFieldBuilder(FieldDefinition fieldDefinition, byte[] rawData) {
        this.dataField = new DataField(fieldDefinition);
        buildDataFieldFromRawData(rawData);
    }

    public DataFieldBuilder(FieldDefinition fieldDefinition, String data) {
        this(fieldDefinition, fieldDefinition.getEncoding().getEncodingHandler().encode(data));
    }

    public DataField getDataField() {
        return dataField;
    }

    public void buildDataFieldFromRawData(byte[] rawData) {
        DataFieldValidator dataFieldValidator = new DataFieldValidator();
        dataField.setRawData(rawData);
        if (!dataFieldValidator.dataFieldIsValid(dataField)) {
            dataField.setRawData(null);
            throw new IllegalArgumentException("The supplied data does not conform to the field definition");
        }
    }
}
