package com.designurway.idlidosa.a.utils;

import android.content.Context;
import android.content.SharedPreferences;


public class SharedPrefManager {
    static SharedPreferences preferences;
    public static final String PREF_TOTAL_KEY = "pref_total_key";
    public static final String PREF_NAME = "pref_total_key";
    Context context;

    public SharedPrefManager(Context context) {
        this.context = context;

    }

    public static void SaveTotalKey(Context context, int total) {
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(PREF_TOTAL_KEY, total);
        editor.apply();
    }

    public static int loadFrompref(Context context) {
        SharedPreferences rpref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return rpref.getInt(PREF_TOTAL_KEY, 0);

    }

    public static void registerPrif(Context context, SharedPreferences.OnSharedPreferenceChangeListener lisner) {
        SharedPreferences rpref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        rpref.registerOnSharedPreferenceChangeListener(lisner);
    }

    public static void unregisterPref(Context context, SharedPreferences.OnSharedPreferenceChangeListener lisner){
        SharedPreferences rpref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        rpref.unregisterOnSharedPreferenceChangeListener(lisner);
    }


    public static void Clear(Context context){
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
    }

}
