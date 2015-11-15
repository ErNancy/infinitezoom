package net.nikkki.infinitezoom.worlds;

import net.nikkki.infinitezoom.steps.CircuitStep;
import net.nikkki.infinitezoom.steps._Step;

public class Circuit extends _World {
	public Circuit() {
		super();
		
		element_w = 6;
		element_h = 6;
		visiblesteps = 6;
		stepscale = 3f;
		angle = 45;
		startscale = 2;
		
		zoomSteps = new _Step[length];
		for (int i = 0; i < zoomSteps.length; i++) {
			zoomSteps[i] = new CircuitStep(this, i);
		}
		zpos = 0;

	}
}
