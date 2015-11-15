package net.nikkki.infinitezoom;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class _Setting_Item extends Fragment {

	protected View view;
	protected ClickInterface clickInterface;

	public static _Setting_Item newInstance(int layoutResource) {
		Bundle arguments = new Bundle();
		arguments.putInt("layoutResource", layoutResource);
		_Setting_Item fragment = new _Setting_Item();
		fragment.setArguments(arguments);
		return fragment;
	}
	public static _Setting_Item newInstance(int layoutResource, int initSelected, String initDescription) {
		Bundle arguments = new Bundle();
		arguments.putString("initDescription", initDescription);
		arguments.putInt("initSelected", initSelected);
		arguments.putInt("layoutResource", layoutResource);
		_Setting_Item fragment = new _Setting_Item();
		fragment.setArguments(arguments);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(getArguments().getInt("layoutResource"),
				container, false);

		ViewGroup items = (ViewGroup) view.findViewById(R.id.items);
		if (items != null) {			
			for (int i = items.getChildCount() - 1; i >= 0; i--) {
				final View item = items.getChildAt(i);
				item.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						clickInterface.itemClicked(item);
					}
				});
			}
		}
		int itemSelected = getArguments().getInt("initSelected", -1);
		if (itemSelected >= 0) {
			setItemSelected(itemSelected);
		}
		String initDescription = getArguments().getString("initDescription");
		if (initDescription != null) {
			setDescription(initDescription);
		}
		return view;
	}
	public void setDescription(String description) {
		TextView text = (TextView) view.findViewById(R.id.description);
		text.setText(description);
	}
	
	public void setItemSelected(int j) {
		ViewGroup items = (ViewGroup) view.findViewById(R.id.items);
		for (int i = items.getChildCount() - 1; i >= 0; i--) {
			final View item = items.getChildAt(i);
			if(i == j)
				item.setBackgroundResource(R.drawable.button_bg_selected);
			else
				item.setBackgroundResource(R.drawable.button_bg);
		}
	}

	public void setClickInterface(ClickInterface theInterface) {
		this.clickInterface = theInterface;
	}

	public interface ClickInterface {
		public void itemClicked(View v);
	}

}
