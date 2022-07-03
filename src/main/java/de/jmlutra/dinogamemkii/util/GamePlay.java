package de.jmlutra.dinogamemkii.util;

import com.almasb.fxgl.dsl.FXGL;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameController;

public class GamePlay {

    public static float gameSpeed = 1.0f;
    public static float obstacleSpeed() {
        return gameSpeed;
    }

    public static void gameOver(int score) {
        Currency.scoreToCurrency(score);

        FXGL.showMessage("Game Over \nYou now have " + Currency.getCurrency() + "€", () -> {
            getGameController().startNewGame();
        });

    }

}
