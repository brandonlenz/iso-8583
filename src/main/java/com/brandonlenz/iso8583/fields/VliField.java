package com.brandonlenz.iso8583.fields;

import com.brandonlenz.iso8583.definitions.fields.VliFieldDefinition;

public class VliField extends DataField {

    private final VliFieldDefinition definition;
    private final Vli vli;

    public VliField(VliFieldDefinition fieldDefinition, Vli vli) {
        super(fieldDefinition);
        this.definition = fieldDefinition;
        this.vli = vli;
    }

    @Override
    public VliFieldDefinition getDefinition() {
        return definition;
    }

    @Override
    public byte[] getRawData() {
        byte[] vliRawData = vli.getRawData();
        byte[] vliAndFieldRawData = new byte[vliRawData.length + super.getRawData().length];

        System.arraycopy(vliRawData,0,vliAndFieldRawData,0, vliRawData.length);
        System.arraycopy(super.getRawData(),0,vliAndFieldRawData, vliRawData.length, super.getRawData().length);

        return vliAndFieldRawData;
    }

    @Override
    public void setRawData(byte[] rawData) {
        //Set the vli data first:
        int vliByteLength = vli.getDefinition().getByteLength();
        byte[] vliRawData = new byte[vliByteLength];
        System.arraycopy(rawData, 0, vliRawData, 0, vliByteLength);
        vli.setRawData(vliRawData);

        //Set this VliField's data with the remainder:
        byte[] dataFieldRawData = new byte[rawData.length - vliByteLength];
        System.arraycopy(rawData, vliByteLength, dataFieldRawData, 0, rawData.length);
        super.setRawData(dataFieldRawData);
    }

    @Override
    public String getData() {
        return vli.getData() + definition.getEncoding().decode(getRawData());
    }
}
