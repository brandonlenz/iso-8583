package com.brandonlenz.iso8583.definitions.messages;

import com.brandonlenz.iso8583.definitions.fields.FieldDefinition;
import com.brandonlenz.iso8583.definitions.fields.FixedFieldDefinition;
import com.brandonlenz.iso8583.definitions.fields.VliDefinition;
import com.brandonlenz.iso8583.definitions.fields.VliFieldDefinition;
import com.brandonlenz.iso8583.definitions.names.FieldName;
import com.brandonlenz.iso8583.structure.ContentType;
import com.brandonlenz.iso8583.structure.Encoding;
import java.util.ArrayList;
import java.util.List;

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
        return new FixedFieldDefinition(FieldName.MTI, 4, NUMERIC_FIELD_ENCODING, ContentType.NUMERIC);
    }

    private static FieldDefinition getSampleIso8583PrimaryBitmapDefinition() {
        return new FixedFieldDefinition(FieldName.PRIMARY_BITMAP, 8, BYTE_FIELD_ENCODING, ContentType.BYTES);
    }

    private static List<FieldDefinition> getSampleIso8583FieldDefinitions() {
        List<FieldDefinition> fieldDefinitions = new ArrayList<>();

        fieldDefinitions.add(new FixedFieldDefinition(  FieldName.SECONDARY_BITMAP,                             8,      BYTE_FIELD_ENCODING,            ContentType.BYTES));
        fieldDefinitions.add(new VliFieldDefinition(    FieldName.PRIMARY_ACCOUNT_NUMBER,                       15, 19, NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC,                new VliDefinition(2, Encoding.HEXADECIMAL_ASCII)));
        fieldDefinitions.add(new FixedFieldDefinition(  FieldName.PROCESSING_CODE,                              6,      NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.add(new FixedFieldDefinition(  FieldName.AMOUNT_TRANSACTION,                           12,     NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.add(new FixedFieldDefinition(  FieldName.AMOUNT_SETTLEMENT,                            12,     NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.add(new FixedFieldDefinition(  FieldName.AMOUNT_CARDHOLDER_BILLING,                    12,     NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.add(new FixedFieldDefinition(  FieldName.TRANSMISSION_DATE_AND_TIME,                   10,     NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.add(new FixedFieldDefinition(  FieldName.AMOUNT_CARDHOLDER_BILLING_FEE,                12,     NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.add(new FixedFieldDefinition(  FieldName.CONVERSION_RATE_SETTLEMENT,                   8,      NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.add(new FixedFieldDefinition(  FieldName.CONVERSION_RATE_CARDHOLDER_BILLING,           8,      NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.add(new FixedFieldDefinition(  FieldName.SYSTEM_TRACE_AUDIT_NUMBER,                    6,      NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.add(new FixedFieldDefinition(  FieldName.LOCAL_TRANSACTION_TIME,                       6,      NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.add(new FixedFieldDefinition(  FieldName.LOCAL_TRANSACTION_DATE,                       6,      NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.add(new FixedFieldDefinition(  FieldName.EXPIRATION_DATE,                              4,      NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.add(new FixedFieldDefinition(  FieldName.SETTLEMENT_DATE,                              4,      NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.add(new FixedFieldDefinition(  FieldName.CURRENCY_CONVERSION_DATE,                     4,      NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.add(new FixedFieldDefinition(  FieldName.CAPTURE_DATE,                                 4,      NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.add(new FixedFieldDefinition(  FieldName.MERCHANT_CATEGORY_CODE,                       4,      NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.add(new FixedFieldDefinition(  FieldName.ACQUIRING_INSTITUTION_COUNTRY_CODE,           3,      NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.add(new FixedFieldDefinition(  FieldName.PAN_EXTENDED_COUNTRY_CODE,                    3,      NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.add(new FixedFieldDefinition(  FieldName.FORWARDING_INSTITUTION_COUNTRY_CODE,          3,      NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.add(new FixedFieldDefinition(  FieldName.POINT_OF_SERVICE_ENTRY_MODE,                  3,      NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.add(new FixedFieldDefinition(  FieldName.APPLICATION_PAN_SEQUENCE_NUMBER,              3,      NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.add(new FixedFieldDefinition(  FieldName.FUNCTION_CODE,                                3,      NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.add(new FixedFieldDefinition(  FieldName.POINT_OF_SERVICE_CONDITION_CODE,              2,      NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.add(new FixedFieldDefinition(  FieldName.POINT_OF_SERCICE_CAPTURE_CODE,                2,      NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.add(new FixedFieldDefinition(  FieldName.AUTHORIZING_IDENTIFICATION_RESPONSE_LENGTH,   1,      NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.add(new FixedFieldDefinition(  FieldName.AMOUNT_TRANSACTION_FEE,                       9,      ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC));
        fieldDefinitions.add(new FixedFieldDefinition(  FieldName.AMOUNT_SETTLEMENT_FEE,                        9,      ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC));
        fieldDefinitions.add(new FixedFieldDefinition(  FieldName.AMOUNT_TRANSACTION_PROCESSING_FEE,            9,      ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC));
        fieldDefinitions.add(new FixedFieldDefinition(  FieldName.AMOUNT_SETTLEMENT_PROCESSING_FEE,             9,      ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC));
        fieldDefinitions.add(new VliFieldDefinition(    FieldName.ACQUIRING_INSTITUTION_IDENTIFICATION_CODE,    11,     NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC,                new VliDefinition(2, Encoding.HEXADECIMAL_ASCII)));
        fieldDefinitions.add(new VliFieldDefinition(    FieldName.FORWARDING_INSTITUTION_IDENTIFICATION_CODE,   11,     NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC,                new VliDefinition(2, Encoding.HEXADECIMAL_ASCII)));
        fieldDefinitions.add(new VliFieldDefinition(    FieldName.PRIMARY_ACCOUNT_NUMBER_EXTENDED,              28,     NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC_SPECIAL,        new VliDefinition(2, Encoding.HEXADECIMAL_ASCII)));
        fieldDefinitions.add(new VliFieldDefinition(    FieldName.TRACK_2_DATA,                                 37,     NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC_SPECIAL,        new VliDefinition(2, Encoding.HEXADECIMAL_ASCII)));
        fieldDefinitions.add(new VliFieldDefinition(    FieldName.TRACK_3_DATA,                                 104,    NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC,                new VliDefinition(3, Encoding.HEXADECIMAL_ASCII)));
        fieldDefinitions.add(new FixedFieldDefinition(  FieldName.RETRIEVAL_REFERENCE_NUMBER,                   12,     ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC));
        fieldDefinitions.add(new FixedFieldDefinition(  FieldName.AUTHORIZATION_IDENTIFICATION_RESPONSE,        6,      ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC));
        fieldDefinitions.add(new FixedFieldDefinition(  FieldName.RESPONSE_CODE,                                2,      ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC));
        fieldDefinitions.add(new FixedFieldDefinition(  FieldName.SERVICE_RESTRICTION_CODE,                     3,      ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC));
        fieldDefinitions.add(new FixedFieldDefinition(  FieldName.CARD_ACCEPTOR_TERMINAL_IDENTIFICATION,        8,      ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL));
        fieldDefinitions.add(new FixedFieldDefinition(  FieldName.CARD_ACCEPTOR_IDENTIFICATION_CODE,            15,     ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL));
        fieldDefinitions.add(new FixedFieldDefinition(  FieldName.CARD_ACCEPTOR_NAME_AND_LOCATION,              40,     ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL));
        fieldDefinitions.add(new VliFieldDefinition(    FieldName.ADDITIONAL_RESPONSE_DATA,                     25,     ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC,           new VliDefinition(2, Encoding.HEXADECIMAL_ASCII)));
        fieldDefinitions.add(new VliFieldDefinition(    FieldName.TRACK_1_DATA,                                 76,     ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC,           new VliDefinition(2, Encoding.HEXADECIMAL_ASCII)));
        fieldDefinitions.add(new VliFieldDefinition(    FieldName.ADDITIONAL_DATA_ISO,                          999,    ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC,           new VliDefinition(3, Encoding.HEXADECIMAL_ASCII)));
        fieldDefinitions.add(new VliFieldDefinition(    FieldName.ADDITIONAL_DATA_NATIONAL,                     999,    ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC,           new VliDefinition(3, Encoding.HEXADECIMAL_ASCII)));
        fieldDefinitions.add(new VliFieldDefinition(    FieldName.ADDITIONAL_DATA_PRIVATE,                      999,    ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC,           new VliDefinition(3, Encoding.HEXADECIMAL_ASCII)));
        fieldDefinitions.add(new FixedFieldDefinition(  FieldName.CURRENCY_CODE_TRANSACTION,                    3,      NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.add(new FixedFieldDefinition(  FieldName.CURRENCY_CODE_SETTLEMENT,                     3,      NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.add(new FixedFieldDefinition(  FieldName.CURRENCY_CODE_CARDHOLDER_BILLING,             3,      NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.add(new FixedFieldDefinition(  FieldName.PERSONAL_IDENTIFICATION_NUMBER_DATA,          8,      BYTE_FIELD_ENCODING,            ContentType.BYTES));
        fieldDefinitions.add(new FixedFieldDefinition(  FieldName.SECURITY_RELATED_CONTROL_INFORMATION,         16,     NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.add(new VliFieldDefinition(    FieldName.ADDITIONAL_AMOUNTS,                           120,    ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC,           new VliDefinition(3, Encoding.HEXADECIMAL_ASCII)));
        fieldDefinitions.add(new VliFieldDefinition(    FieldName.ICC_DATA,                                     999,    ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL,   new VliDefinition(3, Encoding.HEXADECIMAL_ASCII)));
        fieldDefinitions.add(new VliFieldDefinition(    FieldName.RESERVED_ISO,                                 999,    ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL,   new VliDefinition(3, Encoding.HEXADECIMAL_ASCII)));
        fieldDefinitions.add(new VliFieldDefinition(    FieldName.RESERVED_NATIONAL,                            999,    ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL,   new VliDefinition(3, Encoding.HEXADECIMAL_ASCII)));
        fieldDefinitions.add(new VliFieldDefinition(    FieldName.RESERVED_NATIONAL,                            999,    ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL,   new VliDefinition(3, Encoding.HEXADECIMAL_ASCII)));
        fieldDefinitions.add(new VliFieldDefinition(    FieldName.RESERVED_NATIONAL,                            999,    ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL,   new VliDefinition(3, Encoding.HEXADECIMAL_ASCII)));
        fieldDefinitions.add(new VliFieldDefinition(    FieldName.RESERVED_NATIONAL,                            999,    ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL,   new VliDefinition(3, Encoding.HEXADECIMAL_ASCII)));
        fieldDefinitions.add(new VliFieldDefinition(    FieldName.RESERVED_PRIVATE,                             999,    ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL,   new VliDefinition(3, Encoding.HEXADECIMAL_ASCII)));
        fieldDefinitions.add(new VliFieldDefinition(    FieldName.RESERVED_PRIVATE,                             999,    ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL,   new VliDefinition(3, Encoding.HEXADECIMAL_ASCII)));
        fieldDefinitions.add(new VliFieldDefinition(    FieldName.RESERVED_PRIVATE,                             999,    ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL,   new VliDefinition(3, Encoding.HEXADECIMAL_ASCII)));
        fieldDefinitions.add(new FixedFieldDefinition(  FieldName.MESSAGE_AUTHENTICATION_CODE,                  8,      BYTE_FIELD_ENCODING,            ContentType.BYTES));
        fieldDefinitions.add(new FixedFieldDefinition(  FieldName.TERTIARY_BITMAP,                              8,      BYTE_FIELD_ENCODING,            ContentType.BYTES));
        fieldDefinitions.add(new FixedFieldDefinition(  FieldName.SETTLEMENT_CODE,                              1,      NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.add(new FixedFieldDefinition(  FieldName.EXTENDED_PAYMENT_CODE,                        2,      NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.add(new FixedFieldDefinition(  FieldName.RECEIVING_INSTITUTION_COUNTRY_CODE,           3,      NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.add(new FixedFieldDefinition(  FieldName.SETTLEMENT_INSTITUTION_COUNTRY_CODE,          3,      NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.add(new FixedFieldDefinition(  FieldName.NETWORK_MANAGEMENT_INFORMATION_CODE,          3,      NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.add(new FixedFieldDefinition(  FieldName.MESSAGE_NUMBER,                               4,      NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.add(new FixedFieldDefinition(  FieldName.LAST_MESSAGES_NUMBER,                         4,      NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.add(new FixedFieldDefinition(  FieldName.ACTION_DATE,                                  6,      NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.add(new FixedFieldDefinition(  FieldName.NUMBER_OF_CREDITS,                            10,     NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.add(new FixedFieldDefinition(  FieldName.CREDITS_REVERSAL_NUMBER,                      10,     NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.add(new FixedFieldDefinition(  FieldName.NUMBER_OF_DEBITS,                             10,     NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.add(new FixedFieldDefinition(  FieldName.DEBITS_REVERSAL_NUMBER,                       10,     NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.add(new FixedFieldDefinition(  FieldName.TRANSFER_NUMBER,                              10,     NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.add(new FixedFieldDefinition(  FieldName.TRANSFER_REVERSAL_NUMBER,                     10,     NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.add(new FixedFieldDefinition(  FieldName.NUMBER_OF_INQUIRIES,                          10,     NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.add(new FixedFieldDefinition(  FieldName.NUMBER_OF_AUTHORIZATIONS,                     10,     NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.add(new FixedFieldDefinition(  FieldName.CREDITS_PROCESSING_FEE_AMOUNT,                12,     NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.add(new FixedFieldDefinition(  FieldName.CREDITS_TRANSACTION_FEE_AMOUNT,               12,     NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.add(new FixedFieldDefinition(  FieldName.DEBITS_PROCESSING_FEE_AMOUNT,                 12,     NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.add(new FixedFieldDefinition(  FieldName.DEBITS_TRANSACTION_FEE_AMOUNT,                12,     NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.add(new FixedFieldDefinition(  FieldName.TOTAL_AMOUNT_OF_CREDITS,                      16,     NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.add(new FixedFieldDefinition(  FieldName.CREDITS_REVERSAL_AMOUNT,                      16,     NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.add(new FixedFieldDefinition(  FieldName.TOTAL_AMOUNT_OF_DEBITS,                       16,     NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.add(new FixedFieldDefinition(  FieldName.DEBITS_REVERSAL_NUMBER,                       16,     NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.add(new FixedFieldDefinition(  FieldName.ORIGINAL_DATA_ELEMENTS,                       42,     NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC));
        fieldDefinitions.add(new FixedFieldDefinition(  FieldName.FILE_UPDATE_CODE,                             1,      ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC));
        fieldDefinitions.add(new FixedFieldDefinition(  FieldName.FILE_SECURITY_CODE,                           2,      ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC));
        fieldDefinitions.add(new FixedFieldDefinition(  FieldName.RESPONSE_INDICATOR,                           5,      ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC));
        fieldDefinitions.add(new FixedFieldDefinition(  FieldName.SERVICE_INDICATOR,                            7,      ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC));
        fieldDefinitions.add(new FixedFieldDefinition(  FieldName.REPLACEMENT_AMOUNTS,                          42,     ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC));
        fieldDefinitions.add(new FixedFieldDefinition(  FieldName.MESSAGE_SECURITY_CODE,                        8,      BYTE_FIELD_ENCODING,            ContentType.BYTES));
        fieldDefinitions.add(new FixedFieldDefinition(  FieldName.NET_SETTLEMENT_AMOUNT,                        17,     ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC));
        fieldDefinitions.add(new FixedFieldDefinition(  FieldName.PAYEE,                                        25,     ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL));
        fieldDefinitions.add(new VliFieldDefinition(    FieldName.SETTLEMENT_INSTITUTION_IDENTIFICATION_CODE,   11,     NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC,                new VliDefinition(2, Encoding.HEXADECIMAL_ASCII)));
        fieldDefinitions.add(new VliFieldDefinition(    FieldName.RECEIVING_INSTITUTION_IDENTIFICATION_CODE,    11,     NUMERIC_FIELD_ENCODING,         ContentType.NUMERIC,                new VliDefinition(2, Encoding.HEXADECIMAL_ASCII)));
        fieldDefinitions.add(new VliFieldDefinition(    FieldName.FILE_NAME,                                    17,     ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL,   new VliDefinition(2, Encoding.HEXADECIMAL_ASCII)));
        fieldDefinitions.add(new VliFieldDefinition(    FieldName.ACCOUNT_IDENTIFICATION_1,                     28,     ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL,   new VliDefinition(2, Encoding.HEXADECIMAL_ASCII)));
        fieldDefinitions.add(new VliFieldDefinition(    FieldName.ACCOUNT_IDENTIFICATION_2,                     28,     ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL,   new VliDefinition(2, Encoding.HEXADECIMAL_ASCII)));
        fieldDefinitions.add(new VliFieldDefinition(    FieldName.TRANSACTION_DESCRIPTION,                      100,    ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL,   new VliDefinition(2, Encoding.HEXADECIMAL_ASCII)));
        fieldDefinitions.add(new VliFieldDefinition(    FieldName.RESERVED_ISO,                                 999,    ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL,   new VliDefinition(3, Encoding.HEXADECIMAL_ASCII)));
        fieldDefinitions.add(new VliFieldDefinition(    FieldName.RESERVED_ISO,                                 999,    ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL,   new VliDefinition(3, Encoding.HEXADECIMAL_ASCII)));
        fieldDefinitions.add(new VliFieldDefinition(    FieldName.RESERVED_ISO,                                 999,    ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL,   new VliDefinition(3, Encoding.HEXADECIMAL_ASCII)));
        fieldDefinitions.add(new VliFieldDefinition(    FieldName.RESERVED_ISO,                                 999,    ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL,   new VliDefinition(3, Encoding.HEXADECIMAL_ASCII)));
        fieldDefinitions.add(new VliFieldDefinition(    FieldName.RESERVED_ISO,                                 999,    ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL,   new VliDefinition(3, Encoding.HEXADECIMAL_ASCII)));
        fieldDefinitions.add(new VliFieldDefinition(    FieldName.RESERVED_ISO,                                 999,    ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL,   new VliDefinition(3, Encoding.HEXADECIMAL_ASCII)));
        fieldDefinitions.add(new VliFieldDefinition(    FieldName.RESERVED_ISO,                                 999,    ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL,   new VliDefinition(3, Encoding.HEXADECIMAL_ASCII)));
        fieldDefinitions.add(new VliFieldDefinition(    FieldName.RESERVED_NATIONAL,                            999,    ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL,   new VliDefinition(3, Encoding.HEXADECIMAL_ASCII)));
        fieldDefinitions.add(new VliFieldDefinition(    FieldName.RESERVED_NATIONAL,                            999,    ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL,   new VliDefinition(3, Encoding.HEXADECIMAL_ASCII)));
        fieldDefinitions.add(new VliFieldDefinition(    FieldName.RESERVED_NATIONAL,                            999,    ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL,   new VliDefinition(3, Encoding.HEXADECIMAL_ASCII)));
        fieldDefinitions.add(new VliFieldDefinition(    FieldName.RESERVED_NATIONAL,                            999,    ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL,   new VliDefinition(3, Encoding.HEXADECIMAL_ASCII)));
        fieldDefinitions.add(new VliFieldDefinition(    FieldName.RESERVED_NATIONAL,                            999,    ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL,   new VliDefinition(3, Encoding.HEXADECIMAL_ASCII)));
        fieldDefinitions.add(new VliFieldDefinition(    FieldName.RESERVED_NATIONAL,                            999,    ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL,   new VliDefinition(3, Encoding.HEXADECIMAL_ASCII)));
        fieldDefinitions.add(new VliFieldDefinition(    FieldName.RESERVED_NATIONAL,                            999,    ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL,   new VliDefinition(3, Encoding.HEXADECIMAL_ASCII)));
        fieldDefinitions.add(new VliFieldDefinition(    FieldName.RESERVED_NATIONAL,                            999,    ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL,   new VliDefinition(3, Encoding.HEXADECIMAL_ASCII)));
        fieldDefinitions.add(new VliFieldDefinition(    FieldName.RESERVED_PRIVATE,                             999,    ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL,   new VliDefinition(3, Encoding.HEXADECIMAL_ASCII)));
        fieldDefinitions.add(new VliFieldDefinition(    FieldName.RESERVED_PRIVATE,                             999,    ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL,   new VliDefinition(3, Encoding.HEXADECIMAL_ASCII)));
        fieldDefinitions.add(new VliFieldDefinition(    FieldName.RESERVED_PRIVATE,                             999,    ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL,   new VliDefinition(3, Encoding.HEXADECIMAL_ASCII)));
        fieldDefinitions.add(new VliFieldDefinition(    FieldName.RESERVED_PRIVATE,                             999,    ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL,   new VliDefinition(3, Encoding.HEXADECIMAL_ASCII)));
        fieldDefinitions.add(new VliFieldDefinition(    FieldName.RESERVED_PRIVATE,                             999,    ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL,   new VliDefinition(3, Encoding.HEXADECIMAL_ASCII)));
        fieldDefinitions.add(new VliFieldDefinition(    FieldName.RESERVED_PRIVATE,                             999,    ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL,   new VliDefinition(3, Encoding.HEXADECIMAL_ASCII)));
        fieldDefinitions.add(new VliFieldDefinition(    FieldName.RESERVED_PRIVATE,                             999,    ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL,   new VliDefinition(3, Encoding.HEXADECIMAL_ASCII)));
        fieldDefinitions.add(new VliFieldDefinition(    FieldName.RESERVED_PRIVATE,                             999,    ALPHANUMERIC_FIELD_ENCODING,    ContentType.ALPHANUMERIC_SPECIAL,   new VliDefinition(3, Encoding.HEXADECIMAL_ASCII)));
        fieldDefinitions.add(new FixedFieldDefinition(  FieldName.MESSAGE_AUTHENTICATION_CODE,                  8,      BYTE_FIELD_ENCODING,            ContentType.BYTES));

        return fieldDefinitions;
    }

}
