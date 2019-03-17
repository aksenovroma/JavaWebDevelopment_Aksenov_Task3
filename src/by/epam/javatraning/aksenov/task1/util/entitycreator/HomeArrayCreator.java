package by.epam.javatraning.aksenov.task1.util.entitycreator;

import by.epam.javatraning.aksenov.task1.model.entity.container.Home;
import by.epam.javatraning.aksenov.task1.model.entity.container.HomeArray;

public class HomeArrayCreator implements HomeCreator {
    @Override
    public Home create() {
        return new HomeArray();
    }
}
