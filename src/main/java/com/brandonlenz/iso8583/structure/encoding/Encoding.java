package com.brandonlenz.iso8583.structure.encoding;

import com.brandonlenz.iso8583.structure.encoding.handlers.BcdEncodingHandler;
import com.brandonlenz.iso8583.structure.encoding.handlers.BinaryEncodingHandler;
import com.brandonlenz.iso8583.structure.encoding.handlers.EncodingHandler;
import com.brandonlenz.iso8583.structure.encoding.handlers.HexadecimalAsciiEncodingHandler;
import com.brandonlenz.iso8583.structure.encoding.handlers.HexadecimalEbcdicEncodingHandler;

public enum Encoding {
    BCD(new BcdEncodingHandler()),
    BINARY(new BinaryEncodingHandler()),
    HEXADECIMAL_ASCII(new HexadecimalAsciiEncodingHandler()),
    HEXADECIMAL_EBCDIC(new HexadecimalEbcdicEncodingHandler());


    private final EncodingHandler encodingHandler;

    Encoding(EncodingHandler encodingHandler) {
        this.encodingHandler = encodingHandler;
    }

    public EncodingHandler getEncodingHandler() {
        return encodingHandler;
    }

    public byte[] encode(String data) {
        return this.encodingHandler.encode(data);
    }

    public String decode(byte[] rawData) {
        return this.encodingHandler.decode(rawData);
    }
}
