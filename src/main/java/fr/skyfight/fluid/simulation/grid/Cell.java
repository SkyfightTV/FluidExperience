package fr.skyfight.fluid.simulation.grid;

import fr.skyfight.fluid.entities.Entity;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    private final List<Entity> entities;

    public Cell() {
        entities = new ArrayList<>();
    }

    public void clear() {
        entities.clear();
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }
}
