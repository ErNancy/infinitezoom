package net.nikkki.infinitezoom;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ScrollView;

import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.backends.android.AndroidFragmentApplication;

public class __Settings extends FragmentActivity implements
		AndroidFragmentApplication.Callbacks, _Setting_Item.ClickInterface,
		_Setting_PatternDialog.ClickInterface, _Setting_Speed.SpeedInterface {

	FragmentManager fragmentManager;

	ZoomPreviewFragment zoomPreview;

	_Setting_PatternItem patternSelect;
	_Setting_Item colorSelect;
	_Setting_Speed speedSelect;

	private int[] freePatternImages = { R.drawable.sultan, R.drawable.akanthus,
			R.drawable.puddle, R.drawable.cuboid, R.drawable.spears,
			R.drawable.general };
	private String[] freePatternNames = { "Sultan", "Akanthus", "Circuit",
			"Cube", "Spears", "General" };
	private int[] freePatternIndizes = { 0, 1, 2, 3, 4, 5};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.settings);
		Config.load();

		Config.patternIndizes = freePatternIndizes;
		Config.patternImages = freePatternImages;
		Config.patternNames = freePatternNames;
		
		zoomPreview = new ZoomPreviewFragment();
		patternSelect = _Setting_PatternItem.newInstance();
		colorSelect = _Setting_Item.newInstance(R.layout.setting_item_colors,
				Config.getColorStyle(), Config.getColorDescription());
		speedSelect = _Setting_Speed.newInstance(R.layout.setting_item_speed,
				Config.getSpeed(), Config.getSpeedText());
		
		fragmentManager = getSupportFragmentManager();
		FragmentTransaction trans = fragmentManager.beginTransaction();
		trans.replace(R.id.zoom_preview, zoomPreview);
		trans.add(R.id.settings, patternSelect);
		trans.add(R.id.settings, colorSelect);
		trans.add(R.id.settings, speedSelect);
		trans.commit();

		patternSelect.setClickInterface(this);
		colorSelect.setClickInterface(this);
		speedSelect.setSpeedInterface(this);

		final ScrollView scrollView = (ScrollView) findViewById(R.id.scrollview);
		scrollView.getViewTreeObserver().addOnScrollChangedListener(
		new OnScrollChangedListener() {
			@Override
			public void onScrollChanged() {
				zoomPreview.updateYOffset();
			}
		});
	}

	@Override
	public void itemClicked(View v) {

		if (v.getId() == R.id.pattern) {
			_Setting_PatternDialog patternDialog = new _Setting_PatternDialog();
			patternDialog.setClickInterface(this);
			patternDialog.show(fragmentManager, "");
		} else if (v.getId() == R.id.nextPattern) {
			int next = (Config.getIndexOfSelected() + 1)
					% Config.patternIndizes.length;
			setPattern(next);
		} else if (v.getId() == R.id.prevPattern) {
			int prev = Config.getIndexOfSelected() - 1;
			setPattern((prev >= 0) ? prev : Config.patternIndizes.length - 1);
		} else if (v.getId() == R.id.color1) {
			setColor(0);
		} else if (v.getId() == R.id.color2) {
			setColor(1);
		} else if (v.getId() == R.id.color3) {
			setColor(2);
		} else if (v.getId() == R.id.color4) {
			setColor(3);
		}
	}

	@Override
	public void patternItemClicked(int i) {
		setPattern(i);
	}

	@Override
	public void speedChanged(int value) {
		Config.setSpeed(value);
		speedSelect.setDescription(Config.getSpeedText());
	}

	public void setPattern(int i) {
		int p = Config.patternIndizes[i];
		Config.setPattern(p);
		patternSelect.updatePatternPreview();
	}

	public void setColor(int i) {
		colorSelect.setItemSelected(i);
		colorSelect.setDescription(Config.getColorDescription(i));
		Config.setColor(i);
	}

	public static class ZoomPreviewFragment extends AndroidFragmentApplication {
		LWP preview;
		View view;

		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			preview = new LWP();
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
			config.useCompass = false;
			config.useWakelock = false;
			config.useAccelerometer = false;
			view = initializeForView(preview, config);
			view.setBackgroundResource(R.color.transparent);
			return view;
		}

		public void updateYOffset() {
			int[] offset = new int[2];
			view.getLocationOnScreen(offset);
			int height = view.getHeight();
			if (offset[1] < 0) {
				float value = -offset[1] / 2;
				if (value > height / 5)
					value = height / 5;
				preview.setYoffset(value);
			} else {
				preview.setYoffset(0);
			}
		}

	}

	@Override
	public void exit() {
	}

}
