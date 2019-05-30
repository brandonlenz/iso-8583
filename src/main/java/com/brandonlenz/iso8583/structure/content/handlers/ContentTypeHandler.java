package com.brandonlenz.iso8583.structure.content.handlers;

public interface ContentTypeHandler {
    boolean contentIsValid(String data);
}
