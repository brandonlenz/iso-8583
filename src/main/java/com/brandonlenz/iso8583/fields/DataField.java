package com.brandonlenz.iso8583.fields;

import com.brandonlenz.iso8583.definitions.fields.FieldDefinition;
import com.brandonlenz.iso8583.definitions.names.FieldName;
import com.brandonlenz.iso8583.structure.Format;
import java.util.ArrayList;
import java.util.List;

public class DataField {

    private final FieldDefinition definition;
    private byte[] rawData;
    private List<DataField> subfields;

    public DataField(FieldDefinition fieldDefinition) {
        this.definition = fieldDefinition;
        this.subfields = createSubfieldsFromDefinition(fieldDefinition);
    }

    public FieldName getFieldName() {
        return definition.getFieldName();
    }

    public byte[] getRawData() {
        return rawData;
    }

    public void setRawData(byte[] rawData) {
        this.rawData = rawData;
    }

    public Format getFormat() {
        return definition.getFormat();
    }

    public FieldDefinition getDefinition() {
        return definition;
    }

    public List<DataField> getSubfields() {
        return subfields;
    }

    private List<DataField> createSubfieldsFromDefinition(FieldDefinition fieldDefinition) {
        List<DataField> subfieldList = new ArrayList<>();

        for (FieldDefinition subfieldDefinition : fieldDefinition.getSubfieldDefinitions()) {
            subfieldList.add(new DataField(subfieldDefinition));
        }

        return subfieldList;
    }
}
