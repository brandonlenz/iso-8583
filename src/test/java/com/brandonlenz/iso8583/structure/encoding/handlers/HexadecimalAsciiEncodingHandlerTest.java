package com.brandonlenz.iso8583.structure.encoding.handlers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HexadecimalAsciiEncodingHandlerTest {

    private static final byte[] TEST_HEXADECIMAL_ASCII_BYTE_ARRAY = new byte[]{0x48, 0x65, 0x6C, 0x6C, 0x6F, 0x20, 0x57, 0x6f, 0x72, 0x6C, 0x64, 0x21};
    private static final String TEST_HEXADECIMAL_ASCII_STRING = "Hello World!";
    private final EncodingHandler encodingHandler = new HexadecimalAsciiEncodingHandler();

    @Test
    void encode() {
        Assertions.assertArrayEquals(encodingHandler.encode(TEST_HEXADECIMAL_ASCII_STRING),
                TEST_HEXADECIMAL_ASCII_BYTE_ARRAY);
    }

    @Test
    void decode() {
        Assertions
                .assertEquals(encodingHandler.decode(TEST_HEXADECIMAL_ASCII_BYTE_ARRAY), TEST_HEXADECIMAL_ASCII_STRING);
    }

}