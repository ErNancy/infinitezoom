package net.nikkki.infinitezoom;

import net.nikkki.infinitezoom.steps._Step;
import net.nikkki.infinitezoom.worlds._World;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Renderer {
	public static float DISPLAY_WIDTH = Gdx.graphics.getWidth();
	public static float DISPLAY_HEIGHT = Gdx.graphics.getHeight();
	
	public static float CAMERA_WIDTH = 8;
	public static float CAMERA_HEIGHT = CAMERA_WIDTH/DISPLAY_WIDTH * DISPLAY_HEIGHT;
	
	private FPSLogger fpslogger;
	
	private float yOffset = 0;
	
	private _World world;
	private OrthographicCamera cam;	
	private ShapeRenderer shapeRenderer;    
	
	public Renderer(_World world) {
		this.world = world;
		setupCam();
		fpslogger = new FPSLogger();
		shapeRenderer = new ShapeRenderer();		        
		// enable transparency
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
	}
	


	public void render(float delta) {

		// setup renderer
		shapeRenderer.setProjectionMatrix(cam.combined);
		Gdx.gl.glEnable(GL20.GL_BLEND);
		
		// get world properties
		_Step[] levelSteps = world.getSteps();
		float z_position = world.zPos();
		int currentposition = (int) (Math.floor(z_position));		
		
		
		// reset visible flag
		for (int i = 0; i < levelSteps.length; i++) {
			levelSteps[i].visible = false;
		}
		//
		
		
		_Step[] stepArray = new _Step[world.visiblesteps()];
		if (currentposition<=levelSteps.length) {
			currentposition+=levelSteps.length;
		}
		for (int i = 0; i < world.visiblesteps(); i++) {
			stepArray[i] = levelSteps[  (currentposition+i) % levelSteps.length ];
			stepArray[i].visible = true;
			stepArray[i].seen = true;
		}
		float blendstep = z_position%1;
		
		// draw zoom steps
		float scale = (float) Math.pow(world.scale(),(z_position%1))*world.scale()*world.startscale();
		for (int i = 0; i < stepArray.length; i++) {
			float alpha = 1;
			if (i == stepArray.length-1) {
				alpha = blendstep;
			}
			
			stepArray[i].render(shapeRenderer,scale,alpha, i);
			scale *= (1f/world.scale());
		};

		// reset all steps that have been on screen for new colors
		for (int i = 0; i < levelSteps.length; i++) {
			if (levelSteps[i].seen && ! levelSteps[i].visible) {
				levelSteps[i].setup();
				levelSteps[i].seen = false;					
			}
		}
	}

	public void resize(int width, int height) {
		DISPLAY_WIDTH = width;
		DISPLAY_HEIGHT = height;
		
		if (DISPLAY_WIDTH > DISPLAY_HEIGHT) {
			CAMERA_WIDTH = 8;
			CAMERA_HEIGHT = CAMERA_WIDTH/DISPLAY_WIDTH * DISPLAY_HEIGHT;
		} else {
			CAMERA_HEIGHT = 8;
			CAMERA_WIDTH = CAMERA_HEIGHT/DISPLAY_HEIGHT * DISPLAY_WIDTH;

		}
		setupCam();
}
	
	public void setupCam() {
		cam = new OrthographicCamera( CAMERA_WIDTH, CAMERA_HEIGHT);
		Vector2 ov = new Vector2(0,CAMERA_HEIGHT/DISPLAY_HEIGHT*yOffset);
		ov.rotate(-world.angle());
		cam.position.set(ov.x,ov.y,0);
		cam.rotate(world.angle());
		cam.update();
	}
	public void setYoffset(float value) {
		yOffset = value;
		Vector2 ov = new Vector2(0,CAMERA_HEIGHT/DISPLAY_HEIGHT*yOffset);
		ov.rotate(-world.angle());
		cam.position.set(ov.x,ov.y,0);
		cam.update();		
	}
}
