

package net.nikkki.infinitezoom.steps;

import net.nikkki.infinitezoom.worlds._World;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

public class GeneralStep extends _Step {
	private Color COLOR1;
	private Color COLOR2;
	private Color COLOR3;
	private Color COLOR4;


	public GeneralStep(_World w, int i){
		super(w, i);
	}
	
	@Override
	public void setup() {
		COLOR1 = world.getC1();
		COLOR2 = world.getC2();
		COLOR3 = world.getC3();
		COLOR4 = world.getC4();
	}
	

	
	
	Color c2,c1,c3,c4;

	@Override
	public void render(ShapeRenderer sr, float scale, float alpha) {
		
		if (alpha <1) {
			c2 = COLOR2.cpy();
			c1 = COLOR1.cpy();
			c3 = COLOR3.cpy();	
			c4 = COLOR4.cpy();	
			
			c2.a = alpha;
			c1.a = alpha;
			c3.a = alpha;
			c4.a = alpha;
		} else {				
			c2 = COLOR2;
			c1 = COLOR1;
			c3 = COLOR3;
			c4 = COLOR4;
		}		
		
		int cells = 12;
		float p = -360f/(cells*3);
		
		float r = (world.getElement_w()*scale)/2f;
		
		float m = r/world.scale()*0.66f;
		
		float d = r/world.scale();
		
		Vector2 ov1 = new Vector2(0,r);
		Vector2 ov2 = ov1.cpy().rotate(p);

		Vector2 mv1 = new Vector2(0,r-m).rotate(p*0.5f);
		Vector2 mv2 = mv1.cpy().rotate(p);

		Vector2 iv1 = new Vector2(0,r-d);
		Vector2 iv2 = iv1.cpy().rotate(p);
		
		sr.begin(ShapeType.Filled);
		
		

		for (int i = 0; i < cells; i++) {
			
			sr.setColor(c1);
			sr.triangle(ov1.x,ov1.y,
						ov2.x,ov2.y,
						mv1.x,mv1.y);
		
			sr.setColor(c2);			
			sr.triangle(iv1.x,iv1.y,
						iv2.x,iv2.y,
						mv1.x,mv1.y);

			
			sr.setColor(c1);
			sr.triangle(ov2.x,ov2.y,
						mv2.x,mv2.y,
						mv1.x,mv1.y);
		
			sr.setColor(c2);			
			sr.triangle(iv2.x,iv2.y,
						mv2.x,mv2.y,
						mv1.x,mv1.y);
			
			
			ov1.rotate(p*1);
			ov2.rotate(p*1);			
			mv1.rotate(p*1);
			mv2.rotate(p*1);
			iv1.rotate(p*1);
			iv2.rotate(p*1);

			sr.setColor(c1);			
			sr.triangle(iv2.x,iv2.y,
						mv1.x,mv1.y,
						mv2.x,mv2.y);
			
			sr.setColor(c2);			
			sr.triangle(ov2.x,ov2.y,
					mv1.x,mv1.y,
					mv2.x,mv2.y);

			sr.setColor(c4);			
			sr.triangle(mv1.x,mv1.y,
						iv1.x,iv1.y,
						iv2.x,iv2.y);
			sr.triangle(iv1.x,iv1.y,
						iv2.x,iv2.y,
						0,0);

			
			ov1.rotate(p*1);
			ov2.rotate(p*1);
			mv1.rotate(p*1);
			mv2.rotate(p*1);			
			iv1.rotate(p*1);
			iv2.rotate(p*1);

			
			sr.setColor(c2);
			sr.triangle(ov1.x,ov1.y,
						ov2.x,ov2.y,
						mv1.x,mv1.y);
		
			sr.setColor(c1);			
			sr.triangle(iv1.x,iv1.y,
						iv2.x,iv2.y,
						mv1.x,mv1.y);

			
			sr.setColor(c4);			
			sr.triangle(ov2.x,ov2.y,
						mv1.x,mv1.y,
						mv2.x,mv2.y);
			sr.triangle(mv1.x,mv1.y,
						mv2.x,mv2.y,
						iv2.x,iv2.y);

			ov1.rotate(p*1);
			ov2.rotate(p*1);
			mv1.rotate(p*1);
			mv2.rotate(p*1);			
			iv1.rotate(p*1);
			iv2.rotate(p*1);

			
			
//			ov1.rotate(p*3);
//			ov2.rotate(p*3);
//
//			mv1.rotate(p*3);
//			mv2.rotate(p*3);
//			
//			iv1.rotate(p*3);
//			iv2.rotate(p*3);

		}
		
		
	
		sr.end();
	}
	
}

