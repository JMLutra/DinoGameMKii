package de.jmlutra.dinogamemkii;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.ProjectileComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.PhysicsComponent;
import de.jmlutra.dinogamemkii.game.DinoComponent;
import de.jmlutra.dinogamemkii.game.DinoGameFactory;
import de.jmlutra.dinogamemkii.game.DinoSceneFactory;
import de.jmlutra.dinogamemkii.game.ObstacleComponent;
import de.jmlutra.dinogamemkii.util.EntityType;
import de.jmlutra.dinogamemkii.util.GamePlay;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.Map;
import java.util.Random;

import static com.almasb.fxgl.dsl.FXGLForKtKt.*;

public class DinoGameApp extends GameApplication {

    private Entity player;

    private Entity ground;

    private int framecount;

    public static void main(String[] args) { //Eintritspunkt in das Programm
        launch(args);
    }

    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setWidth(1280); // 1280x720
        gameSettings.setHeight(720);
        gameSettings.setTitle("Dino Game MkII");
        gameSettings.setVersion("1.0");
        gameSettings.setMainMenuEnabled(true);
        gameSettings.setSceneFactory(new DinoSceneFactory()); //Eignes Haupt Menü
        gameSettings.setTicksPerSecond(25); //25 FPS

    }

    @Override
    protected void initGame() {
        getGameWorld().addEntityFactory(new DinoGameFactory()); //
        spawn("Background");

        ground = getGameWorld().spawn("Ground", 0, 600);

        player = getGameWorld().spawn("Dino", 150, 400);

        player.getComponent(PhysicsComponent.class).onGroundProperty().addListener((o, oldValue, newValue) -> {
            player.getComponent(DinoComponent.class).setGround(newValue);
            System.out.println(newValue);
        });

    }

    @Override
    protected void initPhysics() {
        FXGL.getPhysicsWorld().setGravity(0,2500);

        onCollisionBegin(EntityType.OBSTACLEGROUND, EntityType.PLAYER, (obstacle, player) -> {
            System.out.println("Collision");

            player.getComponent(PhysicsComponent.class).setVelocityX(obstacle.getComponent(PhysicsComponent.class).getVelocityX());
            GamePlay.gameOver(getWorldProperties().getInt("score"));
            return null;
        });
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("score", 0);
        //vars.put("lives", 2);
    }

    @Override
    protected void initInput() {

        onKey(KeyCode.SPACE, "jump", () -> {
            player.getComponent(DinoComponent.class).jump();
            return null;
        });

        onKey(KeyCode.UP, "jump (alternative)", () -> {
            player.getComponent(DinoComponent.class).jump();
            return null;
        });
    }

    @Override
    protected void initUI() {
        Text scoreLabel = getUIFactoryService().newText("Score", Color.BLACK, 22);
        Text scoreValue = getUIFactoryService().newText("", Color.BLACK, 22);
//        Text livesLabel = getUIFactoryService().newText("Lives", Color.BLACK, 22);
//        Text livesValue = getUIFactoryService().newText("", Color.BLACK, 22);

        scoreLabel.setTranslateX(20);
        scoreLabel.setTranslateY(20);

        scoreValue.setTranslateX(90);
        scoreValue.setTranslateY(20);

//        livesLabel.setTranslateX(getAppWidth() - 100);
//        livesLabel.setTranslateY(20);
//
//        livesValue.setTranslateX(getAppWidth() - 30);
//        livesValue.setTranslateY(20);

        scoreValue.textProperty().bind(getWorldProperties().intProperty("score").asString());
//        livesValue.textProperty().bind(getWorldProperties().intProperty("lives").asString());

        getGameScene().addUINodes(scoreLabel, scoreValue/*, livesLabel, livesValue*/);


    }

    @Override
    protected void onUpdate(double tpf) {
        GamePlay.gameSpeed += 0.001f;
        getWorldProperties().intProperty("score").set(getWorldProperties().intProperty("score").get() + Math.round(GamePlay.gameSpeed));
        if (!(framecount >= 12 /** GamePlay.gameSpeed*/)) {
            framecount++;
        } else {
            Random random = new Random();
            int randomNumber = random.nextInt(100);
            if (randomNumber % 5 == 0) {
                int randomNumber2 = random.nextInt( 2);
                switch (randomNumber2) {
                    case 1:
                        System.out.println("Obstacle1");
                        spawn("ObstacleGroundLarge", 1300, 500);
                        break;
                    case 2:
                        System.out.println("Obstacle2");
                        spawn("ObstacleGroundSmall", 1300, 500);
                        break;
                    default:
                        System.out.println("Obstacle3");
                        spawn("ObstacleGroundSmall", 1300, 500);
                        break;
                }
            }
            framecount = 0;
        }
    }
}
