package com.brandonlenz.iso8583;

import com.brandonlenz.iso8583.building.messages.Iso8583MessageBuilder;
import com.brandonlenz.iso8583.definitions.messages.SampleIso8583MessageDefinition;
import com.brandonlenz.iso8583.fields.Bitmap;
import com.brandonlenz.iso8583.fields.DataField;
import com.brandonlenz.iso8583.messages.Iso8583Message;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import javax.xml.bind.DatatypeConverter;

public class Main { //TODO: log4j?

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
                    System.out.println(DatatypeConverter.printHexBinary(messageBuilder.getMessage().getRawData()));
                    break;
                    case PRINT_DATA:
                    System.out.println(messageBuilder.getMessage().getData());
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
                System.out.print("Successful operation, press any key to continue...");
                System.in.read();
            } catch (RuntimeException | IOException e) {
                System.out.println(e.getStackTrace());
                System.out.println("Operation unsuccessful, please try again.");
            }
        }
    }

    private static void printHeader() {
        System.out.println("######################################################################################################");
        System.out.println("######################################################################################################");
        System.out.println("###          ###          ###          ###########          ###          ###          ###          ###");
        System.out.println("######   #######   ####   ###   ####   ###########   ####   ###   ##########   ####   ##########   ###");
        System.out.println("######   #######   ##########   ####   ###########   ####   ###   ##########   ####   ##########   ###");
        System.out.println("######   #######   ##########   ####   ###########   ####   ###   ##########   ####   ##########   ###");
        System.out.println("######   #######          ###   ####   ###     ###          ###          ###          ######       ###");
        System.out.println("######   ##############   ###   ####   ###########   ####   ##########   ###   ####   ##########   ###");
        System.out.println("######   ##############   ###   ####   ###########   ####   ##########   ###   ####   ##########   ###");
        System.out.println("######   #######   ####   ###   ####   ###########   ####   ##########   ###   ####   ##########   ###");
        System.out.println("###          ###          ###          ###########          ###          ###          ###          ###");
        System.out.println("######################################################################################################");
        System.out.println("######################################################################################################");
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
        messageBuilder = new Iso8583MessageBuilder(new SampleIso8583MessageDefinition());
        System.out.println("A new ISO-8583 Message has been created for you.");
        handleSetMessageTypeIndicator();
        System.out.println("Your new message has been created");
    }

    private static void handleSetMessageTypeIndicator() {
        System.out.println("Your message defines MessageTypeId as follows:");
        System.out.println(messageBuilder.getMessageDefinition().getMessageTypeIndicatorDefinition());
        System.out.print("Please enter the messageTypeId for your new message >");
        String mtiData = scanner.next();
        messageBuilder.setMessageTypeIndicator(mtiData);
    }

    private static void handleAddField() {
        System.out.print("Enter the field number >");
        int fieldNumber = scanner.nextInt();
        System.out.println("Field " + fieldNumber +" is defined as follows:");
        System.out.println(messageBuilder.getMessageDefinition().getFieldDefinition(fieldNumber));
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
        List<DataField> allFields = getAllDataFields(messageBuilder.getMessage());

        for (DataField dataField : allFields) {
            System.out.format("%-20s: %s", dataField.getName(), DatatypeConverter.printHexBinary(dataField.getRawData()));
            System.out.println();
        }
    }

    private static void handlePrettyPrintMessage() {
        List<DataField> allFields = getAllDataFields(messageBuilder.getMessage());

        for (DataField dataField : allFields) {
            System.out.format("%-20s: %s", dataField.getName(), dataField.getData());
            System.out.println();
            if(dataField instanceof Bitmap) {
                Bitmap bitmap = (Bitmap) dataField;
                System.out.format("%-20s: %s", "", bitmap.getBinaryRepresentation());
                System.out.println();
                System.out.format("%-20s: %s", "", bitmap.getSetBits());
                System.out.println();
            }
        }
    }

    private static List<DataField> getAllDataFields(Iso8583Message message) {
        List<DataField> allFields = new ArrayList<>();
        allFields.add(message.getMessageTypeIndicator());
        allFields.add(message.getPrimaryBitmap());
        for (DataField dataField : message.getDataFields()) {
            if (dataField.getRawData() != null) {
                allFields.add(dataField);
            }
        }
        return allFields;
    }
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

