package net.nikkki.infinitezoom.steps;

import net.nikkki.infinitezoom.worlds._World;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class _Step {
	

	
	protected _World world;
	protected int index;
	protected boolean m2, m3_1, m3_2, m4_1, m4_2, m4_3;
	protected boolean m5_1, m5_2, m5_3, m5_4;
	
	public boolean seen = false;
	public boolean visible = false;
	
	public _Step(_World w, int i) {
		world = w;
		index = i;
		m2 = (index%2) == 0;
		m3_1 = (index%3) == 0;
		m3_2 = (index%3) == 1;
		
		m4_1 = (index%4) == 1;
		m4_2 = (index%4) == 2;
		m4_3 = (index%4) == 3;
		
		m5_1 = (index%5) == 1;
		m5_2 = (index%5) == 2;
		m5_3 = (index%5) == 3;
		m5_4 = (index%5) == 4;
		
		setup();
	}
	public void setup() {
	}

	public void render(ShapeRenderer shapeRenderer, float scale, float alpha) {
	}
	public void render(ShapeRenderer shapeRenderer, float scale, float alpha, int distance) {
		render(shapeRenderer, scale, alpha);
	}
	
	protected void quad(ShapeRenderer sr, float p1x,float p1y,float p2x,float p2y,float p3x,float p3y,float p4x,float p4y) {
		sr.triangle(p1x, p1y, p2x, p2y, p3x, p3y);
		sr.triangle(p1x, p1y, p4x, p4y, p3x, p3y);
	}
	protected void triangle(ShapeRenderer sr, Vector2 p1, Vector2 p2, Vector2 p3) {
		sr.triangle(p1.x, p1.y, p2.x, p2.y, p3.x, p3.y);
	}
	public int getIndex() {
		return index;
	}
	
	public _World world() {
		return world;
	}
}









