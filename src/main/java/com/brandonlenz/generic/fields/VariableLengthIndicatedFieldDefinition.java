package com.brandonlenz.generic.fields;

import com.brandonlenz.generic.structure.content.ContentType;
import com.brandonlenz.generic.structure.encoding.Encoding;
import com.brandonlenz.generic.structure.format.Format;
import com.brandonlenz.iso8583.definitions.fields.names.FieldName;
import java.util.ArrayList;
import java.util.List;

public class VariableLengthIndicatedFieldDefinition extends FieldDefinition<VariableLengthIndicatedField> {

    private static final int DEFAULT_MINIMUM_LENGTH = 0;
    private final VariableLengthIndicatorDefinition variableLengthIndicatorDefinition;
    private final Integer minimumLength;
    private final Integer maximumLength;

    public VariableLengthIndicatedFieldDefinition(FieldName name,
                              Encoding encoding,
                              ContentType contentType,
                              VariableLengthIndicatorDefinition variableLengthIndicatorDefinition) {
        this(name, encoding, contentType, variableLengthIndicatorDefinition, new ArrayList<>());
    }

    public VariableLengthIndicatedFieldDefinition(FieldName name,
                              Integer maximumLength,
                              Encoding encoding,
                              ContentType contentType,
                              VariableLengthIndicatorDefinition variableLengthIndicatorDefinition) {
        this(name, DEFAULT_MINIMUM_LENGTH, maximumLength, encoding, contentType, variableLengthIndicatorDefinition, new ArrayList<>());
    }

    public VariableLengthIndicatedFieldDefinition(FieldName name,
                              Integer minimumLength,
                              Integer maximumLength,
                              Encoding encoding,
                              ContentType contentType,
                              VariableLengthIndicatorDefinition variableLengthIndicatorDefinition) {
        this(name, minimumLength, maximumLength, encoding, contentType, variableLengthIndicatorDefinition, new ArrayList<>());
    }

    public VariableLengthIndicatedFieldDefinition(FieldName name,
                              Encoding encoding,
                              ContentType contentType,
                              VariableLengthIndicatorDefinition variableLengthIndicatorDefinition,
                              List<FieldDefinition> subfieldDefinitions) {
        this(name, DEFAULT_MINIMUM_LENGTH, Integer.MAX_VALUE, encoding, contentType, variableLengthIndicatorDefinition, subfieldDefinitions);
    }

    public VariableLengthIndicatedFieldDefinition(FieldName name,
                              Integer minimumLength,
                              Integer maximumLength,
                              Encoding encoding,
                              ContentType contentType,
                              VariableLengthIndicatorDefinition variableLengthIndicatorDefinition,
                              List<FieldDefinition> subfieldDefinitions) {
        super(name, Format.VARIABLE_LENGTH_INDICATED, encoding, contentType, subfieldDefinitions);
        this.variableLengthIndicatorDefinition = variableLengthIndicatorDefinition;
        this.minimumLength = minimumLength;
        this.maximumLength = maximumLength;
    }

    public VariableLengthIndicatorDefinition getVariableLengthIndicatorDefinition() {
        return variableLengthIndicatorDefinition;
    }

    public Integer getMinimumLength() {
        return minimumLength;
    }

    public Integer getMaximumLength() {
        return maximumLength;
    }

    @Override
    public DataFieldBuilder<VariableLengthIndicatedField> getDataFieldBuilder() {
        return VariableLengthIndicatedField.builder(this);
    }

    @Override
    public DataFieldParser<VariableLengthIndicatedField> getDataFieldParser() {
        return new VariableLengthIndicatedField.Parser(this);
    }

    @Override
    public String toString() {
        return "Field Name: " + this.getFieldName().toString() +
                ", Format: " + this.getFormat() + " (" + variableLengthIndicatorDefinition.toString() + ")" +
                ", Minimum Length: " + this.getMinimumLength() +
                ", Maximum Length: " + this.getMaximumLength() +
                ", Encoding: " + this.getEncoding() +
                ", Content Type: " + this.getContentType();
    }
}
