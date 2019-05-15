package com.brandonlenz.iso8583.structure.encoding.handlers;

import java.nio.charset.Charset;

public class HexadecimalEbcdicEncodingHandler implements EncodingHandler {

    private static final Charset HEXADECIMAL_EBCDIC_CHARSET = Charset.forName("Cp1047");

    @Override
    public byte[] encode(String data) {
        return data.getBytes(HEXADECIMAL_EBCDIC_CHARSET);
    }

    @Override
    public String decode(byte[] data) {
        return new String(data, HEXADECIMAL_EBCDIC_CHARSET);
    }

}