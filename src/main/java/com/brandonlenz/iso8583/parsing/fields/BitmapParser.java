package com.brandonlenz.iso8583.parsing.fields;

import com.brandonlenz.iso8583.definitions.fields.BitmapDefinition;
import com.brandonlenz.iso8583.fields.Bitmap;
import java.io.InputStream;

public class BitmapParser extends DataFieldParser<Bitmap> {
    private final BitmapDefinition fieldDefinition;

    public BitmapParser(BitmapDefinition fieldDefinition) {
        this.fieldDefinition = fieldDefinition;
    }

    @Override
    public Bitmap parseFromStream(InputStream is) {
        int bytesToRead = fieldDefinition.getByteLength();
        byte[] rawData = parseBytesFromStream(is, bytesToRead);
        return fieldDefinition.getDataFieldBuilder()
                .build(rawData);
    }
}