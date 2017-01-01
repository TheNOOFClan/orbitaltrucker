package com.github.thenoofclan.orbitaltrucker.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.github.thenoofclan.orbitaltrucker.OrbitalTrucker;

public class DesktopLauncher
{
    public static void main(String[] arg)
    {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Orbital Trucker | Sol";
        config.foregroundFPS = 60;
        config.height = 480;
        config.width = 512;
        new LwjglApplication(new OrbitalTrucker(), config);
    }
}
