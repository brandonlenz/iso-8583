package com.brandonlenz.iso8583.fields;

import com.brandonlenz.iso8583.fields.VliDefinition;
import com.brandonlenz.iso8583.fields.VliFieldDefinition;
import com.brandonlenz.iso8583.definitions.fields.names.FieldName;
import com.brandonlenz.iso8583.fields.VliField;
import com.brandonlenz.generic.structure.content.ContentType;
import com.brandonlenz.generic.structure.encoding.Encoding;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class VliFieldBuilderTest {

    @Test
    void setHex() {
        VliFieldDefinition definition = new VliFieldDefinition(
                FieldName.PRIMARY_ACCOUNT_NUMBER,
                Encoding.HEXADECIMAL_ASCII,
                ContentType.NUMERIC,
                new VliDefinition(2, Encoding.HEXADECIMAL_ASCII, ContentType.NUMERIC));

        VliField vliField = definition.getDataFieldBuilder().build("1234567890123456");
        Assertions.assertEquals("16", vliField.getVli().getData());
    }

    @Test
    void setHexPadded() {
        VliFieldDefinition definition = new VliFieldDefinition(
                FieldName.PRIMARY_ACCOUNT_NUMBER,
                Encoding.HEXADECIMAL_ASCII,
                ContentType.NUMERIC,
                new VliDefinition(3, Encoding.HEXADECIMAL_ASCII, ContentType.NUMERIC));

        VliField vliField = definition.getDataFieldBuilder().build("1234567890123456");
        Assertions.assertEquals("016", vliField.getVli().getData());
    }

    @Test
    void setBcd() {
        VliFieldDefinition definition = new VliFieldDefinition(
                FieldName.PRIMARY_ACCOUNT_NUMBER,
                Encoding.HEXADECIMAL_ASCII,
                ContentType.NUMERIC,
                new VliDefinition(1, Encoding.BCD, ContentType.NUMERIC));

        VliField vliField = definition.getDataFieldBuilder().build("123456789");
        Assertions.assertEquals("09", vliField.getVli().getData());
    }

    @Test
    void setBcdPadded() {
        VliFieldDefinition definition = new VliFieldDefinition(
                FieldName.PRIMARY_ACCOUNT_NUMBER,
                Encoding.HEXADECIMAL_ASCII,
                ContentType.NUMERIC,
                new VliDefinition(3, Encoding.BCD, ContentType.NUMERIC));

        VliField vliField = definition.getDataFieldBuilder().build("123456789");
        Assertions.assertEquals("0009", vliField.getVli().getData());
    }

    @Test
    void setBytes() {
        VliFieldDefinition definition = new VliFieldDefinition(
                FieldName.PRIMARY_ACCOUNT_NUMBER,
                Encoding.HEXADECIMAL_ASCII,
                ContentType.NUMERIC,
                new VliDefinition(1, Encoding.BINARY, ContentType.BYTES));

        VliField vliField = definition.getDataFieldBuilder().build("1234567890123456");
        Assertions.assertEquals("10", vliField.getVli().getData());
    }

    @Test
    void setBytesPadded() {
        VliFieldDefinition definition = new VliFieldDefinition(
                FieldName.PRIMARY_ACCOUNT_NUMBER,
                Encoding.HEXADECIMAL_ASCII,
                ContentType.NUMERIC,
                new VliDefinition(2, Encoding.BINARY, ContentType.BYTES));

        VliField vliField = definition.getDataFieldBuilder().build("1234567890123456");
        Assertions.assertEquals("0010", vliField.getVli().getData());
    }
}