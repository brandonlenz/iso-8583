package com.brandonlenz.iso8583.definitions.messages;

import com.brandonlenz.iso8583.definitions.fields.FieldDefinition;
import java.util.List;

public abstract class Iso8583MessageDefinition implements MessageDefinition {
    protected final FieldDefinition messageTypeIndicatorDefinition;
    private final FieldDefinition primaryBitmapDefinition;
    private final List<FieldDefinition> fieldDefinitions;

    public Iso8583MessageDefinition(FieldDefinition messageTypeIndicatorDefinition,
                             FieldDefinition primaryBitmapDefinition,
                             List<FieldDefinition> fieldDefinitions) {
        this.messageTypeIndicatorDefinition = messageTypeIndicatorDefinition;
        this.primaryBitmapDefinition = primaryBitmapDefinition;
        this.fieldDefinitions = fieldDefinitions;
    }

    public FieldDefinition getMessageTypeIndicatorDefinition() {
        return messageTypeIndicatorDefinition;
    }

    public FieldDefinition getPrimaryBitmapDefinition() {
        return primaryBitmapDefinition;
    }

    @Override
    public List<FieldDefinition> getFieldDefinitions() {
        return fieldDefinitions;
    }

    @Override
    public FieldDefinition getFieldDefinition(int fieldNumber) {
        return fieldDefinitions.get(fieldNumber - 1); //TODO: Handle index out of bounds exception (Also create method to get by field name perhaps?)
    }
}
