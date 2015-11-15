package net.nikkki.infinitezoom.worlds;

import net.nikkki.infinitezoom.steps.SultanStep;
import net.nikkki.infinitezoom.steps._Step;

public class Sultan extends _World {
	public Sultan() {
		super();
		
		element_w = 8;
		element_h = 8;		
		visiblesteps = 8;
		stepscale = 2;
		startscale = 1.2f;

		
		zoomSteps = new _Step[length];
		for (int i = 0; i < zoomSteps.length; i++) {
			zoomSteps[i] = new SultanStep(this,i);
		}
		zpos = 0;		
	}
}