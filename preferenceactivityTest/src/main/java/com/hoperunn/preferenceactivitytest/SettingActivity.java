package com.hoperunn.preferenceactivitytest;

import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.PreferenceActivity;
import android.view.Window;

import androidx.annotation.Nullable;

public class SettingActivity extends PreferenceActivity {
    public static final String PREFER_NAME = "setting";
    private EditTextPreference mVadbosPreference;
    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
       // requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);

//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);


        getPreferenceManager().setSharedPreferencesName(PREFER_NAME);
        addPreferencesFromResource(R.xml.setting);
        EditTextPreference editTextPreference = (EditTextPreference) findPreference("setting_preference");


    }
}
