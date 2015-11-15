package net.nikkki.infinitezoom;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class _Setting_Speed extends _Setting_Item implements OnSeekBarChangeListener{
	
	protected SpeedInterface speedInterface;
	SeekBar seekbar;
	private int maxValue      = 100;
	private int minValue      = 0;
	private int interval      = 1;
	private int seekbarProgress;
	
	
	public static _Setting_Speed newInstance(int layoutResource, int initSpeed, String initDescription) {
		Bundle arguments = new Bundle();
		arguments.putString("initDescription", initDescription);
		arguments.putInt("initSpeed", initSpeed);
		arguments.putInt("layoutResource", layoutResource);
		_Setting_Speed fragment = new _Setting_Speed();
		fragment.setArguments(arguments);
		return fragment;
	}
	
	
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(getArguments().getInt("layoutResource"),
				container, false);

		
		seekbar = (SeekBar) view.findViewById(R.id.seekBarSpeed);
		seekbar.setMax(maxValue - minValue);
		seekbar.setOnSeekBarChangeListener(this);
		
		String initDescription = getArguments().getString("initDescription");
		if (initDescription != null) {
			setDescription(initDescription);
		}
		seekbarProgress = getArguments().getInt("initSpeed", 0);
		seekbar.setProgress(seekbarProgress);
		
		return view;
	}
	
	
	
	
	
	
	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		// TODO Auto-generated method stub
		speedInterface.speedChanged(progress);
		
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}
	public void setSpeedInterface(SpeedInterface theInterface) {
		this.speedInterface = theInterface;
	}

	public interface SpeedInterface {
		public void speedChanged(int value);
	}
	
}
