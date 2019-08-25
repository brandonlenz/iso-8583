package com.brandonlenz.generic.fields;

import com.brandonlenz.generic.structure.content.ContentType;
import com.brandonlenz.generic.structure.encoding.Encoding;
import com.brandonlenz.iso8583.definitions.fields.names.FieldName;

public final class VariableLengthIndicatorDefinition extends AbstractFixedFieldDefinition<VariableLengthIndicator> {

    public VariableLengthIndicatorDefinition(int length, Encoding encoding, ContentType contentType) {
        //TODO: only allow initialization of VliDefinition with Numeric or Bytes ContentTypes
        super(FieldName.VARIABLE_LENGTH_INDICATOR, length, encoding, contentType);
    }

    @Override
    public DataFieldBuilder<VariableLengthIndicator> getDataFieldBuilder() {
        return VariableLengthIndicator.builder(this);
    }

    @Override
    public DataFieldParser<VariableLengthIndicator> getDataFieldParser() {
        return VariableLengthIndicator.parser(this);
    }
}