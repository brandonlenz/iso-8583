package com.brandonlenz.generic.structure.content;

import com.brandonlenz.generic.structure.content.handlers.AlphanumericContentTypeHandler;
import com.brandonlenz.generic.structure.content.handlers.AlphanumericSpecialContentTypeHandler;
import com.brandonlenz.generic.structure.content.handlers.BytesContentTypeHandler;
import com.brandonlenz.generic.structure.content.handlers.ContentTypeHandler;
import com.brandonlenz.generic.structure.content.handlers.NumericContentTypeHandler;
import com.brandonlenz.generic.structure.content.handlers.NumericSpecialContentTypeHandler;

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


