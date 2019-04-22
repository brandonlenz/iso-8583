package com.brandonlenz.iso8583.definitions;

import com.brandonlenz.iso8583.structure.Format;
import java.util.List;

public class FieldDefinition {

    private String name;
    private byte[] rawData;
    private Format format;
    private List<FieldDefinition> subfieldDefinitions;

    public FieldDefinition( String name,
                            byte[] rawData,
                            Format format,
                            List<FieldDefinition> subfieldDefinitions) {
        this.name = name;
        this.rawData = rawData;
        this.format = format;
        this.subfieldDefinitions = subfieldDefinitions;
    }

    public String getName() {
        return name;
    }

    public byte[] getRawData() {
        return rawData;
    }

    public Format getFormat() {
        return format;
    }

    public List<FieldDefinition> getSubfieldDefinitions() {
        return subfieldDefinitions;
    }
}
