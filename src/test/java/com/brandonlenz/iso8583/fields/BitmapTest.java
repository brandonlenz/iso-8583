package com.brandonlenz.iso8583.fields;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.brandonlenz.iso8583.building.DataFieldBuilder;
import com.brandonlenz.iso8583.definitions.fields.FieldDefinition;
import com.brandonlenz.iso8583.definitions.fields.FixedFieldDefinition;
import com.brandonlenz.iso8583.definitions.names.FieldName;
import com.brandonlenz.iso8583.structure.content.ContentType;
import com.brandonlenz.iso8583.structure.encoding.Encoding;
import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BitmapTest {
    private final FieldDefinition bitmapFieldDefinition =
            new FixedFieldDefinition(FieldName.PRIMARY_BITMAP,8, Encoding.BINARY, ContentType.BYTES);
    private Bitmap bitmap;

    @BeforeEach
    void setUp() {
        String emptyBitmapData = "0000000000000000";
        DataField bitmapField = new DataFieldBuilder(bitmapFieldDefinition, emptyBitmapData).getDataField();
        bitmap = new Bitmap(bitmapField);
    }

    @Test
    void getStartFieldIndex() {
        assertEquals(1, bitmap.getStartFieldIndex());
    }

    @Test
    void getEndFieldIndex() {
        assertEquals(64, bitmap.getEndFieldIndex());
    }

    @Test
    void setBitValidInputs() {
        bitmap.setBit(1);
        assertTrue(bitmap.bitIsSet(1));
        assertEquals((byte) 0b10000000, bitmap.asDataField().getRawData()[0]);

        bitmap.setBit(32);
        assertTrue(bitmap.bitIsSet(32));
        assertEquals((byte) 0b00000001, bitmap.asDataField().getRawData()[3]);

        bitmap.setBit(64);
        assertTrue(bitmap.bitIsSet(64));
        assertEquals((byte) 0b00000001, bitmap.asDataField().getRawData()[7]);
    }

    @Test
    void unsetBit() {
        bitmap.setBit(27);
        assertTrue(bitmap.bitIsSet(27));
        assertEquals((byte) 0b00100000, bitmap.asDataField().getRawData()[3]);

        bitmap.unsetBit(27);
        assertEquals((byte) 0b00000000, bitmap.asDataField().getRawData()[3]);
    }

    @Test
    void getSetBits() {
        Assertions.assertTrue(bitmap.getSetBits().isEmpty());

        bitmap.setBit(1);
        bitmap.setBit(8);
        bitmap.setBit(9);
        bitmap.setBit(64);
        Assertions.assertEquals(Arrays.asList(1, 8, 9, 64), bitmap.getSetBits());
    }

    @Test
    void getBinaryRepresentation() {
        assertEquals("00000000 00000000 00000000 00000000 00000000 00000000 00000000 00000000",
                bitmap.getBinaryRepresentation());

        bitmap.setBit(1);
        bitmap.setBit(20);
        bitmap.setBit(56);
        assertEquals("10000000 00000000 00010000 00000000 00000000 00000000 00000001 00000000",
                bitmap.getBinaryRepresentation());
    }

    @Test
    void getHexRepresentation() {
        assertEquals("0000000000000000",
                bitmap.getHexRepresentation());

        bitmap.setBit(1);
        bitmap.setBit(20);
        bitmap.setBit(56);
        assertEquals("8000100000000100",
                bitmap.getHexRepresentation());
    }
}