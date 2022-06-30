package de.jmlutra.dinogamemkii.util;

public class Background {

    public static int currentBackgroundID = 1;

    public static String currentBackgroundPath() {
        return "worlds/" + currentBackgroundID + "/";
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
