package com.brandonlenz.iso8583.structure.content.handlers;

public class NumericContentTypeHandler implements ContentTypeHandler {

    @Override
    public boolean contentIsValid(String data) {
        return data.matches("[0-9]*");
    }
}
