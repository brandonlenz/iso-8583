package com.brandonlenz.iso8583.parsing;

import com.brandonlenz.iso8583.building.Iso8583MessageBuilder;
import com.brandonlenz.iso8583.definitions.fields.FieldDefinition;
import com.brandonlenz.iso8583.definitions.messages.Iso8583MessageDefinition;
import com.brandonlenz.iso8583.messages.Message;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Iso8583Parser implements MessageParser {

    private final Iso8583MessageDefinition messageDefinition;

    public Iso8583Parser(Iso8583MessageDefinition iso8583MessageDefinition) {
        this.messageDefinition = iso8583MessageDefinition;
    }

    public Message parseMessageFromRawData(byte[] rawData) {
        return null;
    }

    @Override
    public Message parseMessageFromStream(InputStream inputStream) {
        Iso8583MessageBuilder messageBuilder = new Iso8583MessageBuilder(messageDefinition);
        messageBuilder.setMessageTypeIndicator(parseDataFieldBytesFromStream(inputStream, messageDefinition.getMessageTypeIndicatorDefinition()));
        messageBuilder.setPrimaryBitmap(parseDataFieldBytesFromStream(inputStream, messageDefinition.getPrimaryBitmapDefinition()));
        List<Integer> fieldNumbers = new ArrayList<>(); //TODO: get the active fields from the bitmap(s)
        for (int index : fieldNumbers) {
            messageBuilder.setField(index, parseDataFieldBytesFromStream(inputStream, messageDefinition.getFieldDefinition(index)));
        }
        return messageBuilder.getMessage();
    }

    private byte[] parseDataFieldBytesFromStream (InputStream inputStream, FieldDefinition fieldDefinition) {
        try {
            int fieldLength= fieldDefinition.getLength();
            byte[] fieldRawData = new byte[fieldLength];
            inputStream.read(fieldRawData, 0, fieldLength);
            return fieldRawData;
        } catch (IOException e) {
            System.out.println("An error occurred while trying to parse field " + fieldDefinition.getFieldName());
            e.printStackTrace();
            return new byte[0];
        }
    }
}