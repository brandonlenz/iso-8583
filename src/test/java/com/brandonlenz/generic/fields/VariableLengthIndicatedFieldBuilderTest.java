package com.brandonlenz.generic.fields;

import com.brandonlenz.iso8583.definitions.fields.names.FieldName;
import com.brandonlenz.generic.structure.content.ContentType;
import com.brandonlenz.generic.structure.encoding.Encoding;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class VariableLengthIndicatedFieldBuilderTest {

    @Test
    void setHex() {
        VariableLengthIndicatedFieldDefinition definition = new VariableLengthIndicatedFieldDefinition(
                FieldName.PRIMARY_ACCOUNT_NUMBER,
                Encoding.HEXADECIMAL_ASCII,
                ContentType.NUMERIC,
                new VariableLengthIndicatorDefinition(2, Encoding.HEXADECIMAL_ASCII, ContentType.NUMERIC));

        VariableLengthIndicatedField variableLengthIndicatedField = definition.getDataFieldBuilder().build("1234567890123456");
        Assertions.assertEquals("16", variableLengthIndicatedField.getVli().getData());
    }

    @Test
    void setHexPadded() {
        VariableLengthIndicatedFieldDefinition definition = new VariableLengthIndicatedFieldDefinition(
                FieldName.PRIMARY_ACCOUNT_NUMBER,
                Encoding.HEXADECIMAL_ASCII,
                ContentType.NUMERIC,
                new VariableLengthIndicatorDefinition(3, Encoding.HEXADECIMAL_ASCII, ContentType.NUMERIC));

        VariableLengthIndicatedField variableLengthIndicatedField = definition.getDataFieldBuilder().build("1234567890123456");
        Assertions.assertEquals("016", variableLengthIndicatedField.getVli().getData());
    }

    @Test
    void setBcd() {
        VariableLengthIndicatedFieldDefinition definition = new VariableLengthIndicatedFieldDefinition(
                FieldName.PRIMARY_ACCOUNT_NUMBER,
                Encoding.HEXADECIMAL_ASCII,
                ContentType.NUMERIC,
                new VariableLengthIndicatorDefinition(1, Encoding.BCD, ContentType.NUMERIC));

        VariableLengthIndicatedField variableLengthIndicatedField = definition.getDataFieldBuilder().build("123456789");
        Assertions.assertEquals("09", variableLengthIndicatedField.getVli().getData());
    }

    @Test
    void setBcdPadded() {
        VariableLengthIndicatedFieldDefinition definition = new VariableLengthIndicatedFieldDefinition(
                FieldName.PRIMARY_ACCOUNT_NUMBER,
                Encoding.HEXADECIMAL_ASCII,
                ContentType.NUMERIC,
                new VariableLengthIndicatorDefinition(3, Encoding.BCD, ContentType.NUMERIC));

        VariableLengthIndicatedField variableLengthIndicatedField = definition.getDataFieldBuilder().build("123456789");
        Assertions.assertEquals("0009", variableLengthIndicatedField.getVli().getData());
    }

    @Test
    void setBytes() {
        VariableLengthIndicatedFieldDefinition definition = new VariableLengthIndicatedFieldDefinition(
                FieldName.PRIMARY_ACCOUNT_NUMBER,
                Encoding.HEXADECIMAL_ASCII,
                ContentType.NUMERIC,
                new VariableLengthIndicatorDefinition(1, Encoding.BINARY, ContentType.BYTES));

        VariableLengthIndicatedField variableLengthIndicatedField = definition.getDataFieldBuilder().build("1234567890123456");
        Assertions.assertEquals("10", variableLengthIndicatedField.getVli().getData());
    }

    @Test
    void setBytesPadded() {
        VariableLengthIndicatedFieldDefinition definition = new VariableLengthIndicatedFieldDefinition(
                FieldName.PRIMARY_ACCOUNT_NUMBER,
                Encoding.HEXADECIMAL_ASCII,
                ContentType.NUMERIC,
                new VariableLengthIndicatorDefinition(2, Encoding.BINARY, ContentType.BYTES));

        VariableLengthIndicatedField variableLengthIndicatedField = definition.getDataFieldBuilder().build("1234567890123456");
        Assertions.assertEquals("0010", variableLengthIndicatedField.getVli().getData());
    }
}