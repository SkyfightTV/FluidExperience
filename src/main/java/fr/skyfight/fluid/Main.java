package fr.skyfight.fluid;

import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.net.URISyntaxException;

import processing.core.PApplet;

public class Main {
    private static String[] args;

    public static void main(String[] args) {
        Main.args = args;
        try {
            Settings.path = Main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            //remove the jar name
            Settings.path = Settings.path.substring(0, Settings.path.lastIndexOf("/"));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        if (args.length == 1)
            Settings.image = new File(args[0]);
        else
            throw new RuntimeException("Image not found");
        PApplet.main("fr.skyfight.fluid.Application");
    }

    public static void restartApplication() throws IOException {
        StringBuilder cmd = new StringBuilder();
        cmd.append(System.getProperty("java.home")).append(File.separator).append("bin").append(File.separator).append("java ");
        for (String jvmArg : ManagementFactory.getRuntimeMXBean().getInputArguments()) {
            cmd.append(jvmArg).append(" ");
        }
        cmd.append("-cp ").append(ManagementFactory.getRuntimeMXBean().getClassPath()).append(" ");
        cmd.append(Main.class.getName()).append(" ");
        for (String arg : args) {
            cmd.append(arg).append(" ");
        }
        Runtime.getRuntime().exec(cmd.toString());
        System.exit(0);
    }
}