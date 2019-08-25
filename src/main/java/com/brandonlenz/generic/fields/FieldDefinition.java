package com.brandonlenz.generic.fields;

import com.brandonlenz.generic.structure.content.ContentType;
import com.brandonlenz.generic.structure.encoding.Encoding;
import com.brandonlenz.generic.structure.format.Format;
import com.brandonlenz.iso8583.definitions.fields.names.FieldName;
import java.util.ArrayList;
import java.util.List;

public abstract class FieldDefinition<F extends DataField> {

    private final FieldName name;
    private final Format format;
    private final Encoding encoding;
    private final ContentType contentType;
    private final List<FieldDefinition> subfieldDefinitions;

    FieldDefinition(FieldName name, Format format, Encoding encoding, ContentType contentType) {
        this(name, format, encoding, contentType, new ArrayList<>());
    }

    FieldDefinition(FieldName name,
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

    public Encoding getEncoding() {
        return encoding;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public List<FieldDefinition> getSubfieldDefinitions() {
        return subfieldDefinitions;
    }

    public abstract DataFieldBuilder<F> getDataFieldBuilder();

    public abstract DataFieldParser<F> getDataFieldParser();

    @Override
    public String toString() {
        return "Field Name: " + this.getFieldName().toString() +
                ", Format: " + this.getFormat() +
                ", Encoding: " + this.getEncoding() +
                ", Content Type: " + this.getContentType();
    }
}