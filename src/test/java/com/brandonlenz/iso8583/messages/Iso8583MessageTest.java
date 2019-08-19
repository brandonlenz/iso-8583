package com.brandonlenz.iso8583.messages;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.brandonlenz.iso8583.definitions.messages.Iso8583MessageDefinition;
import com.brandonlenz.iso8583.definitions.messages.SampleIso8583MessageDefinition;
import com.brandonlenz.iso8583.fields.DataField;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Iso8583MessageTest {

    private static final int SECONDARY_BITMAP_FIELD_NUMBER = 1;
    private static final int PAN_FIELD_NUMBER = 2;
    private static final String PAN_FIELD_DATA = "1234567890123456";
    private static final int AMOUNT_FIELD_NUMBER = 4;
    private static final String AMOUNT_FIELD_DATA = "000000010000";
    private static final byte[] AMOUNT_FIELD_RAW_DATA =
            new byte[]{0x30, 0x30, 0x30, 0x30, 0x30, 0x30, 0x30, 0x31, 0x30, 0x30, 0x30, 0x30};
    private static final int TIMESTAMP_FIELD_NUMBER = 7;
    private static final String TIMESTAMP_FIELD_DATA = "1561507200";
    private static final int PSN_FIELD_NUMBER = 23;
    private static final String PSN_FIELD_DATA = "052";
    private static final int ICC_DATA_FIELD_NUMBER = 55;
    private static final String ICC_DATA_FIELD_DATA = "9F260801020304050607089F270180";
    private static final int SETTLEMENT_CODE_FIELD_NUMBER = 66;
    private static final String SETTLEMENT_CODE_FIELD_DATA = "1";
    private final Iso8583MessageDefinition messageDefinition = new SampleIso8583MessageDefinition();
    private final DataField messageTypeIndicator = messageDefinition.getMessageTypeIndicatorDefinition()
            .getDataFieldBuilder()
            .build("0100");
    private Iso8583MessageBuilder iso8583MessageBuilder;
    private final Iso8583Message iso8583Message = getTestMessage();

    private Iso8583Message getTestMessage() {
        return Iso8583Message
                .builder(messageDefinition, messageTypeIndicator)
                .setField(PAN_FIELD_NUMBER, PAN_FIELD_DATA)
                .setField(AMOUNT_FIELD_NUMBER, AMOUNT_FIELD_DATA)
                .setField(TIMESTAMP_FIELD_NUMBER, TIMESTAMP_FIELD_DATA)
                .setField(PSN_FIELD_NUMBER, PSN_FIELD_DATA)
                .setField(ICC_DATA_FIELD_NUMBER, ICC_DATA_FIELD_DATA)
                .setField(SETTLEMENT_CODE_FIELD_NUMBER, SETTLEMENT_CODE_FIELD_DATA)
                .build();
    }

    @BeforeEach
    void setUp() {
        this.iso8583MessageBuilder = Iso8583Message.builder(messageDefinition, messageTypeIndicator);
    }

    @Test
    void setFieldString() {
        Iso8583Message iso8583Message = iso8583MessageBuilder
                .setField(AMOUNT_FIELD_NUMBER, AMOUNT_FIELD_DATA)
                .build();

        Assertions.assertTrue(iso8583Message.dataFieldIsSet(AMOUNT_FIELD_NUMBER));
        Assertions.assertTrue(iso8583Message.dataFieldBitIsSet(AMOUNT_FIELD_NUMBER));
    }

    @Test
    void setFieldBytes() {
        Iso8583Message iso8583Message = iso8583MessageBuilder
                .setField(AMOUNT_FIELD_NUMBER, AMOUNT_FIELD_RAW_DATA)
                .build();

        Assertions.assertTrue(iso8583Message.dataFieldIsSet(AMOUNT_FIELD_NUMBER));
        Assertions.assertTrue(iso8583Message.dataFieldBitIsSet(AMOUNT_FIELD_NUMBER));
    }

    @Test
    void removeField() {
        Iso8583Message iso8583Message = iso8583MessageBuilder
                .setField(AMOUNT_FIELD_NUMBER, AMOUNT_FIELD_DATA)
                .removeField(AMOUNT_FIELD_NUMBER)
                .build();

        Assertions.assertFalse(iso8583Message.dataFieldIsSet(AMOUNT_FIELD_NUMBER));
        Assertions.assertFalse(iso8583Message.dataFieldBitIsSet(AMOUNT_FIELD_NUMBER));
    }

    @Test
    void setFieldGovernedBySecondaryBitmap() {
        Iso8583Message iso8583Message = iso8583MessageBuilder
                .setField(SETTLEMENT_CODE_FIELD_NUMBER, SETTLEMENT_CODE_FIELD_DATA)
                .build();

        Assertions.assertTrue(iso8583Message.dataFieldIsSet(SETTLEMENT_CODE_FIELD_NUMBER));
        Assertions.assertTrue(iso8583Message.dataFieldBitIsSet(SETTLEMENT_CODE_FIELD_NUMBER));
        Assertions.assertTrue(iso8583Message.dataFieldIsSet(messageDefinition.getSecondaryBitmapFieldNumber()));
        Assertions.assertTrue(iso8583Message.dataFieldBitIsSet(messageDefinition.getSecondaryBitmapFieldNumber()));
    }

    @Test
    void removeFieldGovernedBySecondaryBitmap() {
        Iso8583Message iso8583Message = iso8583MessageBuilder
                .setField(SETTLEMENT_CODE_FIELD_NUMBER, SETTLEMENT_CODE_FIELD_DATA)
                .removeField(SETTLEMENT_CODE_FIELD_NUMBER)
                .build();

        Assertions.assertFalse(iso8583Message.dataFieldIsSet(SETTLEMENT_CODE_FIELD_NUMBER));
        Assertions.assertFalse(iso8583Message.dataFieldIsSet(messageDefinition.getSecondaryBitmapFieldNumber()));
        Assertions.assertFalse(iso8583Message.dataFieldBitIsSet(SETTLEMENT_CODE_FIELD_NUMBER));
        Assertions.assertFalse(iso8583Message.dataFieldBitIsSet(messageDefinition.getSecondaryBitmapFieldNumber()));
    }

    @Test
    void messageTypeIndicatorValid() {
        assertEquals("0100", iso8583Message.getMessageTypeIndicator().getData());
    }

    @Test
    void primaryBitmapValid() {
        assertEquals("D200020000000200", iso8583Message.getPrimaryBitmap().getData());
    }

    @Test
    void secondaryBitmapValid() {
        assertTrue(iso8583Message.dataFieldBitIsSet(SECONDARY_BITMAP_FIELD_NUMBER));
        assertEquals("4000000000000000", iso8583Message.getDataField(SECONDARY_BITMAP_FIELD_NUMBER).getData());
    }

    @Test
    void primaryAccountNumberValid() {
        assertTrue(iso8583Message.dataFieldBitIsSet(PAN_FIELD_NUMBER));
        String expectedPanData = "16" + PAN_FIELD_DATA;
        assertEquals(expectedPanData, iso8583Message.getDataField(PAN_FIELD_NUMBER).getData());
    }

    @Test
    void amountValid() {
        assertTrue(iso8583Message.dataFieldBitIsSet(AMOUNT_FIELD_NUMBER));
        assertEquals(AMOUNT_FIELD_DATA, iso8583Message.getDataField(AMOUNT_FIELD_NUMBER).getData());
    }

    @Test
    void tiemstampValid() {
        assertTrue(iso8583Message.dataFieldBitIsSet(TIMESTAMP_FIELD_NUMBER));
        assertEquals(TIMESTAMP_FIELD_DATA, iso8583Message.getDataField(TIMESTAMP_FIELD_NUMBER).getData());
    }

    @Test
    void panSequenceNumberValid() {
        assertTrue(iso8583Message.dataFieldBitIsSet(PSN_FIELD_NUMBER));
        assertEquals(PSN_FIELD_DATA, iso8583Message.getDataField(PSN_FIELD_NUMBER).getData());
    }

    @Test
    void iccDataValid() {
        assertTrue(iso8583Message.dataFieldBitIsSet(ICC_DATA_FIELD_NUMBER));
        String expectedIccData = "015" + ICC_DATA_FIELD_DATA;
        assertEquals(expectedIccData, iso8583Message.getDataField(ICC_DATA_FIELD_NUMBER).getData());
    }

    @Test
    void settlementCodeValid() {
        assertTrue(iso8583Message.dataFieldBitIsSet(SETTLEMENT_CODE_FIELD_NUMBER));
        assertEquals(SETTLEMENT_CODE_FIELD_DATA, iso8583Message.getDataField(SETTLEMENT_CODE_FIELD_NUMBER).getData());
    }
}