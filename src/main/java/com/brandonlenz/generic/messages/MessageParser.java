package com.brandonlenz.generic.messages;

import java.io.InputStream;

public interface MessageParser {

    Message parseMessage(byte[] rawData);

    Message parseMessageFromStream(InputStream messageStream);
}
