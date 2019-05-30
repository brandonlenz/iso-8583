package com.brandonlenz.iso8583.structure.content.handlers;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class AlphanumericSpecialContentTypeHandlerTest {

    private AlphanumericSpecialContentTypeHandler contentTypeHandler = new AlphanumericSpecialContentTypeHandler();

    @Test
    void contentIsValid() {
        assertTrue(contentTypeHandler.contentIsValid("0123456789"));
        assertTrue(contentTypeHandler.contentIsValid("allLetters"));
        assertTrue(contentTypeHandler.contentIsValid("1234567890lettersAndNumbers1234567890"));
        assertTrue(contentTypeHandler.contentIsValid("!0@123$45*6789"));
        assertTrue(contentTypeHandler.contentIsValid("lettersAndSpecial$%^&"));
        assertTrue(contentTypeHandler.contentIsValid("1234567890 letters and numbers and special $^!@"));
        assertTrue(contentTypeHandler.contentIsValid("!@#$%^&*()"));
        assertTrue(contentTypeHandler.contentIsValid("spaces are special chars"));
        assertTrue(contentTypeHandler.contentIsValid("000102030405060708090A0B0C0D0E0F"));
        assertTrue(contentTypeHandler.contentIsValid("F0F1F2F3F4F5F6F7F8F9FAFBFCFDFEFF"));
    }

    @Test
    void contentIsInvalid() {
        //Everything is valid right now
    }
}