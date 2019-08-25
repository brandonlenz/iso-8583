package com.brandonlenz.generic.fields;

import com.brandonlenz.generic.structure.content.ContentType;
import com.brandonlenz.generic.structure.encoding.Encoding;
import com.brandonlenz.iso8583.definitions.fields.names.FieldName;

public final class BitmapDefinition extends AbstractFixedFieldDefinition<Bitmap> {

    private static final int DEFAULT_START_INDEX = 1;
    private final int startFieldIndex;
    private final int endFieldIndex;

    public BitmapDefinition(FieldName name, int length, Encoding encoding, int startFieldIndex) {
        super(name, length, encoding, ContentType.BYTES);
        this.startFieldIndex = startFieldIndex;
        this.endFieldIndex = startFieldIndex + (getByteLength() * 8) - 1;
    }

    public BitmapDefinition(FieldName name, int length, Encoding encoding) {
        this(name, length, encoding, DEFAULT_START_INDEX);
    }

    int getStartFieldIndex() {
        return startFieldIndex;
    }

    int getEndFieldIndex() {
        return endFieldIndex;
    }

    public boolean governsBit(int dataFieldNumber) {
        return startFieldIndex <= dataFieldNumber && dataFieldNumber <= endFieldIndex;
    }

    @Override
    public DataFieldBuilder<Bitmap> getDataFieldBuilder() {
        return Bitmap.builder(this);
    }

    @Override
    public DataFieldParser<Bitmap> getDataFieldParser() {
        return Bitmap.parser(this);
    }
}