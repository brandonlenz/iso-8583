package com.brandonlenz.generic.messages;

import java.io.InputStream;

public interface MessageParser {

    Message parseMessageFromRawData(byte[] rawData);

    Message parseMessageFromStream(InputStream messageStream);
}
