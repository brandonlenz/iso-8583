package com.brandonlenz.iso8583.validation;

import com.brandonlenz.iso8583.fields.DataField;
import com.brandonlenz.iso8583.structure.encoding.handlers.EncodingHandler;

public class DataFieldValidator {

    public boolean dataFieldIsValid(DataField dataField) {
        return encodingIsValid(dataField) && contentIsValid(dataField) && formatIsValid(dataField);
    }

    private boolean encodingIsValid(DataField dataField) {
        try {
            EncodingHandler encodingHandler = dataField.getDefinition().getEncoding().getEncodingHandler();
            encodingHandler.decode(dataField.getRawData());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private boolean contentIsValid(DataField dataField) {
        return true; //TODO
    }

    private boolean formatIsValid(DataField dataField) {
        return true; //TODO (Validate length, vli, etc kind of things)
    }
}
