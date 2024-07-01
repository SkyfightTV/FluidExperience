package fr.skyfight.fluid.entities;

import fr.skyfight.fluid.Settings;
import fr.skyfight.fluid.utils.Vector2D;
import processing.core.PApplet;

public class Entity {
    protected Vector2D loc;
    protected Vector2D lastLoc;
    protected Vector2D length;
    protected Vector2D velocity;
    protected int []pixels;

    public Entity(Vector2D loc, Vector2D velocity) {
        this.pixels = null;
        this.loc = loc;
        this.lastLoc = loc;
        this.length = new Vector2D();
        this.velocity = velocity;
    }

    public void update(PApplet p, float dt) {
        p.noStroke();
        this.length.set(loc, lastLoc);
        this.lastLoc = this.loc;
        this.loc = new Vector2D(this.loc.getX() + this.length.getX(), this.loc.getY() + this.length.getY());
        this.loc.addY(this.velocity.getY() * dt * dt);
        this.loc.addX(this.velocity.getX() * dt * dt);
        this.velocity.reset();
    }

    public void applyContraints() {
        double size = getSize();
        if (loc.getX() < size) {
            loc.setX(size);
        }
        if (loc.getX() > Settings.WIDTH - size) {
            loc.setX(Settings.WIDTH - size);
        }
        if (loc.getY() < size / 2) {
            loc.setY(size);
            velocity.setY(0);
        }
        if (loc.getY() > Settings.HEIGHT - size) {
            loc.setY(Settings.HEIGHT - size);
        }
    }

    public void applyVelocity(Vector2D velocity) {
        this.velocity.addX(velocity.getX());
        this.velocity.addY(velocity.getY());
    }
    
    public float getSize() {
        return 0;
    }

    public int[] getPixels() {
        return pixels;
    }

    public void setPixels(int[] pixels) {
        this.pixels = pixels;
    }

    public Vector2D getLocation() {
        return loc;
    }

    public void setLocation(Vector2D loc) {
        this.loc = loc;
    }

    public Vector2D getLastLocation() {
        return lastLoc;
    }

    public void setLastLocation(Vector2D lastLoc) {
        this.lastLoc = lastLoc;
    }

    public Vector2D getLength() {
        return length;
    }

    public void setLength(Vector2D length) {
        this.length = length;
    }

    public Vector2D getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2D velocity) {
        this.velocity = velocity;
    }
}
