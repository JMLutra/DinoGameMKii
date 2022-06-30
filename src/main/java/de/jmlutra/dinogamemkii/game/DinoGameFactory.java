package de.jmlutra.dinogamemkii.game;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.OffscreenCleanComponent;
import com.almasb.fxgl.dsl.components.ProjectileComponent;
import com.almasb.fxgl.entity.*;
import com.almasb.fxgl.entity.components.IrremovableComponent;
import com.almasb.fxgl.entity.components.TransformComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyDef;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;
import com.almasb.fxgl.texture.Texture;
import de.jmlutra.dinogamemkii.util.Avatar;
import de.jmlutra.dinogamemkii.util.Background;
import de.jmlutra.dinogamemkii.util.EntityType;
import de.jmlutra.dinogamemkii.util.GamePlay;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class DinoGameFactory implements EntityFactory {

    @Spawns("Background")
    public Entity newBackground(SpawnData data) {
        return FXGL.entityBuilder()
                .type(EntityType.BACKGROUND)
                .view(Background.currentBackground())
                .with(new IrremovableComponent())
                .zIndex(-1)
                .build();
    }

    @Spawns("Ground")
    public Entity newGround(SpawnData data) {
        var view = new Rectangle(1400, 10, Color.RED);
        view.setVisible(false);


        return FXGL.entityBuilder(data)
                .type(EntityType.GROUND)
                .viewWithBBox(view)
                .with(new IrremovableComponent())
                .with(new PhysicsComponent())
                .collidable()
                .build();
    }

    @Spawns("Dino")
    public Entity newDino(SpawnData data){

        PhysicsComponent dinoPhysics = new PhysicsComponent();
        dinoPhysics.setFixtureDef(new FixtureDef().friction(0));
        BodyDef bd = new BodyDef();
        bd.setFixedRotation(true);
        bd.setType(BodyType.DYNAMIC);
        dinoPhysics.addGroundSensor(new HitBox(new Point2D(5, 150), BoundingShape.box(175, 10)));
        dinoPhysics.setBodyDef(bd);

         return FXGL.entityBuilder(data)
                .type(EntityType.PLAYER)
                .with(new OffscreenCleanComponent())
                .with(dinoPhysics)
                .with(new DinoComponent())
                .collidable()
                .build();

    }

    @Spawns("ObstacleGroundLarge")
    public Entity newObstacleGroundLarge(SpawnData data) {

        PhysicsComponent obstaclePhysics = new PhysicsComponent();
        obstaclePhysics.setFixtureDef(new FixtureDef().friction(0));
        BodyDef bd = new BodyDef();
        bd.setType(BodyType.DYNAMIC);
        obstaclePhysics.setBodyDef(bd);

        Texture texture = FXGL.getAssetLoader().loadTexture(Background.currentObstacleGroundLarge());

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
