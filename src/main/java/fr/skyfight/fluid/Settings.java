package fr.skyfight.fluid;

import java.io.File;
import java.text.DecimalFormat;

import fr.skyfight.fluid.utils.Vector2D;

public class Settings {
    public static final DecimalFormat DF = new DecimalFormat("0.00");
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    public static final int OBJECTS = 2700;
    public static final int OBJECT_DIAMETER = 20;
    public static final Vector2D GRAVITY = new Vector2D(0, 2/9.81f);
    public static final int STEPS = 10;
    public static final float DT = 1f / STEPS;

    public static String path;
    public static File image;
    public static long lastTime = System.currentTimeMillis();
}
