package com.brandonlenz.iso8583.parsing;

import com.brandonlenz.iso8583.messages.Message;
import java.io.InputStream;

public interface MessageParser {
    public Message parseMessageFromStream(InputStream messageStream);
}
