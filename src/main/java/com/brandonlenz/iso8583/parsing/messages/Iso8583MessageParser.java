package com.brandonlenz.iso8583.parsing.messages;

import com.brandonlenz.iso8583.definitions.fields.FieldDefinition;
import com.brandonlenz.iso8583.definitions.messages.Iso8583MessageDefinition;
import com.brandonlenz.iso8583.fields.Bitmap;
import com.brandonlenz.iso8583.fields.DataField;
import com.brandonlenz.iso8583.messages.Iso8583Message;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

public class Iso8583MessageParser implements MessageParser {

    private final Iso8583MessageDefinition messageDefinition;

    public Iso8583MessageParser(Iso8583MessageDefinition iso8583MessageDefinition) {
        this.messageDefinition = iso8583MessageDefinition;
    }

    @Override
    public Iso8583Message parseMessageFromRawData(byte[] rawData) {
        InputStream inputStream = new ByteArrayInputStream(rawData);
        return parseMessageFromStream(inputStream);
    }

    @Override
    public Iso8583Message parseMessageFromStream(InputStream messageStream) {
        Iso8583Message message = new Iso8583Message(messageDefinition);

        message.setMessageTypeIndicator(messageDefinition.getMessageTypeIndicatorDefinition().getDataFieldParser()
                .parseFromStream(messageStream));

        message.setPrimaryBitmap(messageDefinition.getPrimaryBitmapDefinition().getDataFieldParser()
                .parseFromStream(messageStream));

        parseDataFieldsFromBitmapBits(messageStream, message.getPrimaryBitmap(), message);

        if (message.dataFieldBitIsSet(message.getDefinition().getSecondaryBitmapFieldNumber())) {
            parseDataFieldsFromBitmapBits(messageStream, message.getSecondaryBitmap(),message);
        }

        return message;
    }

    private void parseDataFieldsFromBitmapBits(InputStream messageStream, Bitmap bitmap, Iso8583Message message) {
        //TODO: Once messages are refactored to contain ONLY the fields that are set,
        // return a collection of DataFields here and do not take message as an input
        List<Integer> bitmapFields = bitmap.getSetBits();
        for (int fieldNumber : bitmapFields) {
            FieldDefinition fieldDefinition = messageDefinition.getFieldDefinition(fieldNumber);
            DataField dataField = fieldDefinition.getDataFieldParser().parseFromStream(messageStream);
            message.setDataField(fieldNumber, dataField);
        }
    }
}