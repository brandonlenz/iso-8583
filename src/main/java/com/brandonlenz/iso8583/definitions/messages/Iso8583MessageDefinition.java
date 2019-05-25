package com.brandonlenz.iso8583.definitions.messages;

import com.brandonlenz.iso8583.definitions.fields.FieldDefinition;
import com.brandonlenz.iso8583.definitions.names.FieldName;
import java.util.ArrayList;
import java.util.List;

public abstract class Iso8583MessageDefinition implements MessageDefinition {

    private static final int SECONDARY_BITMAP_FIELD_NUMBER = 1;
    private static final int TERTIARY_BITMAP_FIELD_NUMBER = 65;
    private final FieldDefinition messageTypeIndicatorDefinition;
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

    public FieldDefinition getSecondaryBitmapDefinition() {
        return this.getFieldDefinition(FieldName.SECONDARY_BITMAP);
    }

    public FieldDefinition getTertiaryBitmapDefinition() {
        return this.getFieldDefinition(FieldName.TERTIARY_BITMAP);
    }

    public int getSecondaryBitmapFieldNumber() {
        return SECONDARY_BITMAP_FIELD_NUMBER;
    }

    public int getTertiaryBitmapFieldNumber() {
        return TERTIARY_BITMAP_FIELD_NUMBER;
    }

    @Override
    public List<FieldDefinition> getFieldDefinitions() {
        List<FieldDefinition> allFieldDefinitions = new ArrayList<>();
        allFieldDefinitions.add(messageTypeIndicatorDefinition);
        allFieldDefinitions.add(primaryBitmapDefinition);
        allFieldDefinitions.addAll(fieldDefinitions);
        return allFieldDefinitions;
    }

    @Override
    public FieldDefinition getFieldDefinition(int fieldNumber) {
        return fieldDefinitions.get(fieldNumber - 1); //TODO: Handle index out of bounds exception (Also create method to get by field name perhaps?)
    }

    @Override
    public FieldDefinition getFieldDefinition(FieldName fieldName) {
        return fieldDefinitions.stream()
                .filter(fd -> fd.getFieldName().equals(fieldName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "FieldDefinition does not contain field with name: " + fieldName.getName() + "."));
    }
}
