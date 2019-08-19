package com.brandonlenz.generic.structure.content.handlers;

public class NumericContentTypeHandler implements ContentTypeHandler {

    @Override
    public boolean contentIsValid(String data) {
        return data.matches("[0-9]*");
    }
}
