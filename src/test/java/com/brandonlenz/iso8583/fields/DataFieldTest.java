package com.brandonlenz.iso8583.fields;

import com.brandonlenz.iso8583.definitions.fields.FieldDefinition;
import com.brandonlenz.iso8583.definitions.fields.FixedFieldDefinition;
import com.brandonlenz.iso8583.definitions.names.FieldName;
import com.brandonlenz.iso8583.structure.content.ContentType;
import com.brandonlenz.iso8583.structure.encoding.Encoding;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DataFieldTest {

    @Test
    void getRawData() {
        FieldDefinition testFieldDefinition = new FixedFieldDefinition(FieldName.AMOUNT_TRANSACTION,
                                                                 8,
                                                                       Encoding.HEXADECIMAL_ASCII,
                                                                       ContentType.NUMERIC);
        DataField testDataField = new DataField(testFieldDefinition);

        Assertions.assertNull(testDataField.getRawData());
    }
}