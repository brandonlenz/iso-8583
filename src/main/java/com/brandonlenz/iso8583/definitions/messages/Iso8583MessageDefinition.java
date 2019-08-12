package com.brandonlenz.iso8583.definitions.messages;

import com.brandonlenz.iso8583.definitions.fields.BitmapDefinition;
import com.brandonlenz.iso8583.definitions.fields.FieldDefinition;
import com.brandonlenz.iso8583.definitions.names.FieldName;
import java.util.Map;

public abstract class Iso8583MessageDefinition implements MessageDefinition {

    private static final int SECONDARY_BITMAP_FIELD_NUMBER = 1;
    private static final int TERTIARY_BITMAP_FIELD_NUMBER = 65;
    private final FieldDefinition messageTypeIndicatorDefinition;
    private final BitmapDefinition primaryBitmapDefinition;
    private final Map<Integer, FieldDefinition> fieldDefinitions;

    public Iso8583MessageDefinition(FieldDefinition messageTypeIndicatorDefinition,
                             BitmapDefinition primaryBitmapDefinition,
                             Map<Integer, FieldDefinition> fieldDefinitions) {
        this.messageTypeIndicatorDefinition = messageTypeIndicatorDefinition;
        this.primaryBitmapDefinition = primaryBitmapDefinition;
        this.fieldDefinitions = fieldDefinitions;
    }

    public FieldDefinition getMessageTypeIndicatorDefinition() {
        return messageTypeIndicatorDefinition;
    }

    public BitmapDefinition getPrimaryBitmapDefinition() {
        return primaryBitmapDefinition;
    }

    public BitmapDefinition getSecondaryBitmapDefinition() {
        return (BitmapDefinition) this.getFieldDefinition(FieldName.SECONDARY_BITMAP);
    }

    public BitmapDefinition getTertiaryBitmapDefinition() {
        return (BitmapDefinition) this.getFieldDefinition(FieldName.TERTIARY_BITMAP);
    }

    public int getSecondaryBitmapFieldNumber() {
        return SECONDARY_BITMAP_FIELD_NUMBER;
    }

    public int getTertiaryBitmapFieldNumber() {
        return TERTIARY_BITMAP_FIELD_NUMBER;
    }

    @Override
    public Map<Integer, FieldDefinition> getFieldDefinitions() {
        return fieldDefinitions;
    }

    @Override
    public FieldDefinition getFieldDefinition(int fieldNumber) {
        if (fieldNumber < 1 || fieldNumber > fieldDefinitions.size()) {
            throw new IllegalArgumentException("MessageDefinition does not contain FieldDefinition for field number " + fieldNumber);
        }

        return fieldDefinitions.get(fieldNumber);
    }

    @Override
    public FieldDefinition getFieldDefinition(FieldName fieldName) {
        return fieldDefinitions.values().stream()
                .filter(fd -> fd.getFieldName().equals(fieldName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "FieldDefinition does not contain field with name: " + fieldName.getName() + "."));
    }
}
