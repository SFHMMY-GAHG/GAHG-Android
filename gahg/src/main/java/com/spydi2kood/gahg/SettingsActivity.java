package com.spydi2kood.gahg;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by spiros on 4/6/14.
 */
public class SettingsActivity extends PreferenceActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.settings_main);
	}
}
