package de.jmlutra.dinogamemkii.game;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import de.jmlutra.dinogamemkii.util.Avatar;
import javafx.geometry.Point2D;
import javafx.util.Duration;

public class DinoComponent extends Component {

    private Avatar avatar;

    private AnimatedTexture texture;

    public AnimationChannel normal;
    public AnimationChannel crouch;

    private boolean isOnGround = false;

    private boolean isCrouching = false;

    public DinoComponent() {
        normal = new AnimationChannel(FXGL.image(Avatar.currentAvatar + "normal.png"), Duration.seconds(0.5),2);
        crouch = new AnimationChannel(FXGL.image(Avatar.currentAvatar + "crouch.png"), Duration.seconds(0.5),2);

        texture = new AnimatedTexture(normal);
    }

    @Override
    public void onAdded() {
        avatar = new Avatar();

        entity.getBoundingBoxComponent().addHitBox(new HitBox("normal", BoundingShape.box(normal.getFrameWidth(1) - 5, normal.getFrameHeight(1))));

        entity.getViewComponent().addChild(texture);

        texture.loopAnimationChannel(normal);
    }

    @Override
    public void onUpdate(double tpf) {
        this.crouch();
    }

    /*Jumping*/

    public void setGround(boolean ground)
    {
        isOnGround = ground;
    }

    public void jump(){
        if(isOnGround) {
            entity.getComponent(PhysicsComponent.class).setVelocityY(-1200);
        }
    }

    /*Crouching*/
    public void setCrouch(boolean crouch) {
        isCrouching = crouch;
    }
    public void crouch() {
        if (isCrouching) {
            entity.getBoundingBoxComponent().removeHitBox("normal");
            entity.getBoundingBoxComponent().addHitBox(new HitBox("crouch", BoundingShape.box(crouch.getFrameWidth(1) - 5, crouch.getFrameHeight(1))));

            texture.loopAnimationChannel(crouch);
        } else {
            entity.getBoundingBoxComponent().removeHitBox("crouch");
            entity.getBoundingBoxComponent().addHitBox(new HitBox("normal", BoundingShape.box(normal.getFrameWidth(1) - 5, normal.getFrameHeight(1))));

            texture.loopAnimationChannel(normal);
        }
        isCrouching = false;
    }

}
