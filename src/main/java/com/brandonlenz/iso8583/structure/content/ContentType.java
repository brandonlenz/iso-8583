package com.brandonlenz.iso8583.structure.content;

import com.brandonlenz.iso8583.structure.content.handlers.AlphanumericContentTypeHandler;
import com.brandonlenz.iso8583.structure.content.handlers.AlphanumericSpecialContentTypeHandler;
import com.brandonlenz.iso8583.structure.content.handlers.BytesContentTypeHandler;
import com.brandonlenz.iso8583.structure.content.handlers.ContentTypeHandler;
import com.brandonlenz.iso8583.structure.content.handlers.NumericContentTypeHandler;
import com.brandonlenz.iso8583.structure.content.handlers.NumericSpecialContentTypeHandler;

public enum ContentType {
    ALPHANUMERIC(new AlphanumericContentTypeHandler()),
    ALPHANUMERIC_SPECIAL(new AlphanumericSpecialContentTypeHandler()),
    BYTES(new BytesContentTypeHandler()),
    NUMERIC(new NumericContentTypeHandler()),
    NUMERIC_SPECIAL(new NumericSpecialContentTypeHandler());

    private ContentTypeHandler contentTypeHandler;

    ContentType(ContentTypeHandler contentTypeHandler) {
        this.contentTypeHandler = contentTypeHandler;
    }

    public boolean isValid(String data) {
        return contentTypeHandler.contentIsValid(data);
    }
}


