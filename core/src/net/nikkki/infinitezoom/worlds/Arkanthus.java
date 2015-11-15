package net.nikkki.infinitezoom.worlds;

import net.nikkki.infinitezoom.steps.ArkanthusStep;
import net.nikkki.infinitezoom.steps._Step;

public class Arkanthus extends _World {
	public Arkanthus() {
		super();
		
		element_w = 8;
		element_h = 8;
		visiblesteps = 7;
		stepscale = 2.9f;
		startscale = 2f;
		angle = 18;
		
		zoomSteps = new _Step[length];
		for (int i = 0; i < zoomSteps.length; i++) {
			zoomSteps[i] = new ArkanthusStep(this,i);
		}
		zpos = 0;		
	}
}