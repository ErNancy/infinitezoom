

package net.nikkki.infinitezoom.steps;

import net.nikkki.infinitezoom.worlds._World;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

public class SultanStep extends _Step {
	private Color COLOR1;
	private Color COLOR2;
	private Color COLOR3;
	private Color COLOR4;


	public SultanStep(_World w, int i){
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
		
		int cells = 16;
		float p = -360f/(cells);
		
		float r = (world.getElement_w()*scale)/2f;
		
		float m = r/world.scale()*0.5f;
		
		float d = r/world.scale();
		
		float s = (index%2==0)?0:p*0.5f;
		
		Vector2 ov2 = new Vector2(0,r).rotate(p+s);

		Vector2 mv1 = new Vector2(0,r-m).rotate(p*0.5f+s);
		Vector2 mv2 = mv1.cpy().rotate(p);

		Vector2 iv2 = new Vector2(0,r-d).rotate(p+s);
		
		sr.begin(ShapeType.Filled);
		
		

		for (int i = 0; i < cells; i++) {
			
			sr.setColor(c1);
			sr.triangle(iv2.x,iv2.y,
						mv1.x,mv1.y,
						0,0);
			
			sr.setColor(c2);
			sr.triangle(iv2.x,iv2.y,
					mv2.x,mv2.y,
					0,0);
			
			sr.setColor(c3);
			sr.triangle(mv1.x,mv1.y,
					ov2.x,ov2.y,
					iv2.x,iv2.y);

			sr.setColor(c4);
			sr.triangle(mv2.x,mv2.y,
					ov2.x,ov2.y,
					iv2.x,iv2.y);

			
			
			
			
			ov2.rotate(p*1);			
			mv1.rotate(p*1);
			mv2.rotate(p*1);
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

