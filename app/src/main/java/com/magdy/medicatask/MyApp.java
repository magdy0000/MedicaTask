package com.magdy.medicatask;

import android.app.Application;

import com.magdy.medicatask.data.preference.MyPreference;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MyPreference.init(this);
    }
}
