package de.jmlutra.dinogamemkii.util;

public class Currency {
    private static int currency;

    public static void scoreToCurrency(int score) {
        currency += score/10;
    }

    public static int getCurrency() {
        return currency;
    }
}
