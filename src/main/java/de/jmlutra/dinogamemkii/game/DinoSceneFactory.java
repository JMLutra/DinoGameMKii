package de.jmlutra.dinogamemkii.game;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.app.scene.SceneFactory;

public class DinoSceneFactory extends SceneFactory {

    /*@Override
    public FXGLMenu newMainMenu() {
        return new DinoMenu(MenuType.MAIN_MENU);
    }*/

    @Override
    public FXGLMenu newGameMenu() {
        return new DinoMenu(MenuType.GAME_MENU);
    }
}
