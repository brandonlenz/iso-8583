package com.brandonlenz.iso8583.fields;

import com.brandonlenz.generic.structure.encoding.Encoding;
import java.util.ArrayList;
import java.util.List;

public class Bitmap extends AbstractFixedField {

    private final BitmapDefinition definition;
    private final int startFieldIndex;
    private final int endFieldIndex;

    public Bitmap(BitmapDefinition fieldDefinition) {
        super(fieldDefinition);
        this.definition = fieldDefinition;
        startFieldIndex = fieldDefinition.getStartFieldIndex();
        endFieldIndex = fieldDefinition.getEndFieldIndex();
    }

    @Override
    public BitmapDefinition getDefinition() {
        return definition;
    }

    //TODO: Delete these unused methods
    public int getStartFieldIndex() {
        return startFieldIndex;
    }

    public int getEndFieldIndex() {
        return endFieldIndex;
    }

    public void setBit(int dataFieldNumber) {
        if (dataFieldNumber < startFieldIndex || dataFieldNumber > endFieldIndex) {
            throw new IllegalArgumentException(
                    "Input bit " + dataFieldNumber + " must be between " + startFieldIndex + " and " + endFieldIndex);
        }

        if (!bitIsSet(dataFieldNumber)) {
            flipBit(dataFieldNumber);
        }
    }

    public void unsetBit(int dataFieldNumber) {
        if (dataFieldNumber < startFieldIndex || dataFieldNumber > endFieldIndex) {
            throw new IllegalArgumentException(
                    "Input bit " + dataFieldNumber + " must be between " + startFieldIndex + " and " + endFieldIndex);
        }

        if (bitIsSet(dataFieldNumber)) {
            flipBit(dataFieldNumber);
        } else {
            throw new IllegalArgumentException(
                    "Bit corresponding to field number " + dataFieldNumber + " is not yet set");
        }
    }

    public boolean bitIsSet(int dataFieldNumber) {
        if (getRawData() == null) {
            return false;
        }

        int byteIndex = getByteIndex(dataFieldNumber);
        int bitIndex = getBitIndex(dataFieldNumber);

        return ((getRawData()[byteIndex] & 0xFF) & (1 << (bitIndex - 1))) >= 1;
    }

    private void flipBit(int dataFieldNumber) {
        int byteIndex = getByteIndex(dataFieldNumber);
        int bitIndex = getBitIndex(dataFieldNumber);

        getRawData()[byteIndex] = (byte) ((getRawData()[byteIndex] & 0xFF) ^ (1 << (bitIndex - 1)));
    }

    private int getByteIndex(int dataFieldNumber) {
        return (dataFieldNumber - startFieldIndex) / 8;
    }

    private int getBitIndex(int dataFieldNumber) {
        return 8 - ((dataFieldNumber - startFieldIndex) % 8);
    }

    public boolean isEmpty() {
        for (byte b : getRawData()) {
            if ((b & 0xFF) != 0) {
                return false;
            }
        }
        return true;
    }

    public List<Integer> getSetBits() {
        List<Integer> bits = new ArrayList<>();
        byte[] bytes = getRawData();

        for (int byteIndex = 0; byteIndex < bytes.length; byteIndex++) {
            for (int bitIndex = 0; bitIndex < 8; bitIndex++) {
                byte mask = (byte) (0b10000000 >>> bitIndex);
                if (((bytes[byteIndex] & 0xFF) & mask) != 0) {
                    bits.add(startFieldIndex - 1 + getDataFieldNumber(byteIndex, bitIndex));
                }
            }
        }

        return bits;
    }

    private int getDataFieldNumber(int byteIndex, int bitIndex) {
        return (byteIndex * 8) + (bitIndex + 1);
    }

    public String getBinaryRepresentation() {
        StringBuilder sb = new StringBuilder();
        byte[] bitmapRawData = getRawData();

        for (int i = 0; i < bitmapRawData.length; i++) {
            sb.append(String.format("%8s", Integer.toBinaryString(bitmapRawData[i] & 0xFF)).replace(' ', '0'));
            if (i != bitmapRawData.length - 1) {
                sb.append(' ');
            }
        }

        return sb.toString();
    }

    public String getHexRepresentation() {
        return Encoding.BINARY.decode(getRawData());
    }

    @Override
    public String toString() {
        return getHexRepresentation();
    }

    public static Bitmap initializeEmptyBitmap(BitmapDefinition bitmapDefinition) {
        return bitmapDefinition.getDataFieldBuilder().build(new byte[bitmapDefinition.getByteLength()]);
    }

}
