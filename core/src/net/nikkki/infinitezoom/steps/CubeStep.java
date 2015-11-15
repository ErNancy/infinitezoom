package net.nikkki.infinitezoom.steps;

import net.nikkki.infinitezoom.worlds._World;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

public class CubeStep extends _Step {
	private Color COLOR1;
	private Color COLOR2;
	private Color COLOR3;
	private Color COLOR4;
	private Color COLOR5;


	public CubeStep(_World w, int i){
		super(w, i);
	}

	@Override
	public void setup() {
		COLOR3 = world.getC3();
		COLOR2 = world.getC2();
		COLOR1 = world.getC1();
		COLOR4 = world.getC4();
		COLOR5 = world.getC5();
	}




	Color c2,c1,c3,c4,c5;

	@Override
	public void render(ShapeRenderer sr, float scale, float alpha) {

		if (alpha <1) {
			c2 = COLOR2.cpy();
			c1 = COLOR1.cpy();
			c3 = COLOR3.cpy();	
			c4 = COLOR4.cpy();	
			c5 = COLOR5.cpy();	

			c2.a = alpha;
			c1.a = alpha;
			c3.a = alpha;
			c4.a = alpha;
			c5.a = alpha;
		} else {				
			c2 = COLOR2;
			c1 = COLOR1;
			c3 = COLOR3;
			c4 = COLOR4;
			c5 = COLOR5;
		}		


		float step_w = (world.getElement_w()*scale);
		float ql = step_w/5f;
		

//		
		
		Vector2 V1_5 = new Vector2(0,-ql*5);
		Vector2 V2_5 = V1_5.cpy().rotate(360/3);
		Vector2 V3_5 = V1_5.cpy().rotate(360/3*2);
		Vector2 V1 = new Vector2(0,-ql);
		Vector2 V2 = V1.cpy().rotate(360/3);
		Vector2 V3 = V1.cpy().rotate(360/3*2);
		
		
		sr.begin(ShapeType.Filled);
		
		
		
		sr.setColor(c1);
		quad(sr,
				0,0,
				V3_5.x, V3_5.y,
				-V1_5.x, -V1_5.y,
				V2_5.x, V2_5.y);
		sr.setColor(c3);
		quad(sr,
			0,0,
			V1_5.x,V1_5.y,
			V1_5.x + V2_5.x, V1_5.y + V2_5.y,
			V2_5.x, V2_5.y);
		sr.setColor(c2);
		quad(sr,
			0,0,
			V1_5.x,V1_5.y,
			V1_5.x + V3_5.x, V1_5.y + V3_5.y,
			V3_5.x, V3_5.y);
		///// inner bg
		sr.setColor(c4);
		sr.triangle(
			V3.x, V3.y,
			V3.x - V2.x, V3.y - V2.y,
			V3.x + V1.x, V3.y + V1.y);
		sr.triangle(
				-V1.x, -V1.y,
				-V1.x - V2.x, -V1.y - V2.y,
				-V1.x + V3.x, -V1.y + V3.y);
		sr.triangle(
				-V1.x, -V1.y,
				-V1.x + V2.x, -V1.y + V2.y,
				-V1.x - V3.x, -V1.y - V3.y);
		sr.triangle(
				V2.x, V2.y,
				V2.x - V3.x, V2.y - V3.y,
				V2.x + V1.x, V2.y + V1.y);
		sr.triangle(
				V1.x, V1.y,
				V1.x - V2.x, V1.y - V2.y,
				V1.x + V3.x, V1.y + V3.y);
		sr.triangle(
				V1.x, V1.y,
				V1.x + V2.x, V1.y + V2.y,
				V1.x - V3.x, V1.y - V3.y);
		// outer bg
		sr.triangle(
				V3.x - V2.x, V3.y - V2.y,
				V3.x - V2.x*2, V3.y - V2.y*2,
				V3.x*2 - V2.x, V3.y*2 - V2.y);
		sr.triangle(
				V1.x - V2.x, V1.y - V2.y,
				V1.x*2 - V2.x, V1.y*2 - V2.y,
				V1.x - V2.x*2, V1.y - V2.y*2);
		sr.triangle(
				V1.x - V3.x, V1.y - V3.y,
				V1.x*2 - V3.x, V1.y*2 - V3.y,
				V1.x - V3.x*2, V1.y - V3.y*2);
		sr.triangle(
				V2.x - V3.x, V2.y - V3.y,
				V2.x - V3.x*2, V2.y - V3.y*2,
				V2.x*2 - V3.x, V2.y*2 - V3.y);
		sr.triangle(
				-V1.x + V2.x, -V1.y + V2.y,
				-V1.x*2 + V2.x, -V1.y*2 + V2.y,
				-V1.x + V2.x*2, -V1.y + V2.y*2);
		sr.triangle(
				-V1.x + V3.x, -V1.y + V3.y,
				-V1.x*2 + V3.x, -V1.y*2 + V3.y,
				-V1.x + V3.x*2, -V1.y + V3.y*2);
		// right sides
		sr.setColor(c3);
		quad(sr,
				V3.x, V3.y,
				V3.x - V1.x, V3.y - V1.y,
				V3.x*2, V3.y*2,
				V3.x - V2.x, V3.y - V2.y);
		quad(sr,
				V1.x + V3.x*3, V1.y + V3.y*3,
				V1.x + V3.x*4, V1.y + V3.y*4,
				V1.x*4 + V3.x*4, V1.y*4 + V3.y*4,
				V1.x*3 + V3.x*3, V1.y*3 + V3.y*3);
		quad(sr,
				-V2.x, -V2.y,
				-V2.x + V1.x, -V2.y + V1.y,
				-V2.x*2 + V1.x, -V2.y*2 + V1.y,
				-V2.x*3, -V2.y*3);
		quad(sr,
				-V1.x, -V1.y,
				-V1.x*3, -V1.y*3,
				-V1.x*3 -V3.x, -V1.y*3 -V3.y,
				-V1.x*2 -V3.x, -V1.y*2 -V3.y);
		quad(sr,
				-V1.x*3, -V1.y*3,
				-V1.x*4, -V1.y*4,
				-V1.x + V3.x*3, -V1.y + V3.y*3,
				-V1.x + V3.x*2, -V1.y + V3.y*2);
			
		
		// left sides
		sr.setColor(c2);
		quad(sr,
				V2.x, V2.y,
				V2.x - V1.x, V2.y - V1.y,
				V2.x*2, V2.y*2,
				V2.x - V3.x, V2.y - V3.y);
		quad(sr,
				V1.x + V2.x*3, V1.y + V2.y*3,
				V1.x + V2.x*4, V1.y + V2.y*4,
				V1.x*4 + V2.x*4, V1.y*4 + V2.y*4,
				V1.x*3 + V2.x*3, V1.y*3 + V2.y*3);
		quad(sr,
				-V3.x, -V3.y,
				-V3.x + V1.x, -V3.y + V1.y,
				-V3.x*2 + V1.x, -V3.y*2 + V1.y,
				-V3.x*3, -V3.y*3);
		quad(sr,
				-V1.x, -V1.y,
				-V1.x*3, -V1.y*3,
				-V1.x*3 -V2.x, -V1.y*3 -V2.y,
				-V1.x*2 -V2.x, -V1.y*2 -V2.y);
		quad(sr,
				-V1.x*3, -V1.y*3,
				-V1.x*4, -V1.y*4,
				-V1.x + V2.x*3, -V1.y + V2.y*3,
				-V1.x + V2.x*2, -V1.y + V2.y*2);
				
				
		
		// top sides
		sr.setColor(c1);
		quad(sr,
				V1.x, V1.y,
				V1.x-V2.x, V1.y-V2.y,
				V1.x*2, V1.y*2,
				V1.x-V3.x, V1.y-V3.y);
		quad(sr,
				-V2.x, -V2.y,
				-V2.x + V3.x, -V2.y + V3.y,
				-V2.x*2 + V3.x, -V2.y*2 + V3.y,
				-V2.x*3, -V2.y*3 );
		quad(sr,
				-V3.x, -V3.y,
				-V3.x + V2.x, -V3.y + V2.y,
				-V3.x*2 + V2.x, -V3.y*2 + V2.y,
				-V3.x*3, -V3.y*3 );
		quad(sr,
				V1.x*4 + V3.x, V1.y*4 + V3.y,
				V1.x*4 + V3.x*4, V1.y*4 + V3.y*4,
				V1.x*3 + V3.x*3, V1.y*3 + V3.y*3,
				V1.x*3 + V3.x, V1.y*3 + V3.y);
		quad(sr,
				V1.x*4 + V2.x, V1.y*4 + V2.y,
				V1.x*4 + V2.x*4, V1.y*4 + V2.y*4,
				V1.x*3 + V2.x*3, V1.y*3 + V2.y*3,
				V1.x*3 + V2.x, V1.y*3 + V2.y);
		
		
		
		sr.end();
	}
	
}
