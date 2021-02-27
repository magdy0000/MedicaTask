package com.magdy.medicatask.data.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class MyPreference {
    //variables
    private static Context mAppContext = null;
    private final static String mySharedPreferenceName = "user date";

    private final static String mySharedPreference_userToken = "userToken";


    private MyPreference() {
    }
    //methods

    public static void init(Context appContext) {
        mAppContext = appContext;
    }

    private static SharedPreferences getSharedPreferences() {
        return mAppContext.getSharedPreferences(mySharedPreferenceName, Context.MODE_PRIVATE);
    }



    public static void setUserToken(String userToken) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString(mySharedPreference_userToken, userToken).apply();
    }
    public static String getUserToken() {
        return getSharedPreferences().getString(mySharedPreference_userToken, "");
    }

}