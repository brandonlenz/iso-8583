package com.brandonlenz.iso8583.structure.content.handlers;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class AlphanumericContentTypeHandlerTest {

    private AlphanumericContentTypeHandler contentTypeHandler = new AlphanumericContentTypeHandler();

    @Test
    void contentIsValid() {
        assertTrue(contentTypeHandler.contentIsValid("0123456789"));
        assertTrue(contentTypeHandler.contentIsValid("allLetters"));
        assertTrue(contentTypeHandler.contentIsValid("1234567890lettersAndNumbers1234567890"));
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