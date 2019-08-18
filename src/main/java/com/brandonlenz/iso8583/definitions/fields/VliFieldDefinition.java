package com.brandonlenz.iso8583.definitions.fields;

import com.brandonlenz.iso8583.building.fields.VliDataFieldBuilder;
import com.brandonlenz.iso8583.definitions.names.FieldName;
import com.brandonlenz.iso8583.fields.VliField;
import com.brandonlenz.iso8583.parsing.fields.VliFieldParser;
import com.brandonlenz.iso8583.structure.content.ContentType;
import com.brandonlenz.iso8583.structure.encoding.Encoding;
import com.brandonlenz.iso8583.structure.format.Format;
import java.util.ArrayList;
import java.util.List;

public final class VliFieldDefinition extends FieldDefinition<VliField> {

    private static final int DEFAULT_MINIMUM_LENGTH = 0;
    private final VliDefinition vliDefinition;
    private final Integer minimumLength;
    private final Integer maximumLength;

    public VliFieldDefinition(FieldName name,
                              Encoding encoding,
                              ContentType contentType,
                              VliDefinition vliDefinition) {
        this(name, encoding, contentType, vliDefinition, new ArrayList<>());
    }

    public VliFieldDefinition(FieldName name,
                              Integer maximumLength,
                              Encoding encoding,
                              ContentType contentType,
                              VliDefinition vliDefinition) {
        this(name, DEFAULT_MINIMUM_LENGTH, maximumLength, encoding, contentType, vliDefinition, new ArrayList<>());
    }

    public VliFieldDefinition(FieldName name,
                              Integer minimumLength,
                              Integer maximumLength,
                              Encoding encoding,
                              ContentType contentType,
                              VliDefinition vliDefinition) {
        this(name, minimumLength, maximumLength, encoding, contentType, vliDefinition, new ArrayList<>());
    }

    public VliFieldDefinition(FieldName name,
                              Encoding encoding,
                              ContentType contentType,
                              VliDefinition vliDefinition,
                              List<FieldDefinition> subfieldDefinitions) {
        this(name, DEFAULT_MINIMUM_LENGTH, Integer.MAX_VALUE, encoding, contentType, vliDefinition, subfieldDefinitions);
    }

    public VliFieldDefinition(FieldName name,
                              Integer minimumLength,
                              Integer maximumLength,
                              Encoding encoding,
                              ContentType contentType,
                              VliDefinition vliDefinition,
                              List<FieldDefinition> subfieldDefinitions) {
        super(name, Format.VARIABLE_LENGTH_INDICATED, encoding, contentType, subfieldDefinitions);
        this.vliDefinition = vliDefinition;
        this.minimumLength = minimumLength;
        this.maximumLength = maximumLength;
    }

    public VliDefinition getVliDefinition() {
        return vliDefinition;
    }

    public Integer getMinimumLength() {
        return minimumLength;
    }

    public Integer getMaximumLength() {
        return maximumLength;
    }

    @Override
    public VliDataFieldBuilder getDataFieldBuilder() {
        return new VliDataFieldBuilder(() -> new VliField(this));
    }

    @Override
    public VliFieldParser getDataFieldParser() {
        return new VliFieldParser(this);
    }

    @Override
    public String toString() {
        return "Field Name: " + this.getFieldName().toString() +
                ", Format: " + this.getFormat() + " (" + vliDefinition.toString() + ")" +
                ", Minimum Length: " + this.getMinimumLength() +
                ", Maximum Length: " + this.getMaximumLength() +
                ", Encoding: " + this.getEncoding() +
                ", Content Type: " + this.getContentType();
    }
}
