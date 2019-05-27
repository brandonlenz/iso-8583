package com.brandonlenz.iso8583.parsing.messages;

import com.brandonlenz.iso8583.messages.Message;
import java.io.InputStream;

public interface MessageParser {

    Message parseMessageFromRawData(byte[] rawData);

    Message parseMessageFromStream(InputStream messageStream);
}
