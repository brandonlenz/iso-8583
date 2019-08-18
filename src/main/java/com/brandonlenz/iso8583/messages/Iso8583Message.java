package com.brandonlenz.iso8583.messages;

import com.brandonlenz.iso8583.definitions.messages.Iso8583MessageDefinition;
import com.brandonlenz.iso8583.fields.Bitmap;
import com.brandonlenz.iso8583.fields.DataField;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Iso8583Message implements Message {

    private final Iso8583MessageDefinition definition;
    private Bitmap primaryBitmap;
    private DataField messageTypeIndicator;
    private final Map<Integer, DataField> dataFields = new HashMap<>();

    public Iso8583Message(Iso8583MessageDefinition definition) {
        this.definition = definition;
        this.primaryBitmap = definition.getPrimaryBitmapDefinition().getDataFieldBuilder()
                .build(new byte[definition.getPrimaryBitmapDefinition().getLength()]);
    }

    @Override
    public byte[] getRawData() {
        try (ByteArrayOutputStream bytes = new ByteArrayOutputStream()) {
            bytes.write(messageTypeIndicator.getRawData());
            bytes.write(primaryBitmap.getRawData());
            for (Bitmap bitmap : getActiveBitmaps()) {
                for (int fieldIndex : bitmap.getSetBits()) {
                    bytes.write(dataFields.get(fieldIndex).getRawData());
                }
            }
            return (bytes).toByteArray();
        } catch (IOException e) {
            System.out.println("An error occurred while trying to get the raw message data: ");
            e.printStackTrace();
            return new byte[0];
        }
    }

    @Override
    public String getData() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(messageTypeIndicator.getData());
        stringBuilder.append(primaryBitmap.getData());
        for (Bitmap bitmap : getActiveBitmaps()) {
            for (int fieldIndex : bitmap.getSetBits()) {
                stringBuilder.append(dataFields.get(fieldIndex).getData());
            }
        }
        return stringBuilder.toString();
    }

    private List<Bitmap> getActiveBitmaps() {
        List<Bitmap> bitmaps = new ArrayList<>();

        bitmaps.add(primaryBitmap);
        if (secondaryBitmapBitIsSet()) {
            bitmaps.add(getSecondaryBitmap());
        }
        if (tertiaryBitmapBitIsSet()) {
            bitmaps.add(getTertiaryBitmap());
        }

        return bitmaps;
    }

    @Override
    public Iso8583MessageDefinition getDefinition() {
        return definition;
    }

    @Override
    public Map<Integer, DataField> getDataFields() {
        return dataFields;
    }

    public DataField getMessageTypeIndicator() {
        return messageTypeIndicator;
    }

    public void setMessageTypeIndicator(DataField messageTypeIndicator) {
        this.messageTypeIndicator = messageTypeIndicator;
    }

    public Bitmap getPrimaryBitmap() {
        return primaryBitmap;
    }

    public void setPrimaryBitmap(Bitmap primaryBitmap) {
        this.primaryBitmap = primaryBitmap;
    }

    public Bitmap getSecondaryBitmap() {
        return (Bitmap) getDataField(definition.getSecondaryBitmapFieldNumber());
    }

    public Bitmap getTertiaryBitmap() {
        return (Bitmap) getDataField(definition.getTertiaryBitmapFieldNumber());
    }

    public boolean secondaryBitmapBitIsSet() {
        return dataFieldBitIsSet(definition.getSecondaryBitmapFieldNumber());
    }

    public boolean tertiaryBitmapBitIsSet() {
        return dataFieldBitIsSet(definition.getTertiaryBitmapFieldNumber());
    }

    public boolean dataFieldBitIsSet(int dataFieldNumber) {
        Bitmap bitmap = getCorrespondingBitmap(dataFieldNumber);
        return bitmap !=null
                && bitmap.getRawData() != null && !bitmap.getData().isEmpty() && bitmap.bitIsSet(dataFieldNumber);
    }

    @Override
    public DataField getDataField(int dataFieldNumber) {
        return dataFields.get(dataFieldNumber);
    }

    public void setDataField(int dataFieldNumber, DataField dataField) {
        if (!definition.getFieldDefinitions().containsKey(dataFieldNumber)) {
            throw new IllegalArgumentException(
                    "DataField number " + dataFieldNumber + " is not defined for this Message");
        }


        //recursively set bitmaps as necessary
        if (primaryBitmap.governsBit(dataFieldNumber)) {
            primaryBitmap.setBit(dataFieldNumber);
        } else if (definition.getSecondaryBitmapDefinition().governsBit(dataFieldNumber)) {
            if (dataFields.containsKey(definition.getSecondaryBitmapFieldNumber())) {
                getSecondaryBitmap().setBit(dataFieldNumber);
            } else {
                Bitmap secondaryBitmap = definition.getSecondaryBitmapDefinition().getDataFieldBuilder()
                        .build(new byte[definition.getSecondaryBitmapDefinition().getByteLength()]);
                secondaryBitmap.setBit(dataFieldNumber);
                setDataField(definition.getSecondaryBitmapFieldNumber(), secondaryBitmap);
            }
        } else if (definition.getTertiaryBitmapDefinition().governsBit(dataFieldNumber)) {
            if (dataFields.containsKey(definition.getTertiaryBitmapFieldNumber())) {
                getTertiaryBitmap().setBit(dataFieldNumber);
            } else {
                Bitmap tertiaryBitmap = definition.getTertiaryBitmapDefinition().getDataFieldBuilder()
                        .build(new byte[definition.getTertiaryBitmapDefinition().getByteLength()]);
                tertiaryBitmap.setBit(dataFieldNumber);
                setDataField(definition.getTertiaryBitmapFieldNumber(), tertiaryBitmap);
            }
        } else {
            throw new IllegalArgumentException(
                    "DataField number " + dataFieldNumber + " is not governed by any defined bitmap");
        }

        dataFields.put(dataFieldNumber, dataField);
    }

    public void removeDataField(int dataFieldNumber) {
        if (!dataFields.containsKey(dataFieldNumber)) {
            throw new IllegalArgumentException(
                    "Message does not contain DataField number " + dataFieldNumber);
        }

        Bitmap bitmap = getCorrespondingBitmap(dataFieldNumber);
        dataFields.remove(dataFieldNumber);
        bitmap.unsetBit(dataFieldNumber);
        if (!bitmap.equals(primaryBitmap) && bitmap.getSetBits().isEmpty()) {
            Optional<Integer> bitmapFieldNumber = dataFields
                    .entrySet()
                    .stream()
                    .filter(e ->  bitmap.equals(e.getValue()))
                    .map(Map.Entry::getKey)
                    .findFirst();
            bitmapFieldNumber.ifPresent(this::removeDataField);
        }
    }

    /**
     * gets the bitmap that governs the bit for a given data field number
     *
     * @param dataFieldNumber the number of the data field to get the "parent" bitmap of
     * @return the "parent" bitmap
     */
    private Bitmap getCorrespondingBitmap(int dataFieldNumber) {
        if (dataFieldNumber < definition.getSecondaryBitmapDefinition().getStartFieldIndex()) {
            return getPrimaryBitmap();
        } else if (dataFieldNumber < definition.getTertiaryBitmapDefinition().getStartFieldIndex()) {
            return getSecondaryBitmap();
        } else if (dataFieldNumber <= definition.getTertiaryBitmapDefinition().getEndFieldIndex()) {
            return getTertiaryBitmap();
        } else {
            throw new IllegalArgumentException("ISO8583Messages do not support field number: " + dataFieldNumber);
        }
    }

    @Override
    public String toString() {
        return getData();
    }
}