package com.brandonlenz.generic.structure.encoding.handlers;

import java.nio.charset.Charset;

public class HexadecimalAsciiEncodingHandler implements EncodingHandler {

    private static final Charset HEXADECIMAL_ASCII_CHARSET = Charset.forName("UTF-8");

    @Override
    public byte[] encode(String data) {
        return data.getBytes(HEXADECIMAL_ASCII_CHARSET);
    }

    @Override
    public String decode(byte[] data) {
        return new String(data, HEXADECIMAL_ASCII_CHARSET);
    }
}
