package net.nikkki.infinitezoom.worlds;

import net.nikkki.infinitezoom.steps.GeneralStep;
import net.nikkki.infinitezoom.steps._Step;

public class General extends _World {
	public General() {
		super();
		
		element_w = 8;
		element_h = 8;		
		visiblesteps = 8;
		stepscale = 2;
		startscale = 1;

		
		zoomSteps = new _Step[length];
		for (int i = 0; i < zoomSteps.length; i++) {
			zoomSteps[i] = new GeneralStep(this,i);
		}
		zpos = 0;		
	}
}