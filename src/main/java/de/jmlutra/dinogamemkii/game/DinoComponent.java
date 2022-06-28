package de.jmlutra.dinogamemkii.game;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import javafx.geometry.Point2D;

public class DinoComponent extends Component {

    private Point2D gravityV;

    public float gravity;
    private float currentGravity = 0;

    @Override
    public void onUpdate(double tpf) {
        entity.translate(movement().multiply(tpf));
System.out.println("Mööp");
    }

    private Point2D movement()
    {
        return new Point2D(100, 0);
    }

    /*private Point2D applyGravity() {

    }*/
}
