package com.github.thenoofclan.orbitaltrucker.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.github.thenoofclan.orbitaltrucker.OrbitalTrucker;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Orbital Trucker | <VERSIONNUMBER>";
		config.foregroundFPS = 60;
		config.height = 240;
		config.width = 256;
		new LwjglApplication(new OrbitalTrucker(), config);
	}
}
