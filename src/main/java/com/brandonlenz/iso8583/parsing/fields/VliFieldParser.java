package com.brandonlenz.iso8583.parsing.fields;

import com.brandonlenz.iso8583.definitions.fields.VliFieldDefinition;
import com.brandonlenz.iso8583.fields.FixedField;
import com.brandonlenz.iso8583.fields.VliField;
import java.io.InputStream;

public class VliFieldParser extends DataFieldParser<VliFieldDefinition, VliField> {

    public VliFieldParser(VliFieldDefinition fieldDefinition) {
        super(fieldDefinition);
    }

    @Override
    public VliField parseFromStream(InputStream is) {
        FixedField vli = new FixedFieldParser(fieldDefinition.getVliDefinition()).parseFromStream(is);

        byte[] vliBytes = vli.getRawData();
        byte[] fieldBytes = getFieldBytes(is, Integer.parseInt(vli.getData()));
        byte[] vliFieldBytes = new byte[vliBytes.length + fieldBytes.length];

        System.arraycopy(vliBytes, 0, vliFieldBytes, 0, vliBytes.length);
        System.arraycopy(fieldBytes, 0, vliFieldBytes, vliBytes.length, fieldBytes.length);

        return fieldDefinition.getDataFieldBuilder()
                .setRawData(vliFieldBytes)
                .build();
    }
}
