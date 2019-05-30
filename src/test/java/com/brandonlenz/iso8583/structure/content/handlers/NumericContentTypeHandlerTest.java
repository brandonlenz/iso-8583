package com.brandonlenz.iso8583.structure.content.handlers;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class NumericContentTypeHandlerTest {

    private NumericContentTypeHandler contentTypeHandler = new NumericContentTypeHandler();

    @Test
    void contentIsValid() {
        assertTrue(contentTypeHandler.contentIsValid("0123456789"));
    }

    @Test
    void contentIsInvalid() {
        assertFalse(contentTypeHandler.contentIsValid("all letters"));
        assertFalse(contentTypeHandler.contentIsValid("1234567890 letters and numbers 1234567890"));
        assertFalse(contentTypeHandler.contentIsValid("!@#$%^&*()"));
    }
}