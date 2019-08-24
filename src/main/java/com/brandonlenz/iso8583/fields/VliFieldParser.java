package com.brandonlenz.iso8583.fields;

import java.io.InputStream;

public class VliFieldParser extends DataFieldParser<VliField> {

    private final VliFieldDefinition fieldDefinition;

    VliFieldParser(VliFieldDefinition fieldDefinition) {
        this.fieldDefinition = fieldDefinition;
    }

    @Override
    public VliField parseFromStream(InputStream is) {
        VliField vliField = new VliField(fieldDefinition);;

        byte[] vliBytes = parseBytesFromStream(is, fieldDefinition.getVliDefinition().getByteLength());
        vliField.setVliRawData(vliBytes);

        byte[] fieldBytes = parseBytesFromStream(is, Integer.parseInt(vliField.getVli().getData()));
        vliField.setRawData(fieldBytes);

        return vliField;
    }
}
