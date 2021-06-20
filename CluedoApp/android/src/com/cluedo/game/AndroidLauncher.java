package com.cluedo.game;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

//in order to implement Motion detection - tutorial used - https://gamefromscratch.com/libgdx-tutorial-6-motion-controls/

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		//config.useGL20=true;

		//tell to enable compass and accelerometer
		config.useCompass=true;
		config.useAccelerometer=true;
		initialize(new MainScreen(), config);
	}
}
