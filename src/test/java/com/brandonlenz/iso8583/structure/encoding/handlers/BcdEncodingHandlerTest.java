package com.brandonlenz.iso8583.structure.encoding.handlers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BcdEncodingHandlerTest {
    private static final byte[] TEST_BCD_BYTE_ARRAY = new byte[] {0x01, 0x23};
    private final EncodingHandler encodingHandler = new BcdEncodingHandler();

    @Test
    void encodePadded() {
        Assertions.assertArrayEquals(TEST_BCD_BYTE_ARRAY, encodingHandler.encode("0123"));
    }

    @Test
    void encodeAddPadding() {
        Assertions.assertArrayEquals(TEST_BCD_BYTE_ARRAY, encodingHandler.encode("123"));
    }

    @Test
    void decode() {
        Assertions.assertEquals("0123", encodingHandler.decode(TEST_BCD_BYTE_ARRAY));
    }
}