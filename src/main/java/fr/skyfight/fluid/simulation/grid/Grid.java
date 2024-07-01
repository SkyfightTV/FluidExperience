package fr.skyfight.fluid.simulation.grid;

import fr.skyfight.fluid.Settings;
import fr.skyfight.fluid.entities.Entity;
import fr.skyfight.fluid.utils.Vector2D;
import processing.core.PApplet;

import java.util.List;

public class Grid {
    public Cell[][] grid;
    public static final int CELL_NUMB_X = 20;
    public static final int CELL_NUMB_Y = 20;
    

    public Grid() {
        grid = new Cell[CELL_NUMB_X][CELL_NUMB_Y];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = new Cell();
            }
        }
    }

    public void updateGrid(PApplet p, List<Entity> activeEntities) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j].clear();
            }
        }
        activeEntities.forEach(entity -> {
            Vector2D location = entity.getLocation();
            int x = (int) (location.getX() / (Settings.WIDTH / CELL_NUMB_X));
            int y = (int) (location.getY() / (Settings.HEIGHT / CELL_NUMB_Y));
            if (x >= CELL_NUMB_X) x = CELL_NUMB_X - 1;
            if (y >= CELL_NUMB_Y) y = CELL_NUMB_Y - 1;
            if (x < 0) x = 0;
            if (y < 0) y = 0;
            grid[x][y].addEntity(entity);
        });
    }

    public void solveCollisions() {
        //Use grid
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {
                Cell cell = grid[x][y];
                for (int dx = -1; dx <= 1; dx++) {
                    for (int dy = -1; dy <= 1; dy++) {
                        if (x + dx >= 0 && x + dx < grid.length && y + dy >= 0 && y + dy < grid[x].length) {
                            Cell o = grid[x + dx][y + dy];
                            for (Entity entity1 : cell.getEntities()) {
                                for (Entity entity2 : o.getEntities()) {
                                    if (entity1 != entity2) {
                                        Vector2D to_obj = new Vector2D(entity1.getLocation(), entity2.getLocation());
                                        double distance = to_obj.getMagnitude();
                                        if (distance < entity1.getSize() + entity2.getSize()) {
                                            to_obj.normalize();
                                            double delta = (entity1.getSize() + entity2.getSize()) - distance;
                                            entity1.getLocation().add(0.5f * delta * to_obj.getX(), 0.5f * delta * to_obj.getY());
                                            entity2.getLocation().remove(0.5f * delta * to_obj.getX(), 0.5f * delta * to_obj.getY());
                                            entity1.applyContraints();
                                            entity2.applyContraints();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
