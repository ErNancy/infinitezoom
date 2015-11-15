package net.nikkki.infinitezoom;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class _Setting_PatternDialog extends DialogFragment {

	int[] patternImages;
	int[] patternIndizes;
	String[] patternNames;
	
	private ClickInterface clickInterface;
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		LayoutInflater inflater = getActivity().getLayoutInflater();

		View view = inflater.inflate(R.layout.pattern_grid, null);		
		GridView gridView = (GridView) view.findViewById(R.id.gridView);
		GridViewAdapter adapter = new GridViewAdapter(getContext(), R.layout.pattern_grid_item, getData());

		gridView.setAdapter(adapter);
		builder.setView(view);
		Dialog dialog = builder.create();
		gridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v, int i, long id) {
				clickInterface.patternItemClicked(i);
				getDialog().dismiss();
			}
		});
		
		return dialog;
	}
	
	
	private ArrayList getData() {
		final ArrayList imageItems = new ArrayList();
		
		patternImages = Config.patternImages;
		patternIndizes = Config.patternIndizes;
		patternNames = Config.patternNames;
	
		for (int i = 0; i < patternIndizes.length; i++) {
			int image = patternImages[i];
			String title = patternNames[i];
			int index = patternIndizes[i];
			imageItems.add(new ImageItem(image, title, index));
		}
		return imageItems;
	}

	public void setClickInterface(ClickInterface theInterface) {
		this.clickInterface = theInterface;
	}

	public interface ClickInterface {
		public void patternItemClicked(int index);
	}
}
