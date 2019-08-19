package com.brandonlenz.iso8583;

import com.brandonlenz.iso8583.definitions.fields.names.FieldName;
import com.brandonlenz.iso8583.definitions.messages.Iso8583MessageDefinition;
import com.brandonlenz.iso8583.definitions.messages.SampleIso8583MessageDefinition;
import com.brandonlenz.iso8583.fields.Bitmap;
import com.brandonlenz.iso8583.fields.DataField;
import com.brandonlenz.iso8583.messages.Iso8583Message;
import com.brandonlenz.iso8583.messages.Iso8583MessageBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.stream.Collectors;
import javax.xml.bind.DatatypeConverter;

public class Demo {

    private static final Iso8583MessageDefinition definition = new SampleIso8583MessageDefinition();
    private static Iso8583MessageBuilder messageBuilder;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        printHeader();
        handleNewMessage();

        while(true) {
            try {
                printOptions();
                Option choice = Option.getOptionFromInt(scanner.nextInt());

                System.out.println(choice.getIntValue() + ": " + choice.toString());
                System.out.println();

                switch (choice) {
                    case NEW_MESSAGE:
                        handleNewMessage();
                        break;
                    case ADD_FIELD:
                        handleAddField();
                        break;
                    case REMOVE_FIELD:
                        handleRemoveField();
                        break;
                    case PRETTY_PRINT_RAW:
                        handlePrettyPrintRaw();
                        break;
                    case PRETTY_PRINT_MESSAGE:
                        handlePrettyPrintMessage();
                        break;
                    case PRINT_RAW_DATA:
                    System.out.println(DatatypeConverter.printHexBinary(messageBuilder.build().getRawData()));
                    break;
                    case PRINT_DATA:
                    System.out.println(messageBuilder.build().getData());
                    break;
                    case EXIT:
                        return; //exit this loop, and thusly the program.
                    case UNKNOWN:
                        //try again...
                        break;
                    default:
                        System.out.println(choice + " not yet implemented. Please select another option.");
                        break;
                }

                System.out.println();
                System.out.print("Successful operation, press enter to continue...");
                System.in.read();
            } catch (RuntimeException | IOException e) {
                System.out.println(e.getMessage());
                System.out.println("Operation unsuccessful, please try again.");
            }
        }
    }

    private static void printHeader() {
        System.out.println("IIIIIIIIII    SSSSSSSSSSSSSSS       OOOOOOOOO                           888888888      555555555555555555       888888888       333333333333333   ");
        System.out.println("I::::::::I  SS:::::::::::::::S    OO:::::::::OO                       88:::::::::88    5::::::::::::::::5     88:::::::::88    3:::::::::::::::33 ");
        System.out.println("I::::::::I S:::::SSSSSS::::::S  OO:::::::::::::OO                   88:::::::::::::88  5::::::::::::::::5   88:::::::::::::88  3::::::33333::::::3");
        System.out.println("II::::::II S:::::S     SSSSSSS O:::::::OOO:::::::O                 8::::::88888::::::8 5:::::555555555555  8::::::88888::::::8 3333333     3:::::3");
        System.out.println("  I::::I   S:::::S             O::::::O   O::::::O                 8:::::8     8:::::8 5:::::5             8:::::8     8:::::8             3:::::3");
        System.out.println("  I::::I   S:::::S             O:::::O     O:::::O                 8:::::8     8:::::8 5:::::5             8:::::8     8:::::8             3:::::3");
        System.out.println("  I::::I    S::::SSSS          O:::::O     O:::::O                  8:::::88888:::::8  5:::::5555555555     8:::::88888:::::8      33333333:::::3 ");
        System.out.println("  I::::I     SS::::::SSSSS     O:::::O     O:::::O ---------------   8:::::::::::::8   5:::::::::::::::5     8:::::::::::::8       3:::::::::::3  ");
        System.out.println("  I::::I       SSS::::::::SS   O:::::O     O:::::O -:::::::::::::-  8:::::88888:::::8  555555555555:::::5   8:::::88888:::::8      33333333:::::3 ");
        System.out.println("  I::::I          SSSSSS::::S  O:::::O     O:::::O --------------- 8:::::8     8:::::8             5::::: 58:::::8     8:::::8             3:::::3");
        System.out.println("  I::::I               S:::::S O:::::O     O:::::O                 8:::::8     8:::::8             5::::: 58:::::8     8:::::8             3:::::3");
        System.out.println("  I::::I               S:::::S O::::::O   O::::::O                 8:::::8     8:::::8 5555555     5::::: 58:::::8     8:::::8             3:::::3");
        System.out.println("II::::::II SSSSSSS     S:::::S O:::::::OOO:::::::O                 8::::::88888::::::8 5::::::55555:::::: 58::::::88888::::::8 3333333     3:::::3");
        System.out.println("I::::::::I S::::::SSSSSS:::::S  OO:::::::::::::OO                   88:::::::::::::88   55:::::::::::::55   88:::::::::::::88  3::::::33333::::::3");
        System.out.println("I::::::::I S:::::::::::::::SS     OO:::::::::OO                       88:::::::::88       55:::::::::55       88:::::::::88    3:::::::::::::::33 ");
        System.out.println("IIIIIIIIII  SSSSSSSSSSSSSSS         OOOOOOOOO                           888888888           555555555           888888888       333333333333333   ");
        System.out.println();
    }

    private static void printOptions() {
        System.out.println();
        for (Option option : Option.values()) {
            if (option != Option.UNKNOWN)
                System.out.println(option.getIntValue() + ": " + option.toString());
        }
        System.out.print("Select an option from above >");
    }

    private static void handleNewMessage(){
        System.out.println("A new ISO-8583 Message has been created for you.");
        DataField messageTypeIndicator = handleSetMessageTypeIndicator();
        messageBuilder = Iso8583Message.builder(definition, messageTypeIndicator);
        System.out.println("Your new message has been created");
    }

    private static DataField handleSetMessageTypeIndicator() {
        System.out.println("Your message defines MessageTypeId as follows:");
        System.out.println(definition.getMessageTypeIndicatorDefinition());
        System.out.print("Please enter the messageTypeId for your new message >");
        String mtiData = scanner.next();
        return definition.getMessageTypeIndicatorDefinition().getDataFieldBuilder().build(mtiData);
    }

    private static void handleAddField() {
        System.out.print("Enter the field number >");
        int fieldNumber = scanner.nextInt();
        System.out.println("Field " + fieldNumber +" is defined as follows:");
        System.out.println(definition.getFieldDefinition(fieldNumber));
        System.out.print("Enter the data for the field >");
        String fieldData = scanner.next();
        messageBuilder.setField(fieldNumber, fieldData);
    }

    private static void handleRemoveField() {
        System.out.print("Enter the field number to remove >");
        int fieldNumber = scanner.nextInt();
        messageBuilder.removeField(fieldNumber);
    }

    private static void handlePrettyPrintRaw() {
        List<DataField> allFields = getAllDataFields(messageBuilder.build());
        int prettyPrintOffset = getPrettyPrintOffset(allFields);

        for (DataField dataField : allFields) {
            System.out.format("%-" + prettyPrintOffset + "s: %s", dataField.getName(), DatatypeConverter.printHexBinary(dataField.getRawData()));
            System.out.println();
        }
    }

    private static void handlePrettyPrintMessage() {
        List<DataField> allFields = getAllDataFields(messageBuilder.build());
        int prettyPrintOffset = getPrettyPrintOffset(allFields);

        for (DataField dataField : allFields) {
            System.out.format("%-" + prettyPrintOffset + "s: %s", dataField.getName(), dataField.getData());
            System.out.println();
            if(dataField instanceof Bitmap) {
                Bitmap bitmap = (Bitmap) dataField;
                System.out.format("%-" + prettyPrintOffset + "s: %s", "+--> BASE 2", bitmap.getBinaryRepresentation());
                System.out.println();
                System.out.format("%-" + prettyPrintOffset + "s: %s", "+--> FIELDS SET", bitmap.getSetBits());
                System.out.println();
            }
        }
    }

    private static List<DataField> getAllDataFields(Iso8583Message message) {
        List<DataField> allFields = new ArrayList<>();
        allFields.add(message.getMessageTypeIndicator());
        allFields.add(message.getPrimaryBitmap());
        allFields.addAll(message.getDataFields()
                .entrySet()
                .stream()
                .sorted(Comparator.comparingInt(Entry::getKey))
                .map(Entry::getValue)
                .collect(Collectors.toList()));
        return allFields;
    }

    private static int getPrettyPrintOffset(List<DataField> dataFields) {
        return dataFields.stream()
                .map(DataField::getName)
                .map(FieldName::toString)
                .map(String::length)
                .mapToInt(Integer::intValue)
                .max()
                .orElse(20) + 5;
    }

    enum Option {
        NEW_MESSAGE(1),
        ADD_FIELD(2),
        REMOVE_FIELD(3),
        PRETTY_PRINT_RAW(4),
        PRETTY_PRINT_MESSAGE(5),
        PRINT_RAW_DATA(6),
        PRINT_DATA(7),
        EXIT(9),
        UNKNOWN(-1);

        private int intValue;

        Option(int value) {
            this.intValue = value;
        }

        public int getIntValue() {
            return intValue;
        }

        public static Option getOptionFromInt(int intValue) {
            return Arrays.stream(Option.values())
                    .filter(o -> o.intValue == intValue)
                    .findFirst()
                    .orElse(UNKNOWN);
        }
    }
}



