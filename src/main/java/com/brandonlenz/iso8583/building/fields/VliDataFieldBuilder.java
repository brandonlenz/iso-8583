package com.brandonlenz.iso8583.building.fields;

import com.brandonlenz.iso8583.definitions.fields.VliDefinition;
import com.brandonlenz.iso8583.fields.VliField;
import java.util.function.Supplier;

public class VliDataFieldBuilder extends DataFieldBuilder<VliField> {

    public VliDataFieldBuilder(Supplier<VliField> supplier) {
        super(supplier);
    }

    @Override
    public VliField build(byte[] rawData) {
        VliField vliField = supplier.get();
        return buildFromRawData(rawData, vliField);
    }

    private byte[] createVliRawData(int indicatedLength, VliDefinition vliDefinition) {
        return vliDefinition.getEncoding().encode(indicatedLength, vliDefinition.getByteLength());
    }

    @Override
    public VliField build(String data) {
        VliField vliField = supplier.get();
        byte[] rawData = vliField.getDefinition().getEncoding().encode(data);
        return buildFromRawData(rawData, vliField);
    }

    private VliField buildFromRawData(byte[] rawData, VliField vliField) {
        byte[] vliRawData = createVliRawData(rawData.length, vliField.getDefinition().getVliDefinition());
        vliField.setVliRawData(vliRawData);
        vliField.setRawData(rawData);
        return vliField;
    }
}
