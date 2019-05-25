package com.brandonlenz.iso8583.messages;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.brandonlenz.iso8583.definitions.messages.SampleIso8583MessageDefinition;
import com.brandonlenz.iso8583.fields.DataField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Iso8583MessageTest {

    private Iso8583Message iso8583Message;

    @BeforeEach
    void setUp() {
        this.iso8583Message = new Iso8583Message(new SampleIso8583MessageDefinition());
    }

    @Test
    void setAndUnsetBitmapFields() {

        DataField testField2 = new DataField(iso8583Message.getDefinition().getFieldDefinition(2));
        iso8583Message.setDataField(2, testField2);
        assertTrue(iso8583Message.dataFieldBitIsSet(2));

        DataField testField24 = new DataField(iso8583Message.getDefinition().getFieldDefinition(24));
        iso8583Message.setDataField(24, testField24);
        assertTrue(iso8583Message.dataFieldBitIsSet(24));

        DataField testField25 = new DataField(iso8583Message.getDefinition().getFieldDefinition(25));
        iso8583Message.setDataField(25, testField25);
        assertTrue(iso8583Message.dataFieldBitIsSet(25));

        DataField testField64 = new DataField(iso8583Message.getDefinition().getFieldDefinition(64));
        iso8583Message.setDataField(64, testField64);
        assertTrue(iso8583Message.dataFieldBitIsSet(64));

        DataField testField100 = new DataField(iso8583Message.getDefinition().getFieldDefinition(100));
        iso8583Message.setDataField(100, testField100);
        assertTrue(iso8583Message.dataFieldBitIsSet(1));
        assertTrue(iso8583Message.dataFieldBitIsSet(100));

        //Now remove the fields:
        iso8583Message.removeDataField(2);
        assertFalse(iso8583Message.dataFieldBitIsSet(2));

        iso8583Message.removeDataField(24);
        assertFalse(iso8583Message.dataFieldBitIsSet(24));

        iso8583Message.removeDataField(25);
        assertFalse(iso8583Message.dataFieldBitIsSet(25));

        iso8583Message.removeDataField(64);
        assertFalse(iso8583Message.dataFieldBitIsSet(64));

        iso8583Message.removeDataField(100);
        assertFalse(iso8583Message.dataFieldBitIsSet(100));
        assertFalse(iso8583Message.dataFieldBitIsSet(1));
    }
}