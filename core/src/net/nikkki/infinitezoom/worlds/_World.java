package net.nikkki.infinitezoom.worlds;

import net.nikkki.infinitezoom.Config;
import net.nikkki.infinitezoom.steps._Step;

import com.badlogic.gdx.graphics.Color;

public class _World {
	
	private static final float greymodifier = 1.05f;
	
	protected float element_w = 6;
	protected float element_h = 6;
	protected float stepscale = 2;
	protected int visiblesteps = 6;
	public long starttime;
	protected float angle = 0;
	protected _Step[] zoomSteps;
	protected float zpos = 0;
	protected int length = 20;
	
	boolean interpolate = false;
	int interpolationspeed = 4000;
	long lastchange = 0;
	
	float startscale = 1;
	
	protected Color color1;
	protected Color color2;
	protected Color color3;
	protected Color color4;
	protected Color color5;
	protected Color color6;
	
	protected Color target_color1;
	protected Color target_color2;
	protected Color target_color3;
	protected Color target_color4;
	protected Color target_color5;
	protected Color target_color6;

	protected Color source_color1;
	protected Color source_color2;
	protected Color source_color3;
	protected Color source_color4;
	protected Color source_color5;
	protected Color source_color6;
	
	public _World() {
		starttime = System.currentTimeMillis();
		setupColors();
	}
	
	public void setupColors() {
		if (Config.colorStyle == 1) {	
			interpolate = true;
			lastchange =  System.currentTimeMillis();
					
			target_color1 = randomColor();
			target_color2 = randomColor();
			target_color3 = randomColor();
			target_color4 = randomColor();
			target_color5 = randomColor();
			target_color6 = randomColor();

			source_color1 = randomColor();
			source_color2 = randomColor();
			source_color3 = randomColor();
			source_color4 = randomColor();
			source_color5 = randomColor();
			source_color6 = randomColor();
			 
			color1 = source_color1.cpy();
			color2 = source_color2.cpy();
			color3 = source_color3.cpy();
			color4 = source_color4.cpy();
			color5 = source_color4.cpy();
			color6 = source_color6.cpy();
		} else {
			interpolate = false;			
		}
	}
	
	public void newColors() {
		setupColors();
		for (int i = 0; i < zoomSteps.length; i++) {
			zoomSteps[i].setup();
		}
	}
	public static Color randomColor() {
		return new Color((float)(Math.random()),(float)(Math.random()),(float)(Math.random()),1);
	}
	
	public static Color makeColor() {
		if (Config.colorStyle == 2) { 
			// mono bright random
			float grey = (float)(Math.random()*0.35f)+0.65f;
			return new Color(grey,grey,Math.min(grey*greymodifier,1),1);		
		} else if (Config.colorStyle == 3) {
			float grey;
			if (Math.random()>0.5f) {
				grey = (float)(Math.random()*0.4f)+0.6f;
			} else {
				grey = (float)(Math.random()*0.4f);
			}
			return new Color(grey,grey,Math.min(grey*greymodifier,1),1);									
		} else {
			// random
			return new Color((float)(Math.random()),(float)(Math.random()),(float)(Math.random()),1);					
		}
	}
	
	public _Step getstep(int i){
		return zoomSteps[i%zoomSteps.length];
	}
	
	public void update(float delta) {
		float zs = (Config.zoomSpeed/stepscale) *delta;
		
		if (!Config.pause) {			
			zpos += zs;
			if (zpos > length)
				zpos -= length;
		}
		if (interpolate) {
			if (lastchange < System.currentTimeMillis() - interpolationspeed) {
				source_color1 = target_color1.cpy();
				source_color2 = target_color2.cpy();
				source_color3 = target_color3.cpy();
				source_color4 = target_color4.cpy();
				source_color5 = target_color5.cpy();
				source_color6 = target_color6.cpy();

				target_color1 = randomColor();
				target_color2 = randomColor();
				target_color3 = randomColor();
				target_color4 = randomColor();
				target_color5 = randomColor();
				target_color6 = randomColor();
				lastchange =  System.currentTimeMillis();
			}
			float percentage = (System.currentTimeMillis() - lastchange) / (float)interpolationspeed;
			
			color1.set(source_color1).lerp(target_color1, percentage);
			color2.set(source_color2).lerp(target_color2, percentage);
			color3.set(source_color3).lerp(target_color3, percentage);
			color4.set(source_color4).lerp(target_color4, percentage);
			color5.set(source_color5).lerp(target_color5, percentage);
			color6.set(source_color6).lerp(target_color6, percentage);
		}
		
	}

	public float angle(){
		return angle;
	}
	public Color getC1(){
		return (Config.colorStyle == 1)?color1:makeColor();
	}
	public Color getC2(){
		return (Config.colorStyle == 1)?color2:makeColor();
	}
	public Color getC3(){
		return (Config.colorStyle == 1)?color3:makeColor();
	}
	public Color getC4(){
		return (Config.colorStyle == 1)?color4:makeColor();
	}
	public Color getC5(){
		return (Config.colorStyle == 1)?color5:makeColor();
	}
	public Color getC6(){
		return (Config.colorStyle == 1)?color6:makeColor();
	}
	public _Step[] getSteps() {
		return zoomSteps;
	}
	public float length() {
		return length;
	}
	public float scale() {
		return stepscale;
	}
	public float zPos() {
		return zpos;
	}
	public float getElement_h() {
		return element_h;
	}
	public float getElement_w() {
		return element_w;
	}

	public int visiblesteps() {
		return visiblesteps;
	}
	public float startscale() {
		return startscale;
	}
	public void setvisibleSteps(int i) {
		if (i < visiblesteps) {
			visiblesteps = i;			
		}
	}


}
