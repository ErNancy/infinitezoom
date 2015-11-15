package net.nikkki.infinitezoom.steps;

import net.nikkki.infinitezoom.worlds._World;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

public class CircuitStep extends _Step {
	private Color color_1;
	private Color color_2;
	private Color color_3;
	private Color color_4;
	private Color color_5;

	
	public CircuitStep(_World w, int i){
		super(w, i);
	}
	
	@Override
	public void setup() {
		color_1 = world.getC1();
		color_2 = world.getC2();
		color_3 = world.getC3();
		color_4 = world.getC4();
		color_5 = world.getC5();
	}
	

	
	
	private float wallheight;
	private float wallwidth;
	private float step;
	Color c_1,c_2,c_3,c_4,c_5;

	@Override
	public void render(ShapeRenderer shapeRenderer, float scale, float alpha) {
		
		if (alpha <1) {
			c_1 = color_2.cpy();
			c_2 = color_1.cpy();
			c_3 = color_3.cpy();	
			c_4 = color_4.cpy();	
			c_5 = color_5.cpy();	
			
			c_1.a = alpha;
			c_2.a = alpha;
			c_3.a = alpha;
			c_4.a = alpha;
			c_5.a = alpha;
		} else {				
			c_1 = color_2;
			c_2 = color_1;
			c_3 = color_3;
			c_4 = color_4;
			c_5 = color_5;
			
			
		}		

		
		float step_w = (world.getElement_w()*scale);
		float step_h = (world.getElement_h()*scale);
		float left = -(step_w/2);
		float bottom = -(step_h/2);
		float right = step_w/2;
		float top = step_h/2;
		
		float boxscale = step_w/4;
		
		wallheight = 3*boxscale;
		Vector2 V1 = new Vector2(0,wallheight);
		Vector2 V2 = V1.cpy().rotate(-360/3);
		
//		wallwidth = 2*boxscale;
//		step = 1*boxscale;
		wallwidth = V2.x;
		step = -V2.y;
		
		
		float cx = left+step_w/2;
		float cy = bottom-step/3;
		cx = 0;
		cy = 0;
		ShapeRenderer sh = shapeRenderer;
		sh.begin(ShapeType.Filled);
		if (m5_1)
			sh.setColor(c_1);
		else if (m5_2)
			sh.setColor(c_5);
		else if (m5_3)
			sh.setColor(c_2);
		else if (m5_4)
			sh.setColor(c_4);
		else 
			sh.setColor(c_3);
		
		
		float breite = step_w/3f;

		drawBox(sh, cx-breite, cy, breite);
		drawBox(sh, cx+breite, cy, breite);
		
		drawBox(sh, cx, cy+breite, breite);
		drawBox(sh, cx, cy-breite, breite);
		
		drawBox(sh, cx-breite, cy+breite, breite);
		drawBox(sh, cx-breite, cy-breite, breite);
		
		drawBox(sh, cx+breite, cy+breite, breite);
		drawBox(sh, cx+breite, cy-breite, breite);
		
		
		float lh = breite/14f;
		float cr = breite/7f;
		
		sh.rect(-breite/2 + lh/2f,-breite/2 +lh/2f +cr,breite-lh,breite-lh-cr*2);
		quad(sh,
				cx-breite/2 + lh/2f,cy-breite/2 +lh/2f +cr,
				-breite/2 +lh/2f +cr,-breite/2 +lh/2f,
				breite/2 -lh/2f -cr,-breite/2 +lh/2f,
				cx+breite/2 - lh/2f,cy-breite/2 +lh/2f +cr
				);
		quad(sh,
				cx-breite/2 + lh/2f,cy+breite/2 -lh/2f -cr,
				-breite/2 +lh/2f +cr,breite/2 -lh/2f,
				breite/2 -lh/2f -cr,breite/2 -lh/2f,
				cx+breite/2 - lh/2f,cy+breite/2 -lh/2f -cr
				);
		
		
		sh.end();
		
	}
	
	private void drawBox(ShapeRenderer sr, float cx, float cy, float breite) {
		

		
		
		float cw = breite/7f;
		float cr = breite/7f;
		
		
		float crl = (float) Math.sqrt(cr*cr+cr*cr);
		
		drawBlob(sr, cx, cy, cw*6.5f, cr, crl);
		drawBlob(sr, cx, cy, cw*4.5f, cr, crl);
		drawBlob(sr, cx, cy, cw*2.5f, cr, crl);
	

		
		
		
	
	}
	
	
	
	private void drawBlob(ShapeRenderer sr, float cx, float cy, float cw, float cr, float crl){
		
		
		float lh = cr /2f;
		sr.rect(cx-(cw/2)+cr, 
				cy-(cw/2),
				cw - cr*2, lh);
		
		sr.rect(cx-(cw/2)+cr, 
				cy+(cw/2)-lh,
				cw - cr*2, lh);
		
		sr.rect(cx-(cw/2), 
				cy-(cw/2)+cr,
				lh, cw - cr*2);
		
		sr.rect(cx+(cw/2)-lh, 
				cy-(cw/2)+cr,
				lh, cw - cr*2);
		
		sr.rect(cx+cw/2 -cr, 
				cy-cw/2, 
				0,0,
				crl, crl/2, 1, 1, 45);
		
		sr.rect(cx-cw/2+cr, 
				cy+cw/2, 
				0,0,
				crl, crl/2, 1, 1, 45+180);

		
		sr.rect(cx+cw/2, 
				cy+cw/2-cr, 
				0,0,
				crl, crl/2, 1, 1, 45+90);
		
		sr.rect(cx-cw/2, 
				cy-cw/2+cr, 
				0,0,
				crl, crl/2, 1, 1, 45-90);
		
		
		

	}
	
}
