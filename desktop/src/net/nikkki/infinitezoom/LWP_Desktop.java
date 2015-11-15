package net.nikkki.infinitezoom;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import java.awt.Dimension;
import java.awt.Toolkit;

public class LWP_Desktop {
	public static void main(String[] argv) {
		final LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		config.title   = "Infinite Zoom";
		config.width =  (int) screenSize.getWidth();;
		config.height = (int) screenSize.getHeight();
		config.fullscreen = false;
    	config.samples = 0;
//		config.useGL20      = false;
		config.vSyncEnabled = true;
		config.resizable    = true;
		config.foregroundFPS = 61;
		config.backgroundFPS = 61;

		new LwjglApplication(new LWP(), config);
	}
}
