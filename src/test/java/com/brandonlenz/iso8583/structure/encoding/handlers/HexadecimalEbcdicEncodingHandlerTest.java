package com.brandonlenz.iso8583.structure.encoding.handlers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HexadecimalEbcdicEncodingHandlerTest {

    private static final byte[] TEST_HEXADECIMAL_EBCDIC_BYTE_ARRAY = new byte[] {(byte) 0xC8, (byte) 0x85, (byte) 0x93, (byte) 0x93, (byte) 0x96, 0x40, (byte) 0xE6, (byte) 0x96, (byte) 0x99, (byte) 0x93, (byte) 0x84, 0x5A};
    private static final String TEST_HEXADECIMAL_EBCDIC_STRING = "Hello World!";
    private final EncodingHandler encodingHandler = new HexadecimalEbcdicEncodingHandler();

    @Test
    void encode() {
        Assertions.assertArrayEquals(encodingHandler.encode(TEST_HEXADECIMAL_EBCDIC_STRING),
                TEST_HEXADECIMAL_EBCDIC_BYTE_ARRAY);
    }

    @Test
    void decode() {
        Assertions.assertEquals(encodingHandler.decode(TEST_HEXADECIMAL_EBCDIC_BYTE_ARRAY),
                TEST_HEXADECIMAL_EBCDIC_STRING);
    }

}