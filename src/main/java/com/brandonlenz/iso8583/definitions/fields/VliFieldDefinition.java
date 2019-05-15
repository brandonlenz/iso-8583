package com.brandonlenz.iso8583.definitions.fields;

import com.brandonlenz.iso8583.definitions.names.FieldName;
import com.brandonlenz.iso8583.structure.content.ContentType;
import com.brandonlenz.iso8583.structure.encoding.Encoding;
import com.brandonlenz.iso8583.structure.Format;
import java.util.ArrayList;
import java.util.List;

public class VliFieldDefinition extends FieldDefinition {

    private VliDefinition vliDefinition;
    private final Integer minimumLength;
    private final Integer maximumLength;

    public VliFieldDefinition(  FieldName name,
                                Encoding encoding,
                                ContentType contentType,
                                VliDefinition vliDefinition) {
        this(name, encoding, contentType, vliDefinition, new ArrayList<>());
    }

    public VliFieldDefinition(  FieldName name,
                                Integer maximumLength,
                                Encoding encoding,
                                ContentType contentType,
                                VliDefinition vliDefinition) {
        this(name, 0, maximumLength, encoding, contentType, vliDefinition, new ArrayList<>());
    }

    public VliFieldDefinition(  FieldName name,
                                Integer minimumLength,
                                Integer maximumLength,
                                Encoding encoding,
                                ContentType contentType,
                                VliDefinition vliDefinition) {
        this(name, minimumLength, maximumLength, encoding, contentType, vliDefinition, new ArrayList<>());
    }

    public VliFieldDefinition(  FieldName name,
                                Encoding encoding,
                                ContentType contentType,
                                VliDefinition vliDefinition,
                                List<FieldDefinition> subfieldDefinitions) {
        this(name, 0, Integer.MAX_VALUE, encoding, contentType, vliDefinition, subfieldDefinitions);
    }

    public VliFieldDefinition(  FieldName name,
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

    public FieldDefinition getVliDefinition() {
        return vliDefinition;
    }

    public Integer getMinimumLength() {
        return minimumLength;
    }

    public Integer getMaximumLength() {
        return maximumLength;
    }

    public int getLength() {
        return 0;
    }
}
