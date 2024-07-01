package fr.skyfight.fluid.utils;

public class Vector2D {

    private double x;
    private double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D(Vector2D vector2D, Vector2D lastVector2D) {
        this.x = vector2D.getX() - lastVector2D.getX();
        this.y = vector2D.getY() - lastVector2D.getY();
    }

    public Vector2D() {
        this.x = 0;
        this.y = 0;
    }

    public void add(Vector2D vector2D) {
        this.x += vector2D.getX();
        this.y += vector2D.getY();
    }

    public void remove(Vector2D vector2D) {
        this.x -= vector2D.getX();
        this.y -= vector2D.getY();
    }

    public void multiply(double value) {
        this.x *= value;
        this.y *= value;
    }

    public void divide(double value) {
        this.x /= value;
        this.y /= value;
    }

    public void add(double value) {
        this.x += value;
        this.y += value;
    }

    public void remove(double value) {
        this.x -= value;
        this.y -= value;
    }

    public void addX(double x) {
        this.x += x;
    }

    public void addY(double y) {
        this.y += y;
    }

    public void removeX(double x) {
        this.x -= x;
    }

    public void removeY(double y) {
        this.y -= y;
    }

    public void add(double x, double y) {
        this.x += x;
        this.y += y;
    }

    public void remove(double x, double y) {
        this.x -= x;
        this.y -= y;
    }

    public void reset() {
        this.x = 0;
        this.y = 0;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void set(Vector2D vector2D) {
        this.x = vector2D.getX();
        this.y = vector2D.getY();
    }

    public void set(Vector2D vector2D, Vector2D lastVector2D) {
        this.x = vector2D.getX() - lastVector2D.getX();
        this.y = vector2D.getY() - lastVector2D.getY();
    }

    public double getMagnitude() {
        return (double) Math.sqrt(x * x + y * y);
    }

    public void normalize() {
        double magnitude = getMagnitude();
        x /= magnitude;
        y /= magnitude;
    }

    @Override
    public String toString() {
        return "Vector2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
