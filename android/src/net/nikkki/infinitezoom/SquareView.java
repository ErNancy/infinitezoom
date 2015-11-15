package net.nikkki.infinitezoom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class SquareView extends FrameLayout {
	
	public SquareView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
	    super.onMeasure(widthMeasureSpec, widthMeasureSpec);
	}
}
