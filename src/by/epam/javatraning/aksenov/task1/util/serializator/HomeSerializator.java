package by.epam.javatraning.aksenov.task1.util.serializator;

import by.epam.javatraning.aksenov.task1.model.entity.container.Home;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class HomeSerializator {
    public static void serialize(Home home, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(home);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Home deserialize(String fileName) {
        Home home = null;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            home = (Home) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return home;
    }
}
