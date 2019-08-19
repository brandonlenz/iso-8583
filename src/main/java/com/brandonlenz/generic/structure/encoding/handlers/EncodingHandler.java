package com.brandonlenz.generic.structure.encoding.handlers;

public interface EncodingHandler {

    byte[] encode(String data);

    default byte[] encode(int integer) {
        return encode(String.valueOf(integer));
    }

    default byte[] encode(int integer, int maximumBytes) {
        return encode(String.format("%0" + maximumBytes + "d", integer));
    }

    String decode(byte[] data);

    default int getByteLength(int contentLength) {
        return contentLength;
    }
}