package by.epam.javatraning.aksenov.task1.util;

import java.nio.charset.StandardCharsets;

public class Converter {
    public static String bytesToString(byte[] bytes) {
        if (bytes != null) {
            return new String(bytes, StandardCharsets.UTF_8);
        }
        return null;
    }
}
