package com.brandonlenz.iso8583.parsing;

import com.brandonlenz.iso8583.definitions.messages.SampleIso8583MessageDefinition;
import com.brandonlenz.iso8583.messages.Iso8583Message;
import com.brandonlenz.iso8583.messages.Message;
import java.io.InputStream;

public class Iso8583Parser implements MessageParser {
    @Override
    public Message parseMessageFromStream(InputStream messageStream) {
        return new Iso8583Message(new SampleIso8583MessageDefinition()); //TODO: Actually parse
    }
}
