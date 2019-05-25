package com.brandonlenz.iso8583.fields;

import com.brandonlenz.iso8583.structure.encoding.Encoding;

public class Bitmap {
    private final int startFieldIndex;
    private final int endFieldIndex;
    private final DataField bitmapField;

    public Bitmap(DataField dataField, int startFieldIndex) {
        this.bitmapField = dataField;
        this.startFieldIndex = startFieldIndex;
        this.endFieldIndex = startFieldIndex + (dataField.getDefinition().getByteLength() * 8) - 1;
    }

    public Bitmap(DataField dataField) {
        this(dataField, 1);
    }

    public DataField asDataField() {
        return this.bitmapField;
    }

    public int getStartFieldIndex() {
        return startFieldIndex;
    }

    public int getEndFieldIndex() {
        return endFieldIndex;
    }

    public void setBit(int dataFieldNumber) {
        if (!bitIsSet(dataFieldNumber)) {
            flipBit(dataFieldNumber);
        } else {
            throw new IllegalArgumentException(
                    "Bit corresponding to field number " + dataFieldNumber + " is already set");
        }
    }

    public void unsetBit(int dataFieldNumber) {
        if (bitIsSet(dataFieldNumber)) {
            flipBit(dataFieldNumber);
        } else {
            throw new IllegalArgumentException(
                    "Bit corresponding to field number " + dataFieldNumber + " is not yet set");
        }
    }

    public boolean bitIsSet(int dataFieldNumber) {
        if (bitmapField.getRawData() == null) {
            return  false;
        }

        int byteIndex = getByteIndex(dataFieldNumber);
        int bitIndex = getBitIndex(dataFieldNumber);

        return ((bitmapField.getRawData()[byteIndex] & 0xFF) & (1 << (bitIndex - 1))) >= 1;
    }

    private void flipBit(int dataFieldNumber) {
        int byteIndex = getByteIndex(dataFieldNumber);
        int bitIndex = getBitIndex(dataFieldNumber);

        bitmapField.getRawData()[byteIndex] =
                (byte) ((bitmapField.getRawData()[byteIndex] & 0xFF) ^ (1 << (bitIndex - 1)));
    }

    private int getByteIndex(int dataFieldNumber) {
        return ((dataFieldNumber - startFieldIndex)) / 8;
    }

    private int getBitIndex(int dataFieldNumber) {
        return 8 - (((dataFieldNumber - startFieldIndex)) % 8);
    }

    public String getBinaryRepresentation() {
        StringBuilder sb = new StringBuilder();
        for (byte bitmapByte : bitmapField.getRawData()) {
            sb.append(String.format("%8s", Integer.toBinaryString(bitmapByte & 0xFF)).replace(' ', '0'));
            sb.append(' ');
        }
        return sb.toString();
    }

    public String getHexRepresentation() {
        return Encoding.BINARY.decode(bitmapField.getRawData());
    }

    @Override
    public String toString() {
        return bitmapField.toString();
    }
}