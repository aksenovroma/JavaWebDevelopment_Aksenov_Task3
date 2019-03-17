package by.epam.javatraning.aksenov.task1.controller;

import by.epam.javatraning.aksenov.task1.model.entity.Equipment;
import by.epam.javatraning.aksenov.task1.model.entity.container.Home;
import by.epam.javatraning.aksenov.task1.model.exception.logic.HomeEquipmentWrongException;
import by.epam.javatraning.aksenov.task1.util.Converter;
import by.epam.javatraning.aksenov.task1.util.EquipmentBuffer;
import by.epam.javatraning.aksenov.task1.util.PrinterCreator;
import by.epam.javatraning.aksenov.task1.util.UserPrinter;
import by.epam.javatraning.aksenov.task1.util.binarydata.BinaryFile;
import by.epam.javatraning.aksenov.task1.util.entitycreator.EquipmentCreator;
import by.epam.javatraning.aksenov.task1.util.entitycreator.HomeListCreator;
import by.epam.javatraning.aksenov.task1.util.exception.EmptyFileException;
import by.epam.javatraning.aksenov.task1.util.exception.NoValidStringException;
import by.epam.javatraning.aksenov.task1.util.serializator.HomeSerializator;
import by.epam.javatraning.aksenov.task1.util.textdata.DataParser;
import by.epam.javatraning.aksenov.task1.util.textdata.DataReader;
import by.epam.javatraning.aksenov.task1.util.textdata.DataValidator;
import by.epam.javatraning.aksenov.task1.view.Printable;
import by.epam.javatraning.aksenov.task1.view.PrinterType;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Program implements the hierarchy of electrical equipment in the house.
 * <p>
 * https://github.com/aksenovroma/JavaWebDevelopment_Aksenov_Task1
 *
 * @author aksenov
 * @version 2.0
 */

public class Main {
    private static final Logger log = Logger.getRootLogger();

    private static final String INPUT_FILE = "input/inputTestFile1.txt";
    private static final String OUTPUT_FILE = "output/outputFile.txt";
    private static final String SERIALIZATION_FILE = "input/serializator.bin";
    private static final String BINARY_FILE = "input/testBinary.bin";

    public static void main(String[] args) {
        PrinterType printerType = UserPrinter.select(OUTPUT_FILE);
        Printable printer = PrinterCreator.create(printerType);

        Home homeArray1 = new HomeListCreator().create();
        Home homeArray2 = null;
        Home homeArray3 = new HomeListCreator().create();
        String text = "";

        /*
        data from text file
         */
        try {

            text = DataReader.readFile(INPUT_FILE);

            List<String> validStringsList = DataValidator.getValidString(text);

            List<EquipmentBuffer> equipmentTypes = DataParser.parseStrToEquipmentBuffer(validStringsList);
            List<Equipment> equipment1 = EquipmentCreator.create(equipmentTypes);

            homeArray1.setEquipment(equipment1.toArray(new Equipment[0]));

            printer.print(homeArray1);

        } catch (EmptyFileException | NoValidStringException | HomeEquipmentWrongException e) {
            log.error(e);
        }


        /*
        serialized object
         */
        HomeSerializator.serialize(homeArray1, SERIALIZATION_FILE);
        homeArray2 = HomeSerializator.deserialize(SERIALIZATION_FILE);

        printer.print(homeArray2);


        /*
        data from binary file
         */
        BinaryFile.writeBinaryFile(text.getBytes(), BINARY_FILE);
        byte[] bytes = BinaryFile.readBinaryFile(BINARY_FILE);
        text = Converter.bytesToString(bytes);

        List<String> validStringsList = null;
        try {
            validStringsList = DataValidator.getValidString(text);
        } catch (NoValidStringException e) {
            log.error(e);
        }

        List<EquipmentBuffer> equipmentTypes = DataParser.parseStrToEquipmentBuffer(validStringsList);
        List<Equipment> equipment2 = EquipmentCreator.create(equipmentTypes);

        try {
            homeArray3.setEquipment(equipment2.toArray(new Equipment[0]));
        } catch (HomeEquipmentWrongException e) {
            log.error(e);
        }

        printer.print(homeArray3);
    }
}