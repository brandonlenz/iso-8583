package com.brandonlenz.iso8583.building;

import com.brandonlenz.iso8583.definitions.messages.SampleIso8583MessageDefinition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Iso8583MessageBuilderTest {

    private Iso8583MessageBuilder iso8583MessageBuilder;
    private static final String TEST_AMOUNT_DATA = "000000001000";
    private static final byte[] TEST_AMOUNT_RAW_DATA = new byte[] {0x30, 0x30, 0x30, 0x30, 0x30, 0x30, 0x30, 0x30, 0x31, 0x30, 0x30, 0x30};

    @BeforeEach
    void setUp() {
        this.iso8583MessageBuilder = new Iso8583MessageBuilder(new SampleIso8583MessageDefinition());
    }

    @Test
    void setFieldHex() {
        iso8583MessageBuilder.setField(4, TEST_AMOUNT_RAW_DATA);

        Assertions.assertArrayEquals(iso8583MessageBuilder.getIso8583Message().getDataField(4).getRawData(), TEST_AMOUNT_RAW_DATA);
        Assertions.assertEquals(iso8583MessageBuilder.getIso8583Message().getDataField(4).getData(), TEST_AMOUNT_DATA);
    }

    @Test
    void setFieldString() {
        iso8583MessageBuilder.setField(4, TEST_AMOUNT_DATA);

        Assertions.assertArrayEquals(iso8583MessageBuilder.getIso8583Message().getDataField(4).getRawData(), TEST_AMOUNT_RAW_DATA);
        Assertions.assertEquals(iso8583MessageBuilder.getIso8583Message().getDataField(4).getData(), TEST_AMOUNT_DATA);
    }

    @Test
    void removeField() {
        iso8583MessageBuilder.setField(4, TEST_AMOUNT_RAW_DATA);
        iso8583MessageBuilder.removeField(4);

        Assertions.assertNull(iso8583MessageBuilder.getIso8583Message().getRawData());
    }
}