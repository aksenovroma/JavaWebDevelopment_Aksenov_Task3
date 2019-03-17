package by.epam.javatraning.aksenov.task1.util.binarydata;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BinaryFile {
    public static byte[] readBinaryFile(String fileName) {
        Path path = Paths.get(fileName);
        byte[] bytes = null;

        try {
            bytes = Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bytes;
    }

    public static void writeBinaryFile(byte[] bytes, String fileName) {
        Path path = Paths.get(fileName);
        try {
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
