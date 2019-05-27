package com.brandonlenz.iso8583.building.fields;

import com.brandonlenz.iso8583.definitions.fields.BitmapDefinition;
import com.brandonlenz.iso8583.fields.Bitmap;

public class BitmapBuilder extends DataFieldBuilder {

    private final BitmapDefinition fieldDefinition;
    private final Bitmap bitmap;

    public BitmapBuilder(BitmapDefinition fieldDefinition) {
        super(fieldDefinition);
        this.fieldDefinition = fieldDefinition;
        this.bitmap = new Bitmap(fieldDefinition);
    }

    @Override
    public Bitmap build() {
        return this.bitmap;
    }

    @Override
    public BitmapBuilder setRawData(byte[] rawData) {
        //TODO: Validator
        bitmap.setRawData(rawData);
        return this;
    }

    @Override
    public BitmapBuilder setData(String data) {
        setRawData(fieldDefinition.getEncoding().encode(data));
        return this;
    }
}