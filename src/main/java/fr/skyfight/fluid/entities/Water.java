package fr.skyfight.fluid.entities;

import fr.skyfight.fluid.Settings;
import fr.skyfight.fluid.utils.Vector2D;
import processing.core.PApplet;
import processing.core.PImage;

public class Water extends Entity {
    private float radius;

    public Water(Vector2D loc, float diameter, Vector2D velocity) {
        super(loc, velocity);
        this.radius = diameter / 2;
    }

    public void updateColor(PApplet p, PImage img) {
        int x = (int) loc.getX();
        int y = (int) loc.getY();
        int index = 0;
        int []imgPixels = img.pixels;
        for (int i = (int) (x - radius); i < x + radius; i++) {
            for (int j = (int) (y - radius); j < y + radius; j++) {
                double dx = i - x;
                double dy = j - y;
                double distanceSquared = dx * dx + dy * dy;
                if (i < 0 || i >= Settings.WIDTH || j < 0 || j >= Settings.HEIGHT) continue;
                if (distanceSquared > radius * radius) continue;
                if (pixels != null) {
                    if (index >= pixels.length)
                        continue;
                    int color = imgPixels[pixels[index]];
                    p.set(i, j, color);
                    index++;
                } else {
                    index = i + j * Settings.WIDTH;
                    int color = imgPixels[index];
                    if (color != 0) {
                        p.set(i, j, color);
                    }
                }
            }
        }
    }

    @Override
    public float getSize() {
        return this.radius;
    }
}
