package fr.skyfight.fluid.simulation;

import fr.skyfight.fluid.Settings;
import fr.skyfight.fluid.entities.Entity;
import fr.skyfight.fluid.entities.Water;
import fr.skyfight.fluid.simulation.grid.Grid;
import fr.skyfight.fluid.utils.Vector2D;
import processing.core.PApplet;
import processing.core.PImage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Simulation {
    public Grid grid;
    public PImage img;
    public List<Entity> entities = new ArrayList<>();
    public List<int []> savedPixels = new ArrayList<>();
    public boolean isFinished = false;
    public boolean isSaved = false;

    public Simulation(PImage img) {
        this.grid = new Grid();
        this.img = img;
    }

    public void load(SimulationSaver saver) throws IOException {
        isSaved = true;
        saver.load().forEach(line -> {
            String[] split = line.split(" ");
            int []pixels = new int[split.length];
            for (int j = 0; j < split.length; j++)
                pixels[j] = Integer.parseInt(split[j]);
            savedPixels.add(pixels);
        });
        saver.removeSave();
    }

    public void spawnEntity() {
        if (entities.size() < Settings.OBJECTS) {
            Settings.lastTime = System.currentTimeMillis();
            Water water = new Water(new Vector2D(Settings.WIDTH / 2, 100), Settings.OBJECT_DIAMETER, new Vector2D(20, 10));
            if (isSaved) {
                water.setPixels(savedPixels.get(entities.size()));
            }
            entities.add(water);
        }
        isFinished = entities.size() == Settings.OBJECTS;
    }

    public void simulate(PApplet p) {
        spawnEntity();
        entities.forEach(water -> water.applyVelocity(Settings.GRAVITY));
        grid.updateGrid(p, entities);
        for (int i = 0; i < Settings.STEPS; i++) {
            applyContraints();
            grid.solveCollisions();
            grid.updateGrid(p, entities);
        }
        entities.forEach(water -> {
            water.update(p, 1);
            ((Water) water).updateColor(p, img);
        });
    }

    public void applyContraints() {
        entities.forEach(Entity::applyContraints);
    }
}
