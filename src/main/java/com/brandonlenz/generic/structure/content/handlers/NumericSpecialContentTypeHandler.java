package com.brandonlenz.generic.structure.content.handlers;

import java.util.regex.Pattern;

public class NumericSpecialContentTypeHandler implements ContentTypeHandler {

    @Override
    public boolean contentIsValid(String data) {
        return Pattern.compile("[0-9]|[^a-z]*", Pattern.CASE_INSENSITIVE)
                .matcher(data)
                .matches();
    }
}