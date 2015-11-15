package net.nikkki.infinitezoom;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class Config {
	public static final Preferences preferences = Gdx.app.getPreferences("preferences");
	private final static String PATTERNKEY = "As3sdf3";
	private final static String COLORKEY   = "s4SD5ds";
	private final static String SPEEDKEY   = "gs4f9sd";
	
	public static boolean ANDROID = false;
	
	public static int selectedPattern = 0;
	public static int colorStyle = 0;
	public static int speed;

	public static float zoomSpeed = 0f;
	public static boolean pause = false;
	
	public static int[] patternImages;
	public static int[] patternIndizes;
	public static String[] patternNames;

	public static final String[] colorDescriptions = {"Random","Animated","Bright","Contrast"};

	public static void load() {
		if (!preferences.contains(PATTERNKEY) ||
			!preferences.contains(COLORKEY) ||
			!preferences.contains(SPEEDKEY) ) {

			preferences.putInteger(PATTERNKEY, 0);
			preferences.putInteger(COLORKEY, 0);
			preferences.putInteger(SPEEDKEY, 10);
			preferences.flush();
		}
		
		selectedPattern = preferences.getInteger(PATTERNKEY, 0);
		
		colorStyle = preferences.getInteger(COLORKEY, 0);		
		speed = preferences.getInteger(SPEEDKEY, 10);
		updateSpeed();
		
	}

	public static void checkForChanges() {
		
		selectedPattern = preferences.getInteger(PATTERNKEY, 18);
		colorStyle = preferences.getInteger(COLORKEY, 0);

		speed = preferences.getInteger(SPEEDKEY, 10);
		updateSpeed();
	
	}

	public static void updateSpeed(){
		loadZoomSpeed();
	}
	public static void loadZoomSpeed() {
		zoomSpeed = speed /100f * 2f + 0.5f;		
		if (!ANDROID) {
			zoomSpeed = 0.75f;
		}
			
	}
	public static int getSpeed() {
		return speed;
	}
	public static String getSpeedText(){
		if (speed < 10) {
			return "Very slow";
		} else if (speed <= 25) {
			return "Slow";
		} else if (speed <= 50) {
			return "Average";			
		} else if (speed <= 75) {
			return "Fast";						
		} else {
			return "Very fast";									
		}
	}
	
	public static void setSpeed(int value) {
		speed = value;
		updateSpeed();
		preferences.putInteger(SPEEDKEY, value);
		preferences.flush();
	}
	public static void setPattern(int pattern) {
		selectedPattern = pattern;
		preferences.putInteger(PATTERNKEY, pattern);
		preferences.flush();
	}
	public static void setColor(int style) {
		colorStyle = style;
		preferences.putInteger(COLORKEY, style);
		preferences.flush();
	}
	public static void save() {
		preferences.putInteger(PATTERNKEY, selectedPattern);
		preferences.putInteger(COLORKEY, colorStyle);
		preferences.putInteger(SPEEDKEY, speed);
		preferences.flush();
	}
	public static int getIndexOfSelected() {
		return getIndexOfPattern(selectedPattern);
	}
	public static int getColorStyle() {
		return colorStyle;
	}
	public static String getColorDescription() {
		return colorDescriptions[getColorStyle()];
	}
	public static String getColorDescription(int i) {
		return colorDescriptions[i];
	}
	public static int getIndexOfPattern(int pattern){
	    for (int i = 0; i < patternIndizes.length; i++) {
	        if (patternIndizes[i] == pattern) {
	            return i;
	        }
	    }
	    return -1;
	}
}
