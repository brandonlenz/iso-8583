package com.brandonlenz.iso8583.definitions.fields;

import com.brandonlenz.iso8583.building.fields.DataFieldBuilder;
import com.brandonlenz.iso8583.definitions.fields.names.FieldName;
import com.brandonlenz.iso8583.fields.Bitmap;
import com.brandonlenz.iso8583.parsing.fields.BitmapParser;
import com.brandonlenz.generic.structure.content.ContentType;
import com.brandonlenz.generic.structure.encoding.Encoding;
import com.brandonlenz.generic.structure.format.Format;
import java.util.ArrayList;

public final class BitmapDefinition extends FieldDefinition<Bitmap> {

    private static final int DEFAULT_START_INDEX = 1;
    private final int startFieldIndex;
    private final int endFieldIndex;
    private int length;

    public BitmapDefinition(FieldName name, int length, Encoding encoding, int startFieldIndex) {
        super(name, Format.FIXED, encoding, ContentType.BYTES, new ArrayList<>());
        this.length = length;
        this.startFieldIndex = startFieldIndex;
        this.endFieldIndex = startFieldIndex + (getByteLength() * 8) - 1;
    }

    public BitmapDefinition(FieldName name, int length, Encoding encoding) {
        this(name, length, encoding, DEFAULT_START_INDEX);
    }

    public int getStartFieldIndex() {
        return startFieldIndex;
    }

    public int getEndFieldIndex() {
        return endFieldIndex;
    }

    public int getLength() {
        return length;
    }

    public int getByteLength() {
        return getEncoding().getEncodingHandler().getByteLength(length);
    }

    public boolean governsBit(int dataFieldNumber) {
        return startFieldIndex <= dataFieldNumber && dataFieldNumber <= endFieldIndex;
    }

    @Override
    public DataFieldBuilder<Bitmap> getDataFieldBuilder() {
        return new DataFieldBuilder<>(() -> new Bitmap(this));
    }

    @Override
    public BitmapParser getDataFieldParser() {
        return new BitmapParser(this);
    }
}