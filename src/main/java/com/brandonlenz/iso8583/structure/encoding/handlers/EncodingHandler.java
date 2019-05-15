package com.brandonlenz.iso8583.structure.encoding.handlers;

public interface EncodingHandler {
    byte[] encode(String data);
    String decode(byte[] data);
}
