package com.brandonlenz.iso8583.building.fields;

import com.brandonlenz.iso8583.definitions.fields.BitmapDefinition;
import com.brandonlenz.iso8583.definitions.names.FieldName;
import com.brandonlenz.iso8583.fields.Bitmap;
import com.brandonlenz.iso8583.structure.encoding.Encoding;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BitmapBuilderTest {

    private BitmapBuilder bitmapBuilder;
    private BitmapDefinition definition = new BitmapDefinition(FieldName.PRIMARY_BITMAP, 8, Encoding.BINARY);

    @BeforeEach
    void setUp() {
        this.bitmapBuilder = definition.getDataFieldBuilder();
    }

    @Test
    void createEmptyBitmap() {
        String emptyBitmapData = "00 00 00 00 00 00 00 00";
        Bitmap emptyBitmap = bitmapBuilder.setData(emptyBitmapData).build();

        Assertions.assertEquals(emptyBitmapData.replace(" ", ""), emptyBitmap.getData());
        Assertions.assertArrayEquals(definition.getEncoding().encode(emptyBitmapData), emptyBitmap.getRawData());
    }
}