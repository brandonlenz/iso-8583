package com.brandonlenz.iso8583.validation;

public interface MessageValidator {
    //For now, we validate data fields as they are parsed (built), so no need to validate the whole message.
    //In the future we'll probably want to parse an entire message, and just log errors on specific fields in the process.
    //Alternatively, we would do all the parsing first, then validate all the fields afterwards. This could enable cross-field validation.
}
