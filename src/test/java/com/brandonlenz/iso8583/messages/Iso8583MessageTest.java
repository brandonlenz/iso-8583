package com.brandonlenz.iso8583.messages;

import com.brandonlenz.iso8583.definitions.messages.SampleIso8583MessageDefinition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Iso8583MessageTest {

    private Iso8583Message iso8583Message;

    @BeforeEach
    void setUp() {
        this.iso8583Message = new Iso8583Message(new SampleIso8583MessageDefinition());
    }

    @Test
    void setAndUnsetBitmapBits() {
        iso8583Message.setBitmapBit(1);
        Assertions.assertEquals((byte) 0b10000000, iso8583Message.getPrimaryBitmap().getRawData()[0]);

        iso8583Message.setBitmapBit(2);
        Assertions.assertEquals((byte) 0b11000000, iso8583Message.getPrimaryBitmap().getRawData()[0]);

        iso8583Message.setBitmapBit(24);
        Assertions.assertEquals((byte) 0b00000001, iso8583Message.getPrimaryBitmap().getRawData()[2]);

        iso8583Message.setBitmapBit(25);
        Assertions.assertEquals((byte) 0b10000000, iso8583Message.getPrimaryBitmap().getRawData()[3]);

        iso8583Message.setBitmapBit(64);
        Assertions.assertEquals((byte) 0b00000001, iso8583Message.getPrimaryBitmap().getRawData()[7]);

        for (byte bitmapByte :
                iso8583Message.getPrimaryBitmap().getRawData()) {
            System.out.print(String.format("%8s", Integer.toBinaryString(bitmapByte & 0xFF)).replace(' ', '0')  + " ");
        }
        System.out.println();

        // raw bitmap =  11000000 00000000 00000001 00000000 00000000 00000000 00000000 00000001
        //               12345678 12345678 12345678 12345678 12345678 12345678 12345678 12345678
        //               1        2        3        4        5        6        7        8


        iso8583Message.unsetBitmapBit(1);
        Assertions.assertEquals((byte) 0b01000000, iso8583Message.getPrimaryBitmap().getRawData()[0]);

        iso8583Message.unsetBitmapBit(2);
        Assertions.assertEquals((byte) 0b00000000, iso8583Message.getPrimaryBitmap().getRawData()[0]);

        iso8583Message.unsetBitmapBit(24);
        Assertions.assertEquals((byte) 0b00000000, iso8583Message.getPrimaryBitmap().getRawData()[2]);

        iso8583Message.unsetBitmapBit(25);
        Assertions.assertEquals((byte) 0b00000000, iso8583Message.getPrimaryBitmap().getRawData()[3]);

        iso8583Message.unsetBitmapBit(64);
        Assertions.assertEquals((byte) 0b00000000, iso8583Message.getPrimaryBitmap().getRawData()[7]);

        for (byte bitmapByte :
                iso8583Message.getPrimaryBitmap().getRawData()) {
            System.out.print(String.format("%8s", Integer.toBinaryString(bitmapByte & 0xFF)).replace(' ', '0')  + " ");
        }
        System.out.println();
    }
}