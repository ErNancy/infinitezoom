package net.nikkki.infinitezoom;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class _Setting_PatternItem extends _Setting_Item {

	private TextView patternTitle;
	private ImageView patternIcon;
	
	public static _Setting_PatternItem newInstance() {
		_Setting_PatternItem fragment = new _Setting_PatternItem();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.setting_item_pattern, container, false);
		
		ViewGroup items = (ViewGroup) view.findViewById(R.id.items);
		for (int i = items.getChildCount() - 1; i >= 0; i--) {
            final View item = items.getChildAt(i);
            item.setOnClickListener(new OnClickListener() {
            	@Override
            	public void onClick(View v) {
            		clickInterface.itemClicked(item);
            	}
            });
        }

		patternTitle = (TextView) view.findViewById(R.id.pattern).findViewById(R.id.description);
		patternIcon = (ImageView) view.findViewById(R.id.pattern).findViewById(R.id.icon);
		
		updatePatternPreview();
		
		return view;
	}
	
	
	public void updatePatternPreview() {
		int i = Config.getIndexOfSelected();
		patternTitle.setText(Config.patternNames[i]);
		patternIcon.setImageResource(Config.patternImages[i]);
	}
	
	
	
}
