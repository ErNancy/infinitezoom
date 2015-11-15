package net.nikkki.infinitezoom;

import com.badlogic.gdx.Game;

public class LWP extends Game {
	public Resolver resolver = null;
	Main screen;
	
	@Override
	public void create() {
		screen = new Main(this, resolver);
		setScreen(screen);
	}

	@Override
	public void dispose () {
		super.dispose();
		resolver = null;
		getScreen().dispose();
	}
	
	@Override
	public void pause() {
	}
	@Override
	public void resume() {
		screen.resume();
	}
	public void setYoffset(float value) {
		screen.setYoffset(value);
	}
}
