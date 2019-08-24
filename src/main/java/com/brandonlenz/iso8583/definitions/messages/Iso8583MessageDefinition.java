package com.brandonlenz.iso8583.definitions.messages;

import com.brandonlenz.iso8583.fields.BitmapDefinition;
import com.brandonlenz.iso8583.fields.FieldDefinition;
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

    //TODO: Rework to not require casting of bitmap fields on get
    public BitmapDefinition getSecondaryBitmapDefinition() {
        return (BitmapDefinition) getFieldDefinition(SECONDARY_BITMAP_FIELD_NUMBER);
    }

    //TODO: Rework to not require casting of bitmap fields on get
    public BitmapDefinition getTertiaryBitmapDefinition() {
        return (BitmapDefinition) getFieldDefinition(TERTIARY_BITMAP_FIELD_NUMBER);
    }

    public int getSecondaryBitmapFieldNumber() {
        return SECONDARY_BITMAP_FIELD_NUMBER;
    }

    public int getTertiaryBitmapFieldNumber() {
        return TERTIARY_BITMAP_FIELD_NUMBER;
    }

    public boolean primaryBitmapGovernsBit(int fieldNumber) {
        return getPrimaryBitmapDefinition().governsBit(fieldNumber);
    }

    public boolean secondaryBitmapGovernsBit(int fieldNumber) {
        return getSecondaryBitmapDefinition().governsBit(fieldNumber);
    }

    public boolean tertiaryBitmapGovernsBit(int fieldNumber) {
        return getTertiaryBitmapDefinition().governsBit(fieldNumber);
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
}
