package com.brandonlenz.iso8583.building.fields;

import com.brandonlenz.iso8583.definitions.fields.FixedFieldDefinition;
import com.brandonlenz.iso8583.definitions.names.FieldName;
import com.brandonlenz.iso8583.fields.FixedField;
import com.brandonlenz.iso8583.structure.content.ContentType;
import com.brandonlenz.iso8583.structure.encoding.Encoding;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FixedFieldBuilderTest {

    private FixedFieldBuilder fixedFieldBuilder;
    private FixedFieldDefinition definition =
            new FixedFieldDefinition(FieldName.AMOUNT_TRANSACTION, 12, Encoding.HEXADECIMAL_ASCII, ContentType.NUMERIC);

    @BeforeEach
    void setUp() {
        this.fixedFieldBuilder = definition.getDataFieldBuilder();
    }

    @Test
    void createFixedField() {
        String testAmountData = "000000120000";
        FixedField amountField = fixedFieldBuilder.setData(testAmountData).build();

        Assertions.assertEquals(testAmountData, amountField.getData());
    }
}