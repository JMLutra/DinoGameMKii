package de.jmlutra.dinogamemkii.game;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import de.jmlutra.dinogamemkii.util.EntityType;
import de.jmlutra.dinogamemkii.util.GamePlay;

public class ObstacleComponent extends Component {

    @Override
    public void onAdded() {
        entity.getComponent(PhysicsComponent.class).setOnPhysicsInitialized(() ->  entity.getComponent(PhysicsComponent.class).setVelocityX(-350 * GamePlay.obstacleSpeed()));
    }

}
