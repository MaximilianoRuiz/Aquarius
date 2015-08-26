package com.example.maximiliano.aquarius;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Utility {

    private static final String IS_SPLAS = "is_splash";

    public static String getURLFileName(String url){
        int last = url.lastIndexOf("/");
        int fin = url.lastIndexOf(".jpg");
        if (fin < last)
            fin = url.lastIndexOf(".gif");

        return url.substring(last + 1, fin);
    }

    public static boolean isSplashShow(Context context){
        SharedPreferences preferences =  PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getBoolean(IS_SPLAS, true);
    }

    public static void stopSplashShow(Context context){
        SharedPreferences preferences =  PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(IS_SPLAS, false);
        editor.commit();
    }
}
