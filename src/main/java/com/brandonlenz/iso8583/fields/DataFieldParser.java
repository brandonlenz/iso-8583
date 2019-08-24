package com.brandonlenz.iso8583.fields;

import com.brandonlenz.iso8583.fields.DataField;
import java.io.IOException;
import java.io.InputStream;

public abstract class DataFieldParser<F extends DataField> {

    public abstract F parseFromStream(InputStream is);

    byte[] parseBytesFromStream(InputStream is, int bytesToRead) {
        byte[] bytes = new byte[bytesToRead];

        try {
            int bytesRead = is.read(bytes, 0, bytesToRead);
            if (bytesRead < bytesToRead) {
                throw new IllegalArgumentException("Prematurely reached end of message InputStream");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while trying to parse " + bytesToRead + " bytes from input stream.");
            e.printStackTrace();
        }

        return bytes;
    }
}
