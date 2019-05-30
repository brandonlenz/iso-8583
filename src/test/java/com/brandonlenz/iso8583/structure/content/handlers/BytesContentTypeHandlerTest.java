package com.brandonlenz.iso8583.structure.content.handlers;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class BytesContentTypeHandlerTest {

    private BytesContentTypeHandler contentTypeHandler = new BytesContentTypeHandler();

    @Test
    void contentIsValid() {
        assertTrue(contentTypeHandler.contentIsValid("000102030405060708090A0B0C0D0E0F"));
        assertTrue(contentTypeHandler.contentIsValid("101112131415161718191A1B1C1D1E1F"));
        assertTrue(contentTypeHandler.contentIsValid("202122232425262728292A2B2C2D2E2F"));
        assertTrue(contentTypeHandler.contentIsValid("303132333435363738393A3B3C3D3E3F"));
        assertTrue(contentTypeHandler.contentIsValid("404142434445464748494A4B4C4D4E4F"));
        assertTrue(contentTypeHandler.contentIsValid("505152535455565758595A5B5C5D5E5F"));
        assertTrue(contentTypeHandler.contentIsValid("606162636465666768696A6B6C6D6E6F"));
        assertTrue(contentTypeHandler.contentIsValid("707172737475767778797A7B7C7D7E7F"));
        assertTrue(contentTypeHandler.contentIsValid("808182838485868788898A8B8C8D8E8F"));
        assertTrue(contentTypeHandler.contentIsValid("909192939495969798999A9B9C9D9E9F"));
        assertTrue(contentTypeHandler.contentIsValid("A0A1A2A3A4A5A6A7A8A9AAABACADAEAF"));
        assertTrue(contentTypeHandler.contentIsValid("B0B1B2B3B4B5B6B7B8B9BABBBCBDBEBF"));
        assertTrue(contentTypeHandler.contentIsValid("C0C1C2C3C4C5C6C7C8C9CACBCCCDCECF"));
        assertTrue(contentTypeHandler.contentIsValid("D0D1D2D3D4D5D6D7D8D9DADBDCDDDEDF"));
        assertTrue(contentTypeHandler.contentIsValid("E0E1E2E3E4E5E6E7E8E9EAEBECEDEEEF"));
        assertTrue(contentTypeHandler.contentIsValid("F0F1F2F3F4F5F6F7F8F9FAFBFCFDFEFF"));
    }

    @Test
    void contentIsInvalid() {
        assertFalse(contentTypeHandler.contentIsValid("!0@123$45*6789"));
        assertFalse(contentTypeHandler.contentIsValid("lettersAndSpecial$%^&"));
        assertFalse(contentTypeHandler.contentIsValid("1234567890 letters and numbers and special $^!@"));
        assertFalse(contentTypeHandler.contentIsValid("!@#$%^&*()"));
        assertFalse(contentTypeHandler.contentIsValid("spaces are special chars"));
    }
}