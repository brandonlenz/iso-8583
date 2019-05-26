package com.brandonlenz.iso8583.structure.encoding.handlers;

import javax.xml.bind.DatatypeConverter;

public class BinaryEncodingHandler implements EncodingHandler {

    @Override
    public byte[] encode(String data) {
        data = data.replace(" ", "");
        return DatatypeConverter.parseHexBinary(data);
    }

    @Override
    public String decode(byte[] data) {
        return DatatypeConverter.printHexBinary(data);
    }

}
