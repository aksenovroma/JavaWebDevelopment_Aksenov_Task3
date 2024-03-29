package by.epam.javatraning.aksenov.task1.model.entity.container;

import by.epam.javatraning.aksenov.task1.model.entity.Equipment;
import by.epam.javatraning.aksenov.task1.model.exception.logic.HomeEquipmentWrongException;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Arrays;
import java.util.List;

/**
 * @author aksenov
 * @version 1.0
 *
 * Entity class for storing information about electrical equipment in the house
 * Class storing textdata in equipment array
 * Class Home has methods for adding and removing equipment from house
 */

public class HomeArray implements Home, Externalizable {
    private static final String NULL_POINTER_EXC = "argument can't be null";

    private Equipment[] equipment = new Equipment[0];

    public HomeArray() {
    }

    public HomeArray(Equipment... equipment) {
        if (equipment != null) {
            this.equipment = equipment;
        }
    }

    public HomeArray(List<Equipment> equipment) {
        if (equipment != null) {
            this.equipment = new Equipment[equipment.size()];

            for (int i = 0; i < size(); i++) {
                this.equipment[i] = equipment.get(i);
            }
        }
    }

    public HomeArray(Home home) {
        if (home != null) {
            this.equipment = (home.getEquipment()).clone();
        }
    }

    @Override
    public Equipment[] getEquipment() {
        return equipment;
    }

    @Override
    public void setEquipment(Equipment[] equipment) throws HomeEquipmentWrongException{
        if (equipment == null) {
            throw new HomeEquipmentWrongException(NULL_POINTER_EXC);
        }
        this.equipment = equipment;
    }

    @Override
    public void set(Equipment e, int index) {
        if (e != null && (index >= 0 && index < equipment.length)) {
            equipment[index] = e;
        }
    }

    @Override
    public void add(Equipment e) {
        if (e == null){
            return;
        }

        Equipment[] newEquipment = new Equipment[equipment.length + 1];

        for (int i = 0; i < equipment.length; i++) {
            newEquipment[i] = equipment[i];
        }
        newEquipment[equipment.length] = e;

        equipment = newEquipment;
    }

    @Override
    public void remove(Equipment e) {
        if (e == null){
            return;
        }

        if (atHome(e)) {
            Equipment[] newEquipment = new Equipment[equipment.length - 1];

            for (int i = 0, j = 0; i < equipment.length; i++, j++) {
                if (!(e.equals(equipment[i]))) {
                    newEquipment[j] = equipment[i];
                } else {
                    j--;
                }
            }
            equipment = newEquipment;
        }
    }

    @Override
    public boolean atHome(Equipment e) {
        if (e == null) {
            return false;
        }

        for (Equipment device : equipment) {
            if (e.equals(device)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Equipment get(int index) {
        if (index >= 0 && index < equipment.length) {
            return equipment[index];
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return equipment.length == 0;
    }

    @Override
    public int size() {
        return equipment.length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HomeArray home = (HomeArray) o;
        return Arrays.equals(equipment, home.equipment);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(equipment);
    }

    @Override
    public String toString() {
        return "Home{" +
                "equipment=" + Arrays.toString(equipment) +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeObject(this.equipment);
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        equipment = (Equipment[]) objectInput.readObject();
    }
}
