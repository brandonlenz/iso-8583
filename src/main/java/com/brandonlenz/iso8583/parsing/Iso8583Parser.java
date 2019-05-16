package com.brandonlenz.iso8583.parsing;

import com.brandonlenz.iso8583.building.Iso8583MessageBuilder;
import com.brandonlenz.iso8583.definitions.messages.Iso8583MessageDefinition;
import com.brandonlenz.iso8583.messages.Message;
import java.io.InputStream;

public class Iso8583Parser implements MessageParser {

    private final Iso8583MessageBuilder messageBuilder;

    public Iso8583Parser(Iso8583MessageDefinition iso8583MessageDefinition) {
        this.messageBuilder = new Iso8583MessageBuilder(iso8583MessageDefinition);
    }

    @Override
    public Message parseMessageFromStream(InputStream messageStream) {

        return messageBuilder.getMessage();
    }
}