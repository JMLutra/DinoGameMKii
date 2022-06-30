package de.jmlutra.dinogamemkii.util;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import com.almasb.fxgl.texture.Texture;
import javafx.util.Duration;

public class Avatar{

    public static String currentAvatar = "avatars/1/";

    public void setAvatar(int avatarID) {
        currentAvatar = "avatars/" + avatarID + "/";
    }
}
