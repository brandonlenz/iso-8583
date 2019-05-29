package com.brandonlenz.iso8583.parsing.messages;

import com.brandonlenz.iso8583.building.messages.Iso8583MessageBuilder;
import com.brandonlenz.iso8583.definitions.fields.FieldDefinition;
import com.brandonlenz.iso8583.definitions.messages.Iso8583MessageDefinition;
import com.brandonlenz.iso8583.fields.DataField;
import com.brandonlenz.iso8583.messages.Message;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

public class Iso8583MessageParser implements MessageParser {

    private final Iso8583MessageDefinition messageDefinition;

    public Iso8583MessageParser(Iso8583MessageDefinition iso8583MessageDefinition) {
        this.messageDefinition = iso8583MessageDefinition;
    }

    @Override
    public Message parseMessageFromRawData(byte[] rawData) {
        InputStream inputStream = new ByteArrayInputStream(rawData);
        return parseMessageFromStream(inputStream);
    }

    @Override
    public Message parseMessageFromStream(InputStream messageStream) { //TODO: All of this is bad. Figure it out
        Iso8583MessageBuilder messageBuilder = new Iso8583MessageBuilder(messageDefinition);

        messageBuilder.setMessageTypeIndicator(messageDefinition.getMessageTypeIndicatorDefinition().getDataFieldParser()
                        .parseFromStream(messageStream).getRawData()); //TODO: getRawData = BAD

        messageBuilder.setPrimaryBitmap(messageDefinition.getPrimaryBitmapDefinition().getDataFieldParser()
                .parseFromStream(messageStream).getRawData()); //TODO: getRawData = BAD

        List<Integer> primaryBitmapFields = messageBuilder.getPrimaryBitmap().getSetBits();
        for (int fieldNumber : primaryBitmapFields) {
            FieldDefinition fieldDefinition = messageDefinition.getFieldDefinition(fieldNumber);
            DataField dataField = fieldDefinition.getDataFieldParser().parseFromStream(messageStream);
            byte[] fieldData = dataField.getRawData();
            messageBuilder.setField(fieldNumber, fieldData); //TODO: getRawData = BAD
        }
        //if has secondary bitmap, parse those
        //if has tertiary bitmap, parse those
        return messageBuilder.getMessage();
    }
}