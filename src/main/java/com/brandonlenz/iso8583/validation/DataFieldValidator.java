package com.brandonlenz.iso8583.validation;

import com.brandonlenz.iso8583.fields.DataField;

public class DataFieldValidator {

    public boolean dataFieldIsValid(DataField dataField) {
        return encodingIsValid(dataField) && contentIsValid(dataField) && formatIsValid(dataField);
    }

    private boolean encodingIsValid(DataField dataField) {
        try {
            dataField.getDefinition().getEncoding().decode(dataField.getRawData());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private boolean contentIsValid(DataField dataField) {
        return dataField.getDefinition().getContentType().isValid(dataField.getData());
    }

    private boolean formatIsValid(DataField dataField) {
        return true; //TODO (Validate length, vli, etc kind of things)
    }
}