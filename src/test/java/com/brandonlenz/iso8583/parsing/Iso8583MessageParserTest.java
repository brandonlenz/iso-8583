package com.brandonlenz.iso8583.parsing;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.brandonlenz.iso8583.definitions.messages.SampleIso8583MessageDefinition;
import com.brandonlenz.iso8583.messages.Iso8583Message;
import com.brandonlenz.iso8583.parsing.messages.Iso8583MessageParser;
import java.util.Arrays;
import javax.xml.bind.DatatypeConverter;
import org.junit.jupiter.api.Test;

class Iso8583MessageParserTest {

    private final byte[] rawMessageData = getRawMessageData();
    private final Iso8583MessageParser iso8583MessageParser = new Iso8583MessageParser(new SampleIso8583MessageDefinition());

    private byte[] getRawMessageData() {
        String messageData = "30 31 30 30" +
                             "52 00 02 00 00 00 02 00" + // Fields set: 2, 4, 7, 23, 55
                             "31 36 31 32 33 34 35 36 37 38 39 30 31 32 33 34 35 36" +
                             "30 30 30 30 30 30 30 31 30 30 30 30" +
                             "31 35 36 31 35 30 37 32 30 30" +
                             "30 35 32" +
                             "30 31 35 9F 26 08 01 02 03 04 05 06 07 08 9F 27 01 80"; //TODO: Add secondary bitmap field for test now that its supported

        return DatatypeConverter.parseHexBinary(messageData.replace(" ", ""));
    }

    @Test
    void parseMessageFromRawData() {
        Iso8583Message parsedMessage = iso8583MessageParser.parseMessageFromRawData(rawMessageData);

        assertEquals("0100", parsedMessage.getMessageTypeIndicator().getData());
        assertEquals("5200020000000200", parsedMessage.getPrimaryBitmap().getData());
        assertEquals(Arrays.asList(2, 4, 7, 23, 55), parsedMessage.getPrimaryBitmap().getSetBits());
        assertEquals("161234567890123456", parsedMessage.getDataField(2).getData());
        assertEquals("000000010000", parsedMessage.getDataField(4).getData());
        assertEquals("1561507200", parsedMessage.getDataField(7).getData());
        assertEquals("052", parsedMessage.getDataField(23).getData());
        assertEquals("0159F260801020304050607089F270180", parsedMessage.getDataField(55).getData());

        assertArrayEquals(rawMessageData, parsedMessage.getRawData());
    }
}