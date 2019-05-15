package com.brandonlenz.iso8583.buidling;

import com.brandonlenz.iso8583.definitions.messages.Iso8583MessageDefinition;
import com.brandonlenz.iso8583.fields.DataField;
import com.brandonlenz.iso8583.messages.Iso8583Message;
import com.brandonlenz.iso8583.messages.Message;

public class Iso8583MessageBuilder implements MessageBuilder {
    private Iso8583Message message;

    public Iso8583MessageBuilder(Iso8583MessageDefinition iso8583MessageDefinition) {
        this.message = new Iso8583Message(iso8583MessageDefinition);
    }

    @Override
    public void setField(int dataFieldNumber, byte[] data) {
        //TODO: Abstract to DataFieldBuilder:
        //This should validate the raw data according to the field definition, then instantiate a DataField from it if valid
        DataField dataField = new DataField(message.getDefinition().getFieldDefinition(dataFieldNumber));
        dataField.setRawData(data); //better to have this automatically set when instantiating the DataField

        message.setDataField(dataFieldNumber, dataField);
    }

    @Override
    public void removeField(int dataFieldNumber) {
        //Get definition from index to do the proper validation of the input field
    }

    @Override
    public Message getMessage() {
        return message;
    }
}
