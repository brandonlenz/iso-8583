package com.brandonlenz.iso8583.definitions.fields;

import com.brandonlenz.iso8583.definitions.names.FieldName;
import com.brandonlenz.iso8583.structure.ContentType;
import com.brandonlenz.iso8583.structure.Encoding;
import com.brandonlenz.iso8583.structure.Format;
import java.util.ArrayList;
import java.util.List;

public abstract class FieldDefinition {

    private final FieldName name;
    private final Format format;
    private final Encoding encoding;
    private final ContentType contentType;
    private final List<FieldDefinition> subfieldDefinitions;

    public FieldDefinition(FieldName name, Format format, Encoding encoding, ContentType contentType) {
        this(name, format, encoding, contentType, new ArrayList<>());
    }

    public FieldDefinition(FieldName name,
                           Format format,
                           Encoding encoding,
                           ContentType contentType,
                           List<FieldDefinition> subfieldDefinitions) {
        this.name = name;
        this.format = format;
        this.encoding = encoding;
        this.contentType = contentType;
        this.subfieldDefinitions = subfieldDefinitions;
    }

    public FieldName getFieldName() {
        return name;
    }

    public Format getFormat() {
        return format;
    }

    public abstract int getLength();

    public Encoding getEncoding() {
        return encoding;
    }

    public List<FieldDefinition> getSubfieldDefinitions() {
        return subfieldDefinitions;
    }
}
