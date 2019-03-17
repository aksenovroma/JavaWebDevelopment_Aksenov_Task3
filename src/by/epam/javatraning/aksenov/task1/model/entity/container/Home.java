package by.epam.javatraning.aksenov.task1.model.entity.container;

import by.epam.javatraning.aksenov.task1.model.entity.Equipment;
import by.epam.javatraning.aksenov.task1.model.exception.logic.HomeEquipmentWrongException;

import java.io.Externalizable;
import java.io.Serializable;

public interface Home {
    void setEquipment(Equipment[] equipment) throws HomeEquipmentWrongException;

    Equipment[] getEquipment();

    Equipment get(int index);

    void set(Equipment e, int index);

    void add(Equipment e);

    void remove(Equipment e);

    boolean atHome(Equipment e);

    int size();

    boolean isEmpty();
}
