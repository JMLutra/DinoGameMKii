package de.jmlutra.dinogamemkii.game;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.OffscreenCleanComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.IrremovableComponent;
import de.jmlutra.dinogamemkii.util.Avatar;
import de.jmlutra.dinogamemkii.util.EntityType;

public class DinoGameFactory implements EntityFactory {

    @Spawns("Background")
    public Entity newBackground(SpawnData data) {
        Entity entity = FXGL.entityBuilder()
                .type(EntityType.BACKGROUND)
                .view("background.png")
                .with(new IrremovableComponent())
                .build();
        entity.addComponent(new OffscreenCleanComponent());
        return entity;
    }

    @Spawns("Dino")
    public Entity newDino(SpawnData data){
        return FXGL.entityBuilder(data)
                .type(EntityType.PLAYER)
                .viewWithBBox(Avatar.currentAvatar())
                .with(new OffscreenCleanComponent())
                .collidable()
                .build();

    }
}
