package net.nikkki.infinitezoom.steps;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import net.nikkki.infinitezoom.worlds._World;

public class SpearStep extends _Step {
	private Color color_INSIDE;
	private Color color_OUTSIDE;
	private Color color_BACK;
	private Color color_BACKGROUND;

	
	public SpearStep(_World w, int i){
		super(w, i);
	}
	
	@Override
	public void setup() {
		color_BACK = world.getC1();
		color_OUTSIDE = world.getC2();
		color_INSIDE = world.getC3();
		color_BACKGROUND = world.getC4();
	}
	

	
	
	Color c_out,c_in,c_back,c_bg;

	@Override
	public void render(ShapeRenderer shapeRenderer, float scale, float alpha) {
		
		if (alpha <1) {
			c_out = color_OUTSIDE.cpy();
			c_in = color_INSIDE.cpy();
			c_back = color_BACK.cpy();	
			c_bg = color_BACKGROUND.cpy();	
			
			c_out.a = alpha;
			c_in.a = alpha;
			c_back.a = alpha;
			c_bg.a = alpha;
		} else {				
			c_out = color_OUTSIDE;
			c_in = color_INSIDE;
			c_back = color_BACK;
			c_bg = color_BACKGROUND;
		}		

		
		float step_w = (world.getElement_w()*scale);
		float step_h = (world.getElement_h()*scale);
		float qs = step_w/4;
		
		float left = -(step_w/2);
		float bottom = -(step_h/2);
		float right = step_w/2;
		float top = step_h/2;
		
		
		
		
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(c_bg);
		shapeRenderer.triangle(left,0,0,top,0,bottom);
		shapeRenderer.triangle(right,0,0,top,0,bottom);

		
		if (index%2 == 1) {
			shapeRenderer.setColor(c_back);
			shapeRenderer.rect(left+qs,bottom,qs,qs);
			shapeRenderer.rect(right-2*qs,top-qs,qs,qs);
			shapeRenderer.rect(left,top-2*qs,qs,qs);
			shapeRenderer.rect(right-qs,bottom+qs,qs,qs);
			
			shapeRenderer.setColor(c_out);
			shapeRenderer.triangle(left,qs,0,top,left+qs,top-qs);
			shapeRenderer.triangle(qs,top,right,0,right-qs,top-qs);
			shapeRenderer.triangle(left+qs,bottom,left,0,-qs,-qs);
			shapeRenderer.triangle(right,-qs,qs,-qs,0,bottom);
			
			shapeRenderer.setColor(c_in);
			shapeRenderer.triangle(left+qs,top-qs,0,top,left+qs,0);
			shapeRenderer.triangle(0,qs,qs,qs,right,0);
			shapeRenderer.triangle(0,bottom,qs,0,qs,-qs);
			shapeRenderer.triangle(left,0,-qs,-qs,0,-qs);
		} else {
			shapeRenderer.setColor(c_back);
			shapeRenderer.rect(0,bottom,qs,qs);
			shapeRenderer.rect(-qs,top-qs,qs,qs);
			shapeRenderer.rect(qs,top-2*qs,qs,qs);
			shapeRenderer.rect(left,bottom+qs,qs,qs);
			
			shapeRenderer.setColor(c_out);
			shapeRenderer.triangle(left,0,-qs,qs,-qs,top);
			shapeRenderer.triangle(0,top,qs,qs,right,qs);
			shapeRenderer.triangle(right,0,qs,-qs,qs,bottom);
			shapeRenderer.triangle(0,bottom,-qs,-qs,left,-qs);
					
			shapeRenderer.setColor(c_in);
			shapeRenderer.triangle(left,0,-qs,qs,0,qs);
			shapeRenderer.triangle(0,top,qs,qs,qs,0);
			shapeRenderer.triangle(right,0,qs,-qs,0,-qs);
			shapeRenderer.triangle(0,bottom,-qs,-qs,-qs,0);

		}
		
		
		shapeRenderer.end();

		
		
		
		
	}
	
}
