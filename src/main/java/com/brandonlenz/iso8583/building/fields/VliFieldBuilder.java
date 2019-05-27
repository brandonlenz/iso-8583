package com.brandonlenz.iso8583.building.fields;

import com.brandonlenz.iso8583.definitions.fields.VliFieldDefinition;
import com.brandonlenz.iso8583.fields.Vli;
import com.brandonlenz.iso8583.fields.VliField;
import java.util.Arrays;

public final class VliFieldBuilder extends DataFieldBuilder {

    private final VliFieldDefinition fieldDefinition;
    private VliField dataField;

    public VliFieldBuilder(VliFieldDefinition fieldDefinition) {
        super(fieldDefinition);
        this.fieldDefinition = fieldDefinition;
    }

    @Override
    public VliField build() {
        return this.dataField;
    }

    @Override
    public VliFieldBuilder setRawData(byte[] rawData) {
        int vliByteLength = fieldDefinition.getVliDefinition().getByteLength();
        Vli vli = buildVli(Arrays.copyOf(rawData, vliByteLength));
        dataField = new VliField(fieldDefinition, vli);
        dataField.setRawData(Arrays.copyOfRange(rawData, vliByteLength, rawData.length));
        return this;
    }

    @Override
    public VliFieldBuilder setData(String data) {
        setRawData(fieldDefinition.getEncoding().encode(data));
        return this;
    }

    private Vli buildVli(byte[] vliBytes) {
        return fieldDefinition.getVliDefinition().getDataFieldBuilder()
                .setRawData(vliBytes)
                .build();
    }
}
