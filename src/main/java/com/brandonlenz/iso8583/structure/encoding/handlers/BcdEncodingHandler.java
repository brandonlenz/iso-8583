package com.brandonlenz.iso8583.structure.encoding.handlers;

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
    public String decode(byte[] data) {
        return DatatypeConverter.printHexBinary(data);
    }

}
