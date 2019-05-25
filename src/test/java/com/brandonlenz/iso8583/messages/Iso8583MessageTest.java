package com.brandonlenz.iso8583.messages;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
        //Set the fields
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

        //Now remove the fields:
        iso8583Message.removeDataField(2);
        assertFalse(iso8583Message.dataFieldBitIsSet(2));

        iso8583Message.removeDataField(24);
        assertFalse(iso8583Message.dataFieldBitIsSet(24));

        iso8583Message.removeDataField(25);
        assertFalse(iso8583Message.dataFieldBitIsSet(25));

        iso8583Message.removeDataField(64);
        assertFalse(iso8583Message.dataFieldBitIsSet(64));
    }

    @Test
    void setAndUnsetSecondaryBitmapFields() {
        //Set the fields
        DataField testField100 = new DataField(iso8583Message.getDefinition().getFieldDefinition(100));
        iso8583Message.setDataField(100, testField100);
        assertTrue(iso8583Message.dataFieldBitIsSet(1));
        assertTrue(iso8583Message.dataFieldBitIsSet(100));

        DataField testField101 = new DataField(iso8583Message.getDefinition().getFieldDefinition(101));
        iso8583Message.setDataField(101, testField101);
        assertTrue(iso8583Message.dataFieldBitIsSet(101));

        //Now unset the fields
        iso8583Message.removeDataField(100);
        assertFalse(iso8583Message.dataFieldBitIsSet(100));

        iso8583Message.removeDataField(101);
        assertFalse(iso8583Message.dataFieldBitIsSet(101));
        assertFalse(iso8583Message.dataFieldBitIsSet(1));
    }

    @Test
    void setAndUnsetTertiaryBitmapFields() {
        //Set the fields
        //TODO: For now throw exception as Tertiaty Bitmaps are not really supported (but could be easily)
        DataField testField150 = new DataField(iso8583Message.getDefinition().getFieldDefinition(100));
        assertThrows(IllegalArgumentException.class, () -> iso8583Message.setDataField(150, testField150));
//        assertTrue(iso8583Message.dataFieldBitIsSet(1));
//        assertTrue(iso8583Message.dataFieldBitIsSet(65));
//        assertTrue(iso8583Message.dataFieldBitIsSet(150));
//
//        DataField testField170 = new DataField(iso8583Message.getDefinition().getFieldDefinition(101));
//        iso8583Message.setDataField(170, testField170);
//        assertTrue(iso8583Message.dataFieldBitIsSet(170));
//
//        //Now unset the fields
//        iso8583Message.removeDataField(170);
//        assertFalse(iso8583Message.dataFieldBitIsSet(170));
//
//        iso8583Message.removeDataField(150);
//        assertFalse(iso8583Message.dataFieldBitIsSet(150));
//        assertFalse(iso8583Message.dataFieldBitIsSet(65));
//        assertFalse(iso8583Message.dataFieldBitIsSet(1));
    }
}