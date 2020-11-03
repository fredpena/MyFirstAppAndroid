package com.pucmm.app;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

public class SharedPreferencesStore {

    private static final String PREF_NAME = "PUCMM";
    private static final int PRIVATE_MODE = 0;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public SharedPreferencesStore(Context context) {
        preferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = preferences.edit();
    }

    public void put(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public String getString(String key) {
        return preferences.getString(key, "");
    }

    public int getInt(String key) {
        return preferences.getInt(key, 0);
    }

    public Map<String, ?> getAll() {
        return preferences.getAll();
    }

    public void remove(String key) {
        if (editor.remove(key) != null) {
            editor.commit();
        }
    }
}
