package net.nikkki.infinitezoom;

import net.nikkki.infinitezoom.worlds.Arkanthus;
import net.nikkki.infinitezoom.worlds.Circuit;
import net.nikkki.infinitezoom.worlds.Cube;
import net.nikkki.infinitezoom.worlds.General;
import net.nikkki.infinitezoom.worlds.Spears;
import net.nikkki.infinitezoom.worlds.Sultan;
import net.nikkki.infinitezoom.worlds._World;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Main extends Base implements InputProcessor{
	private OrthographicCamera camera;
	
	private Renderer renderer;
	private _World world;

	private int worldIndex = 0;
	
	private int currentPattern = 0;
	private int currentColorStyle = 0;
	
	private float yOffset = 0;
	
	private Class[] worlds = {
			Sultan.class,
			Arkanthus.class,
			Circuit.class,
			Cube.class,
			Spears.class,
			General.class,
	};
	
	public Main(Game game, Resolver resolver) {
		super(game, resolver);
	}

	
	
	@Override
	public void show() {
		
		Config.load();
		
		Gdx.input.setInputProcessor(this);
		worldIndex = Integer.valueOf(Config.selectedPattern);

		
		switch(Gdx.app.getType()) {
	       case Android:
	    	   setWorld(worldIndex);
	    	   Config.ANDROID = true;
	    	   break;
	       case Desktop:
	    	   setWorld(worldIndex);
	    	   Config.ANDROID = false;
	    	   break;
		}
	}
	
	public void setWorld() {
		setWorld(0);
	}
	
	public void setWorld(int w) {
		worldIndex = w;
		currentPattern = w;
		world = getWorld(w);
		renderer = new Renderer(world);
		renderer.setYoffset(yOffset);
	}
	
	public _World getWorld(int w) {
		_World rw;
		try {
			rw = (_World) worlds[w].newInstance();
		} catch (InstantiationException e) {
			rw = new Sultan();
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			rw = new Sultan();
			e.printStackTrace();
		}
		return rw;
	}

	

	

	@Override
	public void dispose() {
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		resetCamera();
		renderer.resize(width, height);
	}
	
	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
		Config.checkForChanges();
	}
	
	private void resetCamera() {
		camera = new OrthographicCamera(sW, sH);
	}
	
	private void update() {
		if (currentPattern != Config.selectedPattern) {
			setWorld(Config.selectedPattern);
		}
		if (currentColorStyle != Config.colorStyle) {
			currentColorStyle = Config.colorStyle;
			world.newColors();
		}
	}
	@Override
	public void render(float delta) {
		update();
		super.render(delta);
	
		world.update(delta);
		renderer.render(delta);
		
		if (isAndroid)
			limitFPS();

		if (!isAndroid && Gdx.input.isKeyPressed(Keys.ESCAPE))
			Gdx.app.exit();
	}



	@Override
	public boolean keyDown(int keycode) {
		
		if (keycode == Input.Keys.NUM_1) {
			Config.colorStyle = 0;
		} else if (keycode == Input.Keys.NUM_2) {
			Config.colorStyle = 1;		
		} else if (keycode == Input.Keys.NUM_3) {
			Config.colorStyle = 2;
		} else if (keycode == Input.Keys.NUM_4) {
			Config.colorStyle = 3;
		} else if (keycode == Input.Keys.RIGHT) {
			worldIndex++;
			if (worldIndex>= worlds.length)
				worldIndex= 0;
			Config.setPattern(worldIndex);
		} else if (keycode == Input.Keys.LEFT) {
			worldIndex--;
			if (worldIndex< 0)
				worldIndex= worlds.length-1;
			Config.setPattern(worldIndex);
		}
		return false;
		
	}

	public void setYoffset(float value) {
		yOffset = value;
		renderer.setYoffset(value);
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}
	@Override
	public boolean keyTyped(char character) {
		return false;
	}
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}
	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}
	@Override
	public boolean scrolled(int amount) {
		return false;
	}






}
