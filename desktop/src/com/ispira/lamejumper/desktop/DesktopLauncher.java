package com.ispira.lamejumper.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ispira.lamejumper.LameJumper;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "LameJumper";
		config.width = 480;
		config.height = 800;
		new LwjglApplication(new LameJumper(), config);
	}
}
