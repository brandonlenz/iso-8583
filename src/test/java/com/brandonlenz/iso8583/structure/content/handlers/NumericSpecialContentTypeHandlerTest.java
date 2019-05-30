package com.brandonlenz.iso8583.structure.content.handlers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NumericSpecialContentTypeHandlerTest {

    private NumericSpecialContentTypeHandler contentTypeHandler = new NumericSpecialContentTypeHandler();

    @Test
    void contentIsValid() {
        assertTrue(contentTypeHandler.contentIsValid("!0@ 1 2 3$4 5* 67 89"));
        assertTrue(contentTypeHandler.contentIsValid("!@#$%^&*()"));
        assertTrue(contentTypeHandler.contentIsValid("0123456789"));
        assertTrue(contentTypeHandler.contentIsValid("0 1 2 3 4 5 6 7 8 9"));
    }

    @Test
    void contentIsInvalid() {
        assertFalse(contentTypeHandler.contentIsValid("lettersAndSpecial$%^&"));
        assertFalse(contentTypeHandler.contentIsValid("1234567890 letters and numbers and special $^!@"));
        assertFalse(contentTypeHandler.contentIsValid("spaces are special chars"));
    }
}