package com.brandonlenz.iso8583.fields;

import com.brandonlenz.iso8583.definitions.FieldDefinition;
import com.brandonlenz.iso8583.structure.Format;
import java.util.List;

public class DataField {

    private FieldDefinition fieldDefinition;
    private List<DataField> subfields;

    public DataField(FieldDefinition fieldDefinition) {
        this.fieldDefinition = fieldDefinition;
    }

    public String getName() {
        return fieldDefinition.getName();
    }

    public byte[] getRawData() {
        return fieldDefinition.getRawData();
    }

    public Format getFormat() {
        return fieldDefinition.getFormat();
    }

    public FieldDefinition getDefinition() {
        return fieldDefinition;
    }

    public List<DataField> getSubfields() {
        return subfields;
    }
}
