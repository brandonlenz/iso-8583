package com.brandonlenz.iso8583.messages;

import com.brandonlenz.iso8583.definitions.fields.FieldDefinition;
import com.brandonlenz.iso8583.definitions.messages.Iso8583MessageDefinition;
import com.brandonlenz.iso8583.fields.Bitmap;
import com.brandonlenz.iso8583.fields.DataField;
import com.brandonlenz.generic.messages.MessageParser;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

public class Iso8583MessageParser implements MessageParser {
    private final Iso8583Message message;
    private final Iso8583MessageDefinition definition;

    Iso8583MessageParser(Iso8583MessageDefinition definition) {
        this.definition = definition;
        this.message = new Iso8583Message(definition);
    }

    @Override
    public Iso8583Message parseMessageFromRawData(byte[] rawData) {
        InputStream inputStream = new ByteArrayInputStream(rawData);
        return parseMessageFromStream(inputStream);
    }

    @Override
    public Iso8583Message parseMessageFromStream(InputStream messageStream) {

        message.setMessageTypeIndicator(definition.getMessageTypeIndicatorDefinition().getDataFieldParser()
                .parseFromStream(messageStream));

        message.setPrimaryBitmap(definition.getPrimaryBitmapDefinition().getDataFieldParser()
                .parseFromStream(messageStream));

        parseDataFieldsFromBitmapBits(messageStream, message.getPrimaryBitmap());
        message.getSecondaryBitmap().ifPresent(bitmap -> parseDataFieldsFromBitmapBits(messageStream, bitmap));
        message.getTertiaryBitmap().ifPresent(bitmap -> parseDataFieldsFromBitmapBits(messageStream, bitmap));
        return message;
    }

    private void parseDataFieldsFromBitmapBits(InputStream messageStream, Bitmap bitmap) {
        List<Integer> bitmapFields = bitmap.getSetBits();
        for (int fieldNumber : bitmapFields) {
            FieldDefinition fieldDefinition = definition.getFieldDefinition(fieldNumber);
            DataField dataField = fieldDefinition.getDataFieldParser().parseFromStream(messageStream);
            message.setField(fieldNumber, dataField);
        }
    }
}
