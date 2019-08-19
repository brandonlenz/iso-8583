package com.brandonlenz.generic.structure.content.handlers;

public class AlphanumericSpecialContentTypeHandler  implements ContentTypeHandler {

    @Override
    public boolean contentIsValid(String data) {
        return true; //currently accepts all characters
    }
}
