package de.jmlutra.dinogamemkii.util;

public class Background {

    public static int currentBackgroundID = 2;

    public static String currentBackgroundPath() { //setzt den Pfad
        return "worlds/" + currentBackgroundID +"/";
    }

    public static void changeBackground(int id) { //damit mans ändern kann
        currentBackgroundID = id;
    }

    public static String currentBackground() {
        return currentBackgroundPath() + "background.png";
    }
    public static String currentObstacleGroundLarge() {
        return currentBackgroundPath() + "obstacleGroundLarge.png";
    }

    public static String currentObstacleGroundSmall() {
        return currentBackgroundPath() + "obstacleGroundSmall.png";
    }
}
