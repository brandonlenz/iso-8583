package com.brandonlenz.iso8583.fields;

import com.brandonlenz.iso8583.definitions.fields.FixedFieldDefinition;
import com.brandonlenz.iso8583.definitions.names.FieldName;
import com.brandonlenz.iso8583.structure.content.ContentType;
import com.brandonlenz.iso8583.structure.encoding.Encoding;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FixedFieldTest {
    private final FixedFieldDefinition definition = new FixedFieldDefinition(
            FieldName.AMOUNT_TRANSACTION,
                12,
            Encoding.HEXADECIMAL_ASCII,
            ContentType.NUMERIC);

    @Test
    void testFixedField() {
        String testAmountData = "000000120000";
        FixedField amountField = definition.getDataFieldBuilder().setData(testAmountData).build();

        Assertions.assertEquals("000000120000", amountField.getData());
    }
}