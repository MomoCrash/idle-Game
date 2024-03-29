package org.momocrash;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("Idle Game");
		config.setIdleFPS(30);
		config.setWindowedMode(1080, 720);
		config.setResizable(false);
		config.useVsync(true);
		config.setWindowIcon("tree.png");
		new Lwjgl3Application(new IdleMain(), config);
	}
}
