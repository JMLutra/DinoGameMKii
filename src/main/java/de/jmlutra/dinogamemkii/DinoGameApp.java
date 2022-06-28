package de.jmlutra.dinogamemkii;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.components.ProjectileComponent;
import com.almasb.fxgl.entity.Entity;
import de.jmlutra.dinogamemkii.game.DinoGameFactory;
import javafx.geometry.Point2D;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameWorld;
import static com.almasb.fxgl.dsl.FXGLForKtKt.spawn;

public class DinoGameApp extends GameApplication {

    private Entity player;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setWidth(1280);
        gameSettings.setHeight(720);
        gameSettings.setTitle("Dino Game MkII");
        gameSettings.setVersion("Beta");
        //gameSettings.setMainMenuEnabled(true);

    }

    @Override
    protected void initGame() {
        getGameWorld().addEntityFactory(new DinoGameFactory());
        spawn("Background");

        player = getGameWorld().spawn("Dino", 100, 400);

    }
}
