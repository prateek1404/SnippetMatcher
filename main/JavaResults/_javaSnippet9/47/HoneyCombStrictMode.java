package mc.app.components.calendarwidget.utils;

import android.os.StrictMode;

public class HoneyCombStrictMode implements IStrictMode {

	public void enableStrictMode() {
		// enable StrictMode for GingerBread based devices
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
		.detectAll()
		.penaltyLog()
		.penaltyFlashScreen()
		.build());
	}

}
