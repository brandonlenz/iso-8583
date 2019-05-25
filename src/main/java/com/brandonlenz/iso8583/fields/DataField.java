package com.brandonlenz.iso8583.fields;

import com.brandonlenz.iso8583.definitions.fields.FieldDefinition;
import com.brandonlenz.iso8583.definitions.names.FieldName;
import com.brandonlenz.iso8583.structure.Format;
import java.util.ArrayList;
import java.util.List;

public class DataField {

    private final FieldDefinition definition;
    private byte[] rawData; //You couldn't live with your failure, and where did that bring you? Back to me. (NULL POINTER GON' HAPPEN HERE)
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

    public String getData() {
        return definition.getEncoding().decode(rawData);
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

    public Bitmap asBitmap(int startFieldIndex) {
        return new Bitmap(this, startFieldIndex);
    }

    @Override
    public String toString() {
        return this.getData();
    }
}
