package com.brandonlenz.iso8583.parsing.messages;

import com.brandonlenz.iso8583.building.messages.Iso8583MessageBuilder;
import com.brandonlenz.iso8583.definitions.fields.FieldDefinition;
import com.brandonlenz.iso8583.definitions.messages.Iso8583MessageDefinition;
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
    public Message parseMessageFromStream(InputStream messageStream) {
        Iso8583MessageBuilder messageBuilder = new Iso8583MessageBuilder(messageDefinition);

        FieldDefinition messageTypeIndicatorDefinition = messageDefinition.getMessageTypeIndicatorDefinition();
        byte[] messageTypeIndicatorBytes = parseDataFieldBytesFromStream(messageStream, messageTypeIndicatorDefinition);
        messageBuilder.setMessageTypeIndicator(messageTypeIndicatorBytes);

        FieldDefinition primaryBitmapDefinition = messageDefinition.getPrimaryBitmapDefinition();
        byte[] primaryBitmapBytes = parseDataFieldBytesFromStream(messageStream, primaryBitmapDefinition);
        messageBuilder.setPrimaryBitmap(primaryBitmapBytes);

        List<Integer> primaryBitmapFields = messageBuilder.getPrimaryBitmap().getSetBits();
        for (int fieldNumber : primaryBitmapFields) {
            FieldDefinition fieldDefinition = messageDefinition.getFieldDefinition(fieldNumber);
            byte[] fieldBytes = parseDataFieldBytesFromStream(messageStream, fieldDefinition);
            messageBuilder.setField(fieldNumber, fieldBytes);
        }
        //if has secondary bitmap, parse those
        //if has tertiary bitmap, parse those
        return messageBuilder.getMessage();
    }

    private byte[] parseDataFieldBytesFromStream(InputStream messageStream, FieldDefinition fieldDefinition) {
//        try {
//            int fieldLength;
//            fieldLength = fieldDefinition.getByteLength();
//            byte[] fieldRawData = new byte[fieldLength];
//            int bytesRead = messageStream.read(fieldRawData, 0, fieldLength);
//            if (bytesRead < fieldLength) {
//                throw new IllegalArgumentException("Prematurely reached end of message InputStream");
//            }
//            return fieldRawData;
//
//        } catch (IOException e) {
//            System.out.println("An error occurred while trying to parse field " + fieldDefinition.getFieldName());
//            e.printStackTrace();
            return new byte[0];
//        }
    }
}