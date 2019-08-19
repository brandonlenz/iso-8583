package com.brandonlenz.iso8583.definitions.messages;

import com.brandonlenz.iso8583.definitions.fields.BitmapDefinition;
import com.brandonlenz.iso8583.definitions.fields.FieldDefinition;
import com.brandonlenz.iso8583.definitions.fields.FixedFieldDefinition;
import com.brandonlenz.iso8583.definitions.fields.VliDefinition;
import com.brandonlenz.iso8583.definitions.fields.VliFieldDefinition;
import com.brandonlenz.iso8583.definitions.fields.names.FieldName;
import com.brandonlenz.generic.structure.content.ContentType;
import com.brandonlenz.generic.structure.encoding.Encoding;
import java.util.HashMap;
import java.util.Map;

public class SampleIso8583MessageDefinition extends Iso8583MessageDefinition {

    private static final Encoding NUMERIC_FIELD_ENCODING = Encoding.HEXADECIMAL_ASCII;
    private static final Encoding ALPHANUMERIC_FIELD_ENCODING = Encoding.HEXADECIMAL_ASCII;
    private static final Encoding BYTE_FIELD_ENCODING = Encoding.BINARY;

    public SampleIso8583MessageDefinition() {
        super(getSampleIso8583MessageTypeIndicatorDefinition(),
              getSampleIso8583PrimaryBitmapDefinition(),
              getSampleIso8583FieldDefinitions());
    }

    private static FieldDefinition getSampleIso8583MessageTypeIndicatorDefinition() {
        return new FixedFieldDefinition(FieldName.MESSAGE_TYPE_INDICATOR, 4, NUMERIC_FIELD_ENCODING, ContentType.NUMERIC);
    }

    private static BitmapDefinition getSampleIso8583PrimaryBitmapDefinition() {
        return new BitmapDefinition(FieldName.PRIMARY_BITMAP, 8, BYTE_FIELD_ENCODING);
    }

    private static Map<Integer, FieldDefinition> getSampleIso8583FieldDefinitions() {
        Map<Integer, FieldDefinition> fieldDefinitions = new HashMap<>();

        fieldDefinitions.put(1,   new BitmapDefinition(      FieldName.SECONDARY_BITMAP,                             8,      BYTE_FIELD_ENCODING,            65));
        fieldDefinitions.put(2,   new VliFieldDefinition(    FieldName.PRIMARY_ACCOUNT_NUMBER,                       15, 19, NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC,                new VliDefinition(2, Encoding.HEXADECIMAL_ASCII, ContentType.BYTES)));
        fieldDefinitions.put(3,   new FixedFieldDefinition(  FieldName.PROCESSING_CODE,                              6,      NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.put(4,   new FixedFieldDefinition(  FieldName.AMOUNT_TRANSACTION,                           12,     NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.put(5,   new FixedFieldDefinition(  FieldName.AMOUNT_SETTLEMENT,                            12,     NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.put(6,   new FixedFieldDefinition(  FieldName.AMOUNT_CARDHOLDER_BILLING,                    12,     NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.put(7,   new FixedFieldDefinition(  FieldName.TRANSMISSION_DATE_AND_TIME,                   10,     NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.put(8,   new FixedFieldDefinition(  FieldName.AMOUNT_CARDHOLDER_BILLING_FEE,                12,     NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.put(9,   new FixedFieldDefinition(  FieldName.CONVERSION_RATE_SETTLEMENT,                   8,      NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.put(10,  new FixedFieldDefinition(  FieldName.CONVERSION_RATE_CARDHOLDER_BILLING,           8,      NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.put(11,  new FixedFieldDefinition(  FieldName.SYSTEM_TRACE_AUDIT_NUMBER,                    6,      NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.put(12,  new FixedFieldDefinition(  FieldName.LOCAL_TRANSACTION_TIME,                       6,      NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.put(13,  new FixedFieldDefinition(  FieldName.LOCAL_TRANSACTION_DATE,                       6,      NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.put(14,  new FixedFieldDefinition(  FieldName.EXPIRATION_DATE,                              4,      NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.put(15,  new FixedFieldDefinition(  FieldName.SETTLEMENT_DATE,                              4,      NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.put(16,  new FixedFieldDefinition(  FieldName.CURRENCY_CONVERSION_DATE,                     4,      NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.put(17,  new FixedFieldDefinition(  FieldName.CAPTURE_DATE,                                 4,      NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.put(18,  new FixedFieldDefinition(  FieldName.MERCHANT_CATEGORY_CODE,                       4,      NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.put(19,  new FixedFieldDefinition(  FieldName.ACQUIRING_INSTITUTION_COUNTRY_CODE,           3,      NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.put(20,  new FixedFieldDefinition(  FieldName.PAN_EXTENDED_COUNTRY_CODE,                    3,      NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.put(21,  new FixedFieldDefinition(  FieldName.FORWARDING_INSTITUTION_COUNTRY_CODE,          3,      NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.put(22,  new FixedFieldDefinition(  FieldName.POINT_OF_SERVICE_ENTRY_MODE,                  3,      NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.put(23,  new FixedFieldDefinition(  FieldName.APPLICATION_PAN_SEQUENCE_NUMBER,              3,      NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.put(24,  new FixedFieldDefinition(  FieldName.FUNCTION_CODE,                                3,      NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.put(25,  new FixedFieldDefinition(  FieldName.POINT_OF_SERVICE_CONDITION_CODE,              2,      NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.put(26,  new FixedFieldDefinition(  FieldName.POINT_OF_SERVICE_CAPTURE_CODE,                2,      NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.put(27,  new FixedFieldDefinition(  FieldName.AUTHORIZING_IDENTIFICATION_RESPONSE_LENGTH,   1,      NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.put(28,  new FixedFieldDefinition(  FieldName.AMOUNT_TRANSACTION_FEE,                       9,      ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC));
        fieldDefinitions.put(29,  new FixedFieldDefinition(  FieldName.AMOUNT_SETTLEMENT_FEE,                        9,      ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC));
        fieldDefinitions.put(30,  new FixedFieldDefinition(  FieldName.AMOUNT_TRANSACTION_PROCESSING_FEE,            9,      ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC));
        fieldDefinitions.put(31,  new FixedFieldDefinition(  FieldName.AMOUNT_SETTLEMENT_PROCESSING_FEE,             9,      ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC));
        fieldDefinitions.put(32,  new VliFieldDefinition(    FieldName.ACQUIRING_INSTITUTION_IDENTIFICATION_CODE,    11,     NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC,                new VliDefinition(2, Encoding.HEXADECIMAL_ASCII, ContentType.BYTES)));
        fieldDefinitions.put(33,  new VliFieldDefinition(    FieldName.FORWARDING_INSTITUTION_IDENTIFICATION_CODE,   11,     NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC,                new VliDefinition(2, Encoding.HEXADECIMAL_ASCII, ContentType.BYTES)));
        fieldDefinitions.put(34,  new VliFieldDefinition(    FieldName.PRIMARY_ACCOUNT_NUMBER_EXTENDED,              28,     NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC_SPECIAL,        new VliDefinition(2, Encoding.HEXADECIMAL_ASCII, ContentType.BYTES)));
        fieldDefinitions.put(35,  new VliFieldDefinition(    FieldName.TRACK_2_DATA,                                 37,     NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC_SPECIAL,        new VliDefinition(2, Encoding.HEXADECIMAL_ASCII, ContentType.BYTES)));
        fieldDefinitions.put(36,  new VliFieldDefinition(    FieldName.TRACK_3_DATA,                                 104,    NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC,                new VliDefinition(3, Encoding.HEXADECIMAL_ASCII, ContentType.BYTES)));
        fieldDefinitions.put(37,  new FixedFieldDefinition(  FieldName.RETRIEVAL_REFERENCE_NUMBER,                   12,     ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC));
        fieldDefinitions.put(38,  new FixedFieldDefinition(  FieldName.AUTHORIZATION_IDENTIFICATION_RESPONSE,        6,      ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC));
        fieldDefinitions.put(39,  new FixedFieldDefinition(  FieldName.RESPONSE_CODE,                                2,      ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC));
        fieldDefinitions.put(40,  new FixedFieldDefinition(  FieldName.SERVICE_RESTRICTION_CODE,                     3,      ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC));
        fieldDefinitions.put(41,  new FixedFieldDefinition(  FieldName.CARD_ACCEPTOR_TERMINAL_IDENTIFICATION,        8,      ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL));
        fieldDefinitions.put(42,  new FixedFieldDefinition(  FieldName.CARD_ACCEPTOR_IDENTIFICATION_CODE,            15,     ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL));
        fieldDefinitions.put(43,  new FixedFieldDefinition(  FieldName.CARD_ACCEPTOR_NAME_AND_LOCATION,              40,     ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL));
        fieldDefinitions.put(44,  new VliFieldDefinition(    FieldName.ADDITIONAL_RESPONSE_DATA,                     25,     ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC,           new VliDefinition(2, Encoding.HEXADECIMAL_ASCII, ContentType.BYTES)));
        fieldDefinitions.put(45,  new VliFieldDefinition(    FieldName.TRACK_1_DATA,                                 76,     ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC,           new VliDefinition(2, Encoding.HEXADECIMAL_ASCII, ContentType.BYTES)));
        fieldDefinitions.put(46,  new VliFieldDefinition(    FieldName.ADDITIONAL_DATA_ISO,                          999,    ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC,           new VliDefinition(3, Encoding.HEXADECIMAL_ASCII, ContentType.BYTES)));
        fieldDefinitions.put(47,  new VliFieldDefinition(    FieldName.ADDITIONAL_DATA_NATIONAL,                     999,    ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC,           new VliDefinition(3, Encoding.HEXADECIMAL_ASCII, ContentType.BYTES)));
        fieldDefinitions.put(48,  new VliFieldDefinition(    FieldName.ADDITIONAL_DATA_PRIVATE,                      999,    ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC,           new VliDefinition(3, Encoding.HEXADECIMAL_ASCII, ContentType.BYTES)));
        fieldDefinitions.put(49,  new FixedFieldDefinition(  FieldName.CURRENCY_CODE_TRANSACTION,                    3,      NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.put(50,  new FixedFieldDefinition(  FieldName.CURRENCY_CODE_SETTLEMENT,                     3,      NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.put(51,  new FixedFieldDefinition(  FieldName.CURRENCY_CODE_CARDHOLDER_BILLING,             3,      NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.put(52,  new FixedFieldDefinition(  FieldName.PERSONAL_IDENTIFICATION_NUMBER_DATA,          8,      BYTE_FIELD_ENCODING,            ContentType.BYTES));
        fieldDefinitions.put(53,  new FixedFieldDefinition(  FieldName.SECURITY_RELATED_CONTROL_INFORMATION,         16,     NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.put(54,  new VliFieldDefinition(    FieldName.ADDITIONAL_AMOUNTS,                           120,    ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC,           new VliDefinition(3, Encoding.HEXADECIMAL_ASCII, ContentType.BYTES)));
        fieldDefinitions.put(55,  new VliFieldDefinition(    FieldName.ICC_DATA,                                     999,    BYTE_FIELD_ENCODING,            ContentType.ALPHANUMERIC_SPECIAL,   new VliDefinition(3, Encoding.HEXADECIMAL_ASCII, ContentType.BYTES)));
        fieldDefinitions.put(56,  new VliFieldDefinition(    FieldName.RESERVED_ISO,                                 999,    ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL,   new VliDefinition(3, Encoding.HEXADECIMAL_ASCII, ContentType.BYTES)));
        fieldDefinitions.put(57,  new VliFieldDefinition(    FieldName.RESERVED_NATIONAL,                            999,    ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL,   new VliDefinition(3, Encoding.HEXADECIMAL_ASCII, ContentType.BYTES)));
        fieldDefinitions.put(58,  new VliFieldDefinition(    FieldName.RESERVED_NATIONAL,                            999,    ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL,   new VliDefinition(3, Encoding.HEXADECIMAL_ASCII, ContentType.BYTES)));
        fieldDefinitions.put(59,  new VliFieldDefinition(    FieldName.RESERVED_NATIONAL,                            999,    ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL,   new VliDefinition(3, Encoding.HEXADECIMAL_ASCII, ContentType.BYTES)));
        fieldDefinitions.put(60,  new VliFieldDefinition(    FieldName.RESERVED_NATIONAL,                            999,    ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL,   new VliDefinition(3, Encoding.HEXADECIMAL_ASCII, ContentType.BYTES)));
        fieldDefinitions.put(61,  new VliFieldDefinition(    FieldName.RESERVED_PRIVATE,                             999,    ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL,   new VliDefinition(3, Encoding.HEXADECIMAL_ASCII, ContentType.BYTES)));
        fieldDefinitions.put(62,  new VliFieldDefinition(    FieldName.RESERVED_PRIVATE,                             999,    ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL,   new VliDefinition(3, Encoding.HEXADECIMAL_ASCII, ContentType.BYTES)));
        fieldDefinitions.put(63,  new VliFieldDefinition(    FieldName.RESERVED_PRIVATE,                             999,    ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL,   new VliDefinition(3, Encoding.HEXADECIMAL_ASCII, ContentType.BYTES)));
        fieldDefinitions.put(64,  new FixedFieldDefinition(  FieldName.MESSAGE_AUTHENTICATION_CODE,                  8,      BYTE_FIELD_ENCODING,            ContentType.BYTES));
        fieldDefinitions.put(65,  new BitmapDefinition(      FieldName.TERTIARY_BITMAP,                              8,      BYTE_FIELD_ENCODING,            129));
        fieldDefinitions.put(66,  new FixedFieldDefinition(  FieldName.SETTLEMENT_CODE,                              1,      NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.put(67,  new FixedFieldDefinition(  FieldName.EXTENDED_PAYMENT_CODE,                        2,      NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.put(68,  new FixedFieldDefinition(  FieldName.RECEIVING_INSTITUTION_COUNTRY_CODE,           3,      NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.put(69,  new FixedFieldDefinition(  FieldName.SETTLEMENT_INSTITUTION_COUNTRY_CODE,          3,      NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.put(70,  new FixedFieldDefinition(  FieldName.NETWORK_MANAGEMENT_INFORMATION_CODE,          3,      NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.put(71,  new FixedFieldDefinition(  FieldName.MESSAGE_NUMBER,                               4,      NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.put(72,  new FixedFieldDefinition(  FieldName.LAST_MESSAGES_NUMBER,                         4,      NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.put(73,  new FixedFieldDefinition(  FieldName.ACTION_DATE,                                  6,      NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.put(74,  new FixedFieldDefinition(  FieldName.NUMBER_OF_CREDITS,                            10,     NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.put(75,  new FixedFieldDefinition(  FieldName.CREDITS_REVERSAL_NUMBER,                      10,     NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.put(76,  new FixedFieldDefinition(  FieldName.NUMBER_OF_DEBITS,                             10,     NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.put(77,  new FixedFieldDefinition(  FieldName.DEBITS_REVERSAL_NUMBER,                       10,     NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.put(78,  new FixedFieldDefinition(  FieldName.TRANSFER_NUMBER,                              10,     NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.put(79,  new FixedFieldDefinition(  FieldName.TRANSFER_REVERSAL_NUMBER,                     10,     NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.put(80,  new FixedFieldDefinition(  FieldName.NUMBER_OF_INQUIRIES,                          10,     NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.put(81,  new FixedFieldDefinition(  FieldName.NUMBER_OF_AUTHORIZATIONS,                     10,     NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.put(82,  new FixedFieldDefinition(  FieldName.CREDITS_PROCESSING_FEE_AMOUNT,                12,     NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.put(83,  new FixedFieldDefinition(  FieldName.CREDITS_TRANSACTION_FEE_AMOUNT,               12,     NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.put(84,  new FixedFieldDefinition(  FieldName.DEBITS_PROCESSING_FEE_AMOUNT,                 12,     NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.put(85,  new FixedFieldDefinition(  FieldName.DEBITS_TRANSACTION_FEE_AMOUNT,                12,     NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.put(86,  new FixedFieldDefinition(  FieldName.TOTAL_AMOUNT_OF_CREDITS,                      16,     NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.put(87,  new FixedFieldDefinition(  FieldName.CREDITS_REVERSAL_AMOUNT,                      16,     NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.put(88,  new FixedFieldDefinition(  FieldName.TOTAL_AMOUNT_OF_DEBITS,                       16,     NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.put(89,  new FixedFieldDefinition(  FieldName.DEBITS_REVERSAL_NUMBER,                       16,     NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.put(90,  new FixedFieldDefinition(  FieldName.ORIGINAL_DATA_ELEMENTS,                       42,     NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.put(91,  new FixedFieldDefinition(  FieldName.FILE_UPDATE_CODE,                             1,      ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC));
        fieldDefinitions.put(92,  new FixedFieldDefinition(  FieldName.FILE_SECURITY_CODE,                           2,      ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC));
        fieldDefinitions.put(93,  new FixedFieldDefinition(  FieldName.RESPONSE_INDICATOR,                           5,      ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC));
        fieldDefinitions.put(94,  new FixedFieldDefinition(  FieldName.SERVICE_INDICATOR,                            7,      ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC));
        fieldDefinitions.put(95,  new FixedFieldDefinition(  FieldName.REPLACEMENT_AMOUNTS,                          42,     ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC));
        fieldDefinitions.put(96,  new FixedFieldDefinition(  FieldName.MESSAGE_SECURITY_CODE,                        8,      BYTE_FIELD_ENCODING,            ContentType.BYTES));
        fieldDefinitions.put(97,  new FixedFieldDefinition(  FieldName.NET_SETTLEMENT_AMOUNT,                        17,     ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC));
        fieldDefinitions.put(98,  new FixedFieldDefinition(  FieldName.PAYEE,                                        25,     ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL));
        fieldDefinitions.put(99,  new VliFieldDefinition(    FieldName.SETTLEMENT_INSTITUTION_IDENTIFICATION_CODE,   11,     NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC,                new VliDefinition(2, Encoding.HEXADECIMAL_ASCII, ContentType.BYTES)));
        fieldDefinitions.put(100, new VliFieldDefinition(    FieldName.RECEIVING_INSTITUTION_IDENTIFICATION_CODE,    11,     NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC,                new VliDefinition(2, Encoding.HEXADECIMAL_ASCII, ContentType.BYTES)));
        fieldDefinitions.put(101, new VliFieldDefinition(    FieldName.FILE_NAME,                                    17,     ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL,   new VliDefinition(2, Encoding.HEXADECIMAL_ASCII, ContentType.BYTES)));
        fieldDefinitions.put(102, new VliFieldDefinition(    FieldName.ACCOUNT_IDENTIFICATION_1,                     28,     ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL,   new VliDefinition(2, Encoding.HEXADECIMAL_ASCII, ContentType.BYTES)));
        fieldDefinitions.put(103, new VliFieldDefinition(    FieldName.ACCOUNT_IDENTIFICATION_2,                     28,     ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL,   new VliDefinition(2, Encoding.HEXADECIMAL_ASCII, ContentType.BYTES)));
        fieldDefinitions.put(104, new VliFieldDefinition(    FieldName.TRANSACTION_DESCRIPTION,                      100,    ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL,   new VliDefinition(2, Encoding.HEXADECIMAL_ASCII, ContentType.BYTES)));
        fieldDefinitions.put(105, new VliFieldDefinition(    FieldName.RESERVED_ISO,                                 999,    ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL,   new VliDefinition(3, Encoding.HEXADECIMAL_ASCII, ContentType.BYTES)));
        fieldDefinitions.put(106, new VliFieldDefinition(    FieldName.RESERVED_ISO,                                 999,    ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL,   new VliDefinition(3, Encoding.HEXADECIMAL_ASCII, ContentType.BYTES)));
        fieldDefinitions.put(107, new VliFieldDefinition(    FieldName.RESERVED_ISO,                                 999,    ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL,   new VliDefinition(3, Encoding.HEXADECIMAL_ASCII, ContentType.BYTES)));
        fieldDefinitions.put(108, new VliFieldDefinition(    FieldName.RESERVED_ISO,                                 999,    ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL,   new VliDefinition(3, Encoding.HEXADECIMAL_ASCII, ContentType.BYTES)));
        fieldDefinitions.put(109, new VliFieldDefinition(    FieldName.RESERVED_ISO,                                 999,    ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL,   new VliDefinition(3, Encoding.HEXADECIMAL_ASCII, ContentType.BYTES)));
        fieldDefinitions.put(110, new VliFieldDefinition(    FieldName.RESERVED_ISO,                                 999,    ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL,   new VliDefinition(3, Encoding.HEXADECIMAL_ASCII, ContentType.BYTES)));
        fieldDefinitions.put(111, new VliFieldDefinition(    FieldName.RESERVED_ISO,                                 999,    ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL,   new VliDefinition(3, Encoding.HEXADECIMAL_ASCII, ContentType.BYTES)));
        fieldDefinitions.put(112, new VliFieldDefinition(    FieldName.RESERVED_NATIONAL,                            999,    ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL,   new VliDefinition(3, Encoding.HEXADECIMAL_ASCII, ContentType.BYTES)));
        fieldDefinitions.put(113, new VliFieldDefinition(    FieldName.RESERVED_NATIONAL,                            999,    ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL,   new VliDefinition(3, Encoding.HEXADECIMAL_ASCII, ContentType.BYTES)));
        fieldDefinitions.put(114, new VliFieldDefinition(    FieldName.RESERVED_NATIONAL,                            999,    ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL,   new VliDefinition(3, Encoding.HEXADECIMAL_ASCII, ContentType.BYTES)));
        fieldDefinitions.put(115, new VliFieldDefinition(    FieldName.RESERVED_NATIONAL,                            999,    ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL,   new VliDefinition(3, Encoding.HEXADECIMAL_ASCII, ContentType.BYTES)));
        fieldDefinitions.put(116, new VliFieldDefinition(    FieldName.RESERVED_NATIONAL,                            999,    ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL,   new VliDefinition(3, Encoding.HEXADECIMAL_ASCII, ContentType.BYTES)));
        fieldDefinitions.put(117, new VliFieldDefinition(    FieldName.RESERVED_NATIONAL,                            999,    ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL,   new VliDefinition(3, Encoding.HEXADECIMAL_ASCII, ContentType.BYTES)));
        fieldDefinitions.put(118, new VliFieldDefinition(    FieldName.RESERVED_NATIONAL,                            999,    ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL,   new VliDefinition(3, Encoding.HEXADECIMAL_ASCII, ContentType.BYTES)));
        fieldDefinitions.put(119, new VliFieldDefinition(    FieldName.RESERVED_NATIONAL,                            999,    ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL,   new VliDefinition(3, Encoding.HEXADECIMAL_ASCII, ContentType.BYTES)));
        fieldDefinitions.put(120, new VliFieldDefinition(    FieldName.RESERVED_PRIVATE,                             999,    ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL,   new VliDefinition(3, Encoding.HEXADECIMAL_ASCII, ContentType.BYTES)));
        fieldDefinitions.put(121, new VliFieldDefinition(    FieldName.RESERVED_PRIVATE,                             999,    ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL,   new VliDefinition(3, Encoding.HEXADECIMAL_ASCII, ContentType.BYTES)));
        fieldDefinitions.put(122, new VliFieldDefinition(    FieldName.RESERVED_PRIVATE,                             999,    ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL,   new VliDefinition(3, Encoding.HEXADECIMAL_ASCII, ContentType.BYTES)));
        fieldDefinitions.put(123, new VliFieldDefinition(    FieldName.RESERVED_PRIVATE,                             999,    ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL,   new VliDefinition(3, Encoding.HEXADECIMAL_ASCII, ContentType.BYTES)));
        fieldDefinitions.put(124, new VliFieldDefinition(    FieldName.RESERVED_PRIVATE,                             999,    ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL,   new VliDefinition(3, Encoding.HEXADECIMAL_ASCII, ContentType.BYTES)));
        fieldDefinitions.put(125, new VliFieldDefinition(    FieldName.RESERVED_PRIVATE,                             999,    ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL,   new VliDefinition(3, Encoding.HEXADECIMAL_ASCII, ContentType.BYTES)));
        fieldDefinitions.put(126, new VliFieldDefinition(    FieldName.RESERVED_PRIVATE,                             999,    ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL,   new VliDefinition(3, Encoding.HEXADECIMAL_ASCII, ContentType.BYTES)));
        fieldDefinitions.put(127, new VliFieldDefinition(    FieldName.RESERVED_PRIVATE,                             999,    ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL,   new VliDefinition(3, Encoding.HEXADECIMAL_ASCII, ContentType.BYTES)));
        fieldDefinitions.put(128, new FixedFieldDefinition(  FieldName.MESSAGE_AUTHENTICATION_CODE,                  8,      BYTE_FIELD_ENCODING,            ContentType.BYTES));

        return fieldDefinitions;
    }

}
