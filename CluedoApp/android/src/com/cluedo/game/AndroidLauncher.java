package com.cluedo.game;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

//https://gamefromscratch.com/libgdx-tutorial-6-motion-controls/

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new MainScreen(), config);

		//config.useGL20=true;

		//enable Accelerometer and Compass
		config.useAccelerometer=true;
		config.useCompass=true;

		//initialize(new MotionDemo(), config);
	}
}
