package com.brandonlenz.generic.structure.encoding.handlers;

import javax.xml.bind.DatatypeConverter;

public class BcdEncodingHandler implements EncodingHandler {

    @Override
    public byte[] encode(String data) {
        data = data.replace(" ", "");

        if (data.length() % 2 != 0) {
            data = "0" + data;
        }

        return DatatypeConverter.parseHexBinary(data);
    }

    @Override
    public byte[] encode(int integer, int maximumBytes) {
        return this.encode(String.format("%0" + maximumBytes * 2 + "d", integer));
    }

    @Override
    public String decode(byte[] data) {
        return DatatypeConverter.printHexBinary(data);
    }

    @Override
    public int getByteLength(int contentLength) {
        return contentLength % 2 == 0 ? contentLength / 2 : (contentLength / 2) + 1;
    }
}
