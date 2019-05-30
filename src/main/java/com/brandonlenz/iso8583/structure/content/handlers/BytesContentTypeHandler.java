package com.brandonlenz.iso8583.structure.content.handlers;

import java.util.regex.Pattern;

public class BytesContentTypeHandler implements ContentTypeHandler {

    @Override
    public boolean contentIsValid(String data) {
        return Pattern.compile("[0-9A-F]*")
                .matcher(data)
                .matches();
    }
}
