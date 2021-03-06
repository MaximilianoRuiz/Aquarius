package com.example.maximiliano.aquarius.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.maximiliano.aquarius.R;

import java.util.ArrayList;
import java.util.List;

public class Utility {

    public static final String IS_SPLAS = "is_splash";
    public static final String JPG = ".jpg";
    public static final String GIF = ".gif";
    public static final String ACTIONBAR_BACKGROUND = "#FFE8E8E8";
    public static final String EMPTY_STRING = "";

    public static String getURLFileName(String url){
        int lastOcurrencePosition = url.lastIndexOf("/");
        int extentionFilePosition = url.lastIndexOf(JPG);

        if (extentionFilePosition < lastOcurrencePosition)
            extentionFilePosition = url.lastIndexOf(GIF);

        return url.substring(lastOcurrencePosition + 1, extentionFilePosition);
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

    public static boolean isASCOrderPreferenceSelected(Context context){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        String orderAsc = context.getString(R.string.title);
        return orderAsc.equals(pref.getString(context.getString(R.string.order_pref_key), orderAsc));
    }

    public static List<DetailVO> obteinDetailVOList(Context context){
        String[] titles = context.getResources().getStringArray(R.array.titles);
        String[] descriptions = context.getResources().getStringArray(R.array.descriptions);
        String[] urls = context.getResources().getStringArray(R.array.urls);
        int[] priorities = context.getResources().getIntArray(R.array.priorities);

        List<DetailVO> detailVOs = new ArrayList<>();

        for (int i = 0; i < titles.length; i++) {
            DetailVO detailVO = new DetailVO();
            detailVO.setTitle(titles[i]);
            detailVO.setDescription(descriptions[i]);
            detailVO.setURLImage(urls[i]);
            detailVO.setPriority(priorities[i]);
            detailVOs.add(detailVO);
        }
        return detailVOs;
    }
}
