package com.brandonlenz.iso8583.validation;

public final class DataFieldValidatorFactory {
    private static final DataFieldValidator dataFieldValidator = new DataFieldValidator();

    private DataFieldValidatorFactory() {
        //hide implicit constructor
    }

    public static DataFieldValidator getDataFieldValidator() {
        return dataFieldValidator;
    }
}
