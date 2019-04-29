package com.brandonlenz.iso8583.fields;

import com.brandonlenz.iso8583.definitions.fields.FieldDefinition;
import com.brandonlenz.iso8583.definitions.names.FieldName;
import com.brandonlenz.iso8583.structure.Format;
import java.util.List;

public class DataField {

    private FieldDefinition fieldDefinition;
    private byte[] rawData;
    private List<DataField> subfields;

    public DataField(FieldDefinition fieldDefinition) {
        this.fieldDefinition = fieldDefinition;
    }

    public FieldName getFieldName() {
        return fieldDefinition.getFieldName();
    }

    public byte[] getRawData() {
        return rawData;
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
