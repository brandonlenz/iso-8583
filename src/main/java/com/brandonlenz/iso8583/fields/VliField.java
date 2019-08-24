package com.brandonlenz.iso8583.fields;

public class VliField extends DataField {

    private final VliFieldDefinition definition;
    private final FixedField vli;

    public VliField(VliFieldDefinition fieldDefinition) {
        super(fieldDefinition);
        this.definition = fieldDefinition;
        this.vli = new FixedField(fieldDefinition.getVliDefinition());
    }

    public VliField(VliFieldDefinition fieldDefinition, FixedField vli) {
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
        if (vli.getRawData() == null && super.getRawData() == null) return null;

        byte[] vliRawData = vli.getRawData();
        byte[] vliAndFieldRawData = new byte[vliRawData.length + super.getRawData().length];

        System.arraycopy(vliRawData,0,vliAndFieldRawData,0, vliRawData.length);
        System.arraycopy(super.getRawData(),0,vliAndFieldRawData, vliRawData.length, super.getRawData().length);

        return vliAndFieldRawData;
    }

    @Override
    public String getData() {
        return vli.getData() + super.getData();
    }

    public void setVliRawData(byte[] rawData) {
        vli.setRawData(rawData);
    }

    public FixedField getVli() {
        return vli;
    }
}
