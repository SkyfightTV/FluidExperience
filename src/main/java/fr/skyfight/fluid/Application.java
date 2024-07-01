package fr.skyfight.fluid;

import java.io.File;
import java.io.IOException;

import fr.skyfight.fluid.simulation.Simulation;
import fr.skyfight.fluid.simulation.SimulationSaver;
import processing.core.PApplet;
import processing.core.PImage;

public class Application extends PApplet {
    public Simulation simulation;
    public SimulationSaver saver;
    public PImage img;
    public File saveFile;

    public void settings() {
        size(Settings.WIDTH, Settings.HEIGHT);
    }
    
    public void setup() {
        background(0);

        saveFile = new File(Settings.path + "\\data.txt");
        if (Settings.image == null)
            throw new RuntimeException("Image not found");
        img = loadImage(Settings.image.getAbsolutePath());
        img.resize(Settings.WIDTH, Settings.HEIGHT);
        img.loadPixels();

        simulation = new Simulation(img);
        saver = new SimulationSaver(saveFile);

        if (saveFile.exists()) {
           try {
            simulation.load(saver);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void draw() {
        background(0);

        if (simulation.isFinished && saver.saveThread == null && !simulation.isSaved) {
            try {
                saver.save(simulation);
            } catch (IOException e) {
                e.printStackTrace();
            }
            text("Saving waters data...", 10, 20);
        } else {
            if (saver.saveThread != null && !saver.saveThread.isAlive()) {
                try {
                    Main.restartApplication();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return;
            }
            float start = System.nanoTime();
            simulation.simulate(this);
            updatePixels();
            float time = (System.nanoTime() - start) / 1000000f;
            text("Simulation time: " + Settings.DF.format(time) + "ms", 10, 20);
            text("Objects: " + this.simulation.entities.size(), 10, 30);
            String topmsg = "Create model in progress...";
            if (simulation.isSaved) {
                topmsg = "Reproduice model in progress...";
            } else if (simulation.isFinished) {
                topmsg = "Model created!";
            }
            text(topmsg, 10, 40, Settings.WIDTH - 20, Settings.HEIGHT - 20);
        }
    }
}
