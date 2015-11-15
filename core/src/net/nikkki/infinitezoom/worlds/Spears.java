package net.nikkki.infinitezoom.worlds;

import net.nikkki.infinitezoom.steps.SpearStep;
import net.nikkki.infinitezoom.steps._Step;

public class Spears extends _World {
	public Spears() {
		super();
		
		element_w = 8;
		element_h = 8;
		
		visiblesteps = 8;
		stepscale = 2;
		
		zoomSteps = new _Step[length];
		for (int i = 0; i < zoomSteps.length; i++) {
			zoomSteps[i] = new SpearStep(this,i);
		}
		zpos = 0;
		
		
		
	}
}
