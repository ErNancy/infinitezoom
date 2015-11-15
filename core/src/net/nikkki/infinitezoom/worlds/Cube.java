package net.nikkki.infinitezoom.worlds;

import net.nikkki.infinitezoom.steps.CubeStep;
import net.nikkki.infinitezoom.steps._Step;

public class Cube extends _World {
	public Cube() {
		super();
		
		element_w = 8;
		element_h = 8;
		visiblesteps = 8;
		stepscale = 5f;
		startscale = 1f;
		angle = 0;
		
		zoomSteps = new _Step[length];
		for (int i = 0; i < zoomSteps.length; i++) {
			zoomSteps[i] = new CubeStep(this,i);
		}
		zpos = 0;		
	}
}