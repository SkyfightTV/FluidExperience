package fr.skyfight.fluid.simulation;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.List;

import fr.skyfight.fluid.Settings;
import fr.skyfight.fluid.utils.Vector2D;

public class SimulationSaver {
    public boolean isSaving = false;
    public File out;
    public Thread saveThread = null;

    public SimulationSaver(File out) {
        this.out = out;
    }

    public void save(Simulation sim) throws IOException {
        if (isSaving || saveThread != null)
            return;
        if (out.exists()) 
            removeSave();
        out.createNewFile();
        System.out.println("Saving " + sim.entities.size() + " entities...");
        this.saveThread = new Thread(() -> saveThread(sim));
        this.saveThread.start();
    }

    private void saveThread(Simulation sim) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        isSaving = true;
        sim.entities.forEach(entity -> {
            try {
                StringBuilder str = new StringBuilder();
                Vector2D location = entity.getLocation();
                float radius = entity.getSize();
                int x = (int) location.getX();
                int y = (int) location.getY();
                for (int i = x - (int) radius; i < x + radius; i++) {
                    for (int j = y - (int) radius; j < y + radius; j++) {
                        double dx = i - x;
                        double dy = j - y;
                        double distanceSquared = dx * dx + dy * dy;
                        if (distanceSquared > radius * radius) continue;
                        if (i < 0 || j < 0 || i >= Settings.WIDTH || j >= Settings.HEIGHT)
                            continue;
                        int index = i + j * Settings.WIDTH;
                        str.append(index).append(" ");
                    }
                }
                str.append("\n");
                Files.write(out.toPath(), str.toString().getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public List<String> load() throws IOException {
        return Files.readAllLines(out.toPath());
    }

    public void removeSave() {
        out.delete();
    }
}
