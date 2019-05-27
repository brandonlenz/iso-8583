package com.brandonlenz.iso8583.definitions.fields;

import com.brandonlenz.iso8583.building.fields.BitmapBuilder;
import com.brandonlenz.iso8583.definitions.names.FieldName;
import com.brandonlenz.iso8583.structure.content.ContentType;
import com.brandonlenz.iso8583.structure.encoding.Encoding;

public class BitmapDefinition extends FixedFieldDefinition {

    private final int startFieldIndex;

    public BitmapDefinition(FieldName name, int length, Encoding encoding, int startFieldIndex) {
        super(name, length, encoding, ContentType.BYTES);
        this.startFieldIndex = startFieldIndex;
    }

    public BitmapDefinition(FieldName name, int length, Encoding encoding) {
        this(name, length, encoding, 1);
    }

    public int getStartFieldIndex() {
        return startFieldIndex;
    }

    @Override
    public BitmapBuilder getDataFieldBuilder() {
        return new BitmapBuilder(this);
    }
}