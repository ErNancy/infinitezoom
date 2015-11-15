package net.nikkki.infinitezoom.steps;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

import net.nikkki.infinitezoom.worlds._World;

public class ArkanthusStep extends _Step {
	private Color COLOR1;
	private Color COLOR2;
	private Color COLOR3;
	private Color COLOR4;

	
	public ArkanthusStep(_World w, int i){
		super(w, i);
	}
	
	@Override
	public void setup() {
		COLOR3 = world.getC1();
		COLOR2 = world.getC2();
		COLOR1 = world.getC3();
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

		float step_w = (world.getElement_w()*scale);
		
		Color cl1,cl2,cl3, cl4;
		
		sr.begin(ShapeType.Filled);
		if (index%2 == 0){
			cl1 = c2;
			cl2 = c4;
			cl3 = c1;
			cl4 = c3;
		}
		else {
			cl1 = c1;
			cl2 = c2;
			cl3 = c3;
			cl4 = c4;
		}
			
		float r = step_w/30;
		Vector2 hv = new Vector2(r,0);
		Vector2 vv = new Vector2(0,r);

		if (index%2 == 1) {
			hv.rotate(36);
			vv.rotate(36);
		}
		
		int leaves = 5;
		int segments = 2;
		float a = -360f/ leaves;
		for (int i = 0; i < leaves; i++) {
						
			for (int ax = 0; ax < 2; ax++) {
				Vector2 sv = new Vector2(0,0);
				sv.add(vv.x*1.5f,vv.y*1.5f);
				int s = ax;
				
				int axis = 1;
				if (ax == 0)
					axis = -1;
					
				if (s%2 == 0)
					sr.setColor(cl4);
				else 
					sr.setColor(cl1);
				
				sr.triangle(
						0,0,
						sv.x + axis*hv.x*0 + vv.x*3.5f,		sv.y+ axis*hv.y*0 + vv.y*3.5f,
						sv.x + axis*hv.x*-4 + vv.x*4,		sv.y+ axis*hv.y*-4 + vv.y*4
				);
				
				for (int j = 0; j < segments; j++) {
					if (s%2 == 0)
						sr.setColor(cl4);
					else 
						sr.setColor(cl1);
					
					
					quad(	sr,
							sv.x + axis*hv.x*-1 + vv.x*6.5f,	sv.y+ axis*hv.y*-1 + vv.y*6.5f,
							sv.x + axis*hv.x*0 + vv.x*7.5f,		sv.y+ axis*hv.y*0 + vv.y*7.5f,
							sv.x + axis*hv.x*0 + vv.x*3.5f,		sv.y+ axis*hv.y*0 + vv.y*3.5f,
							sv.x + axis*hv.x*-1 + vv.x*2.5f,	sv.y+ axis*hv.y*-1 + vv.y*2.5f
					);
					quad(	sr,
							sv.x + axis*hv.x*-2 + vv.x*4,		sv.y+ axis*hv.y*-2 + vv.y*4,
							sv.x + axis*hv.x*-0.5f + vv.x*5.5f,	sv.y+ axis*hv.y*-0.5f + vv.y*5.5f,
							sv.x + axis*hv.x*-1 + vv.x*2.5f,	sv.y+ axis*hv.y*-1 + vv.y*2.5f,
							sv.x + axis*hv.x*-2.5f + vv.x*2.5f,	sv.y+ axis*hv.y*-2.5f + vv.y*2.5f
					);
					quad(	sr,
							sv.x + axis*hv.x*-2 + vv.x*4,		sv.y+ axis*hv.y*-2 + vv.y*4,
							sv.x + axis*hv.x*-2.5f + vv.x*2.5f,	sv.y+ axis*hv.y*-2.5f + vv.y*2.5f,
							sv.x + axis*hv.x*-3 + vv.x*4,		sv.y+ axis*hv.y*-3 + vv.y*4,
							sv.x + axis*hv.x*-3 + vv.x*5,		sv.y+ axis*hv.y*-3 + vv.y*5
					);
					quad(	sr,
							sv.x + axis*hv.x*-3 + vv.x*4,		sv.y+ axis*hv.y*-3 + vv.y*4,
							sv.x + axis*hv.x*-2.5f + vv.x*2.5f,	sv.y+ axis*hv.y*-2.5f + vv.y*2.5f,
							sv.x + axis*hv.x*-4 + vv.x*4,		sv.y+ axis*hv.y*-4 + vv.y*4,
							sv.x + axis*hv.x*-4 + vv.x*5,		sv.y+ axis*hv.y*-4 + vv.y*5
					);
					//
					quad(	sr,
							sv.x + axis*hv.x*-1 + vv.x*5,		sv.y+ axis*hv.y*-1 + vv.y*5,
							sv.x + axis*hv.x*-3 + vv.x*5,		sv.y+ axis*hv.y*-3 + vv.y*5,
							sv.x + axis*hv.x*-2.5f + vv.x*6.5f,	sv.y+ axis*hv.y*-2.5f + vv.y*6.5f,
							sv.x + axis*hv.x*-1 + vv.x*6.5f,	sv.y+ axis*hv.y*-1 + vv.y*6.5f
					);
					quad(	sr,
							sv.x + axis*hv.x*-2.5f + vv.x*6.5f,	sv.y+ axis*hv.y*-2.5f + vv.y*6.5f,
							sv.x + axis*hv.x*-3 + vv.x*5,		sv.y+ axis*hv.y*-3 + vv.y*5,
							sv.x + axis*hv.x*-5 + vv.x*7,		sv.y+ axis*hv.y*-5 + vv.y*7,
							sv.x + axis*hv.x*-4 + vv.x*7,		sv.y+ axis*hv.y*-4 + vv.y*7
							);
					quad(	sr,
							sv.x + axis*hv.x*-2.5f + vv.x*6.5f,	sv.y+ axis*hv.y*-2.5f + vv.y*6.5f,
							sv.x + axis*hv.x*-4 + vv.x*7,		sv.y+ axis*hv.y*-4 + vv.y*7,
							sv.x + axis*hv.x*-5 + vv.x*8,		sv.y+ axis*hv.y*-5 + vv.y*8,
							sv.x + axis*hv.x*-4 + vv.x*8,		sv.y+ axis*hv.y*-4 + vv.y*8
							);
					
					sv.add(vv.x*4,vv.y*4);
					s++;
					
				}
				if (s%2 == 0)
					sr.setColor(cl4);
				else 
					sr.setColor(cl1);
				quad(	sr,
						sv.x + axis*hv.x*-1 + vv.x*8,			sv.y+ axis*hv.y*-1 + vv.y*8,
						sv.x + axis*hv.x*0 + vv.x*7,			sv.y+ axis*hv.y*0 + vv.y*7,
						sv.x + axis*hv.x*0 + vv.x*3.5f,			sv.y+ axis*hv.y*0 + vv.y*3.5f,
						sv.x + axis*hv.x*-1 + vv.x*2.5f,		sv.y+ axis*hv.y*-1 + vv.y*2.5f
				);
				quad(	sr,
						sv.x + axis*hv.x*-2 + vv.x*4,			sv.y+ axis*hv.y*-2 + vv.y*4,
						sv.x + axis*hv.x*-0.5f + vv.x*4,		sv.y+ axis*hv.y*-0.5f + vv.y*4,
						sv.x + axis*hv.x*-1 + vv.x*2.5f,		sv.y+ axis*hv.y*-1 + vv.y*2.5f,
						sv.x + axis*hv.x*-2.5f + vv.x*2.5f,		sv.y+ axis*hv.y*-2.5f + vv.y*2.5f
				);
				quad(	sr,
						sv.x + axis*hv.x*-2 + vv.x*4,			sv.y+ axis*hv.y*-2 + vv.y*4,
						sv.x + axis*hv.x*-2.5f + vv.x*2.5f,		sv.y+ axis*hv.y*-2.5f + vv.y*2.5f,
						sv.x + axis*hv.x*-3 + vv.x*4,			sv.y+ axis*hv.y*-3 + vv.y*4,
						sv.x + axis*hv.x*-3 + vv.x*5,			sv.y+ axis*hv.y*-3 + vv.y*5
				);
				quad(	sr,
						sv.x + axis*hv.x*-3 + vv.x*4,			sv.y+ axis*hv.y*-3 + vv.y*4,
						sv.x + axis*hv.x*-2.5f + vv.x*2.5f,		sv.y+ axis*hv.y*-2.5f + vv.y*2.5f,
						sv.x + axis*hv.x*-4 + vv.x*4,			sv.y+ axis*hv.y*-4 + vv.y*4,
						sv.x + axis*hv.x*-4 + vv.x*5,			sv.y+ axis*hv.y*-4 + vv.y*5
				);
				//
				quad(	sr,
						sv.x + axis*hv.x*-1 + vv.x*4,			sv.y+ axis*hv.y*-1 + vv.y*4,
						sv.x + axis*hv.x*-2 + vv.x*5,			sv.y+ axis*hv.y*-2 + vv.y*5,
						sv.x + axis*hv.x*-2 + vv.x*7,			sv.y+ axis*hv.y*-2 + vv.y*7,
						sv.x + axis*hv.x*-1 + vv.x*6,			sv.y+ axis*hv.y*-1 + vv.y*6
				);
				
			}
			hv.rotate(a);
			vv.rotate(a);
		}
			
		

		
		
		sr.end();
	}
	
}
