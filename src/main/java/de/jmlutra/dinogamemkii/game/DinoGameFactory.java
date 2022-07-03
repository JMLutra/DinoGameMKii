package de.jmlutra.dinogamemkii.game;

import com.almasb.fxgl.dsl.FXGL; //generell libery
import com.almasb.fxgl.dsl.components.OffscreenCleanComponent; //löscht sobald es aus dem bereich raus is
import com.almasb.fxgl.dsl.components.ProjectileComponent;
import com.almasb.fxgl.entity.*; //holt alles für die objekte
import com.almasb.fxgl.entity.components.IrremovableComponent; //für unlöschbare objekte
import com.almasb.fxgl.entity.components.TransformComponent;
import com.almasb.fxgl.physics.BoundingShape; //für die hitboxen
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent; // physik für die objekte
import com.almasb.fxgl.physics.box2d.dynamics.BodyDef; // Hitboxen erstellen
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;
import com.almasb.fxgl.texture.Texture; // zum laden von texturen
import de.jmlutra.dinogamemkii.game.ObstacleComponent;
import de.jmlutra.dinogamemkii.util.Avatar;
import de.jmlutra.dinogamemkii.util.Background; //ändern des Hintergrunds
import de.jmlutra.dinogamemkii.util.EntityType; // für kollisionserkennung
import de.jmlutra.dinogamemkii.util.GamePlay;
import javafx.geometry.Point2D; //zeichnen im 2d
import javafx.scene.paint.Color; //farbe
import javafx.scene.shape.Rectangle; //Rechteck

public class DinoGameFactory implements EntityFactory {

    @Spawns("Background")
    public Entity newBackground(SpawnData data) {
        return FXGL.entityBuilder()
                .type(EntityType.BACKGROUND) // KOllision erkennung
                .view(Background.currentBackground()) //lädt die Textur, bekommt den aktuellen Hintergrund aus der Klasse
                .with(new IrremovableComponent()) // kann man nicht entfernen
                .zIndex(-1) //setzt in der Zeichenebene eins nach Hinten (alles wird davor angezeigt)
                .build(); // erstellt am Ende die entity aus dem Builder
    }

    @Spawns("Ground")
    public Entity newGround(SpawnData data) {
        var view = new Rectangle(1400, 10, Color.RED); //erstellen des Rechtecks
        view.setVisible(false); //unsichtbar machen


        return FXGL.entityBuilder(data)
                .type(EntityType.GROUND)
                .viewWithBBox(view)
                .with(new IrremovableComponent())
                .with(new PhysicsComponent()) //kann mit der physiks engine kommunizieren
                .collidable() //objekte können damit kollidieren
                .build();
    }
    @Spawns("Dino") //hat eigene physik Eigenschaftten
    public Entity newDino(SpawnData data){

        PhysicsComponent dinoPhysics = new PhysicsComponent();
        dinoPhysics.setFixtureDef(new FixtureDef().friction(0)); //hat eine Reibung von 0
        BodyDef bd = new BodyDef();
        bd.setFixedRotation(true); //kann sich nicht drehen
        bd.setType(BodyType.DYNAMIC); // können ihn bewegen
        dinoPhysics.addGroundSensor(new HitBox(new Point2D(5, 150), BoundingShape.box(175, 10)));// zum erkennen ob er auf dem Boden steht
        dinoPhysics.setBodyDef(bd); //setzen der eigenen Body definition

        return FXGL.entityBuilder(data)
                .type(EntityType.PLAYER)
                .with(new OffscreenCleanComponent())
                .with(dinoPhysics) //Eigenen phsiks compenent nutzen
                .with(new DinoComponent()) //nutzt eigen erstelltes Dino Component
                .collidable()
                .build();

    }
    @Spawns("ObstacleGroundLarge") //bekommt eigene Physiks
    public Entity newObstacleGroundLarge(SpawnData data) {

        PhysicsComponent obstaclePhysics = new PhysicsComponent();
        obstaclePhysics.setFixtureDef(new FixtureDef().friction(0));
        BodyDef bd = new BodyDef();
        bd.setType(BodyType.DYNAMIC);
        obstaclePhysics.setBodyDef(bd);

        Texture texture = FXGL.getAssetLoader().loadTexture(Background.currentObstacleGroundLarge()); //nutzen eines texture loadersn

        return FXGL.entityBuilder(data)
                .type(EntityType.OBSTACLEGROUND)
                .view(texture)
                .bbox(new HitBox(BoundingShape.box(texture.getWidth(), texture.getHeight()))) //erstellen neue Hitbox. Wir auf hähe und breite der geladenen textur gesetzt, krigen wir von der textur
                .with(obstaclePhysics)
                .with(new ObstacleComponent())
                //.with(new OffscreenCleanComponent())
                .zIndex(-1)
                .rotate(0) //setzt die Rotation auf das normale was von der Textur geladen wird
                .collidable()
                .build();
    }

    @Spawns("ObstacleGroundSmall")
    public Entity newObstacleGroundSmall(SpawnData data) {

        PhysicsComponent obstaclePhysics = new PhysicsComponent();
        obstaclePhysics.setFixtureDef(new FixtureDef().friction(0));
        BodyDef bd = new BodyDef();
        bd.setType(BodyType.DYNAMIC);
        obstaclePhysics.setBodyDef(bd);

        Texture texture = FXGL.getAssetLoader().loadTexture(Background.currentObstacleGroundSmall());

        return FXGL.entityBuilder(data)
                .type(EntityType.OBSTACLEGROUND)
                .view(texture)
                .bbox(new HitBox(BoundingShape.box(texture.getWidth(), texture.getHeight())))
                .with(obstaclePhysics)
                .with(new ObstacleComponent())
                //.with(new OffscreenCleanComponent())
                .zIndex(-1)
                .rotate(0)
                .collidable()
                .build();
    }

    /*@Spawns("ObstacleAir")
    public Entity newObstacleGAir(SpawnData data) {

        PhysicsComponent obstaclePhysics = new PhysicsComponent();
        obstaclePhysics.setFixtureDef(new FixtureDef().friction(0));
        BodyDef bd = new BodyDef();
        bd.setType(BodyType.DYNAMIC);
        obstaclePhysics.setBodyDef(bd);

        Texture texture = FXGL.getAssetLoader().loadTexture(Background.currentObstacleAir());

        return FXGL.entityBuilder(data)
                .type(EntityType.OBSTACLEGROUND)
                .view(texture)
                .bbox(new HitBox(BoundingShape.box(texture.getWidth(), texture.getHeight())))
                .with(obstaclePhysics)
                .with(new ObstacleComponent())
                .with(new OffscreenCleanComponent())
                .zIndex(-1)
                .rotate(0)
                .collidable()
                .build();
    }*/
}