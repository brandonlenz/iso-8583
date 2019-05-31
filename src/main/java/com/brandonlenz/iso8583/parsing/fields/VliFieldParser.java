package com.brandonlenz.iso8583.parsing.fields;

import com.brandonlenz.iso8583.definitions.fields.VliFieldDefinition;
import com.brandonlenz.iso8583.fields.VliField;
import java.io.InputStream;

public class VliFieldParser extends DataFieldParser<VliField> {

    private final VliFieldDefinition fieldDefinition;

    public VliFieldParser(VliFieldDefinition fieldDefinition) {
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
