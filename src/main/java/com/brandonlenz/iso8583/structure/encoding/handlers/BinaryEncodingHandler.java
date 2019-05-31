package com.brandonlenz.iso8583.structure.encoding.handlers;

import javax.xml.bind.DatatypeConverter;

public class BinaryEncodingHandler implements EncodingHandler {

    @Override
    public byte[] encode(String data) {
        data = data.replace(" ", "");
        return DatatypeConverter.parseHexBinary(data);
    }

    @Override
    public byte[] encode(int integer) {
        return this.encode(Integer.toHexString(integer));
    }

    @Override
    public byte[] encode(int integer, int maximumBytes) {
        return this.encode(String.format("%0" + maximumBytes * 2 + "x", integer));
    }

    @Override
    public String decode(byte[] data) {
        return DatatypeConverter.printHexBinary(data);
    }

}
