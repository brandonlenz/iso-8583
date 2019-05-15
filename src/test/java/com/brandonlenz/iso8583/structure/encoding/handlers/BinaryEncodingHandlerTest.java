package com.brandonlenz.iso8583.structure.encoding.handlers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BinaryEncodingHandlerTest {

    private static final byte[] TEST_BINARY_BYTE_ARRAY = new byte[]{0x00, 0x01, 0x0E, 0x0F, 0x10, 0x15, 0x1A, 0x1E,
            0x1A, (byte) 0xFF};
    private static final String TEST_BINARY_STRING = "00010E0F10151A1E1AFF";
    private static final String TEST_BINARY_STRING_WITH_SPACES = "00 01 0E 0F 10 15 1A 1E 1A FF";
    private final EncodingHandler encodingHandler = new BinaryEncodingHandler();

    @Test
    void encode() {
        Assertions.assertArrayEquals(TEST_BINARY_BYTE_ARRAY, encodingHandler.encode(TEST_BINARY_STRING));
    }

    @Test
    void encodeWithSpaces() {
        Assertions.assertArrayEquals(TEST_BINARY_BYTE_ARRAY, encodingHandler.encode(TEST_BINARY_STRING_WITH_SPACES));
    }

    @Test
    void decode() {
        Assertions.assertEquals(TEST_BINARY_STRING, encodingHandler.decode(TEST_BINARY_BYTE_ARRAY));
    }
}