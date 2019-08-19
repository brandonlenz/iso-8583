package com.brandonlenz.iso8583.messages;

import com.brandonlenz.iso8583.definitions.messages.Iso8583MessageDefinition;
import com.brandonlenz.iso8583.definitions.messages.SampleIso8583MessageDefinition;
import com.brandonlenz.iso8583.fields.DataField;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Iso8583MessageBuilderTest {

    private static final Iso8583MessageDefinition messageDefinition = new SampleIso8583MessageDefinition();
    private static final int SECONDARY_BITMAP_FIELD_NUMBER = messageDefinition.getSecondaryBitmapFieldNumber();
    private static final int AMOUNT_FIELD_NUMBER = 4;
    private static final String AMOUNT_FIELD_DATA = "000000010000";
    private static final byte[] AMOUNT_FIELD_RAW_DATA =
            new byte[]{0x30, 0x30, 0x30, 0x30, 0x30, 0x30, 0x30, 0x31, 0x30, 0x30, 0x30, 0x30};
    private static final int SETTLEMENT_CODE_FIELD_NUMBER = 66;
    private static final String SETTLEMENT_CODE_FIELD_DATA = "1";
    private final DataField messageTypeIndicator = messageDefinition.getMessageTypeIndicatorDefinition()
            .getDataFieldBuilder()
            .build("0100");
    private Iso8583MessageBuilder iso8583MessageBuilder;

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

        Assertions.assertTrue(iso8583Message.dataFieldIsSet(SECONDARY_BITMAP_FIELD_NUMBER));
        Assertions.assertTrue(iso8583Message.dataFieldBitIsSet(SECONDARY_BITMAP_FIELD_NUMBER));
        Assertions.assertTrue(iso8583Message.dataFieldIsSet(SETTLEMENT_CODE_FIELD_NUMBER));
        Assertions.assertTrue(iso8583Message.dataFieldBitIsSet(SETTLEMENT_CODE_FIELD_NUMBER));
    }

    @Test
    void removeFieldGovernedBySecondaryBitmap() {
        Iso8583Message iso8583Message = iso8583MessageBuilder
                .setField(SETTLEMENT_CODE_FIELD_NUMBER, SETTLEMENT_CODE_FIELD_DATA)
                .removeField(SETTLEMENT_CODE_FIELD_NUMBER)
                .build();

        Assertions.assertFalse(iso8583Message.dataFieldIsSet(SECONDARY_BITMAP_FIELD_NUMBER));
        Assertions.assertFalse(iso8583Message.dataFieldBitIsSet(SECONDARY_BITMAP_FIELD_NUMBER));
        Assertions.assertFalse(iso8583Message.dataFieldIsSet(SETTLEMENT_CODE_FIELD_NUMBER));
        Assertions.assertFalse(iso8583Message.dataFieldBitIsSet(SETTLEMENT_CODE_FIELD_NUMBER));
    }
}