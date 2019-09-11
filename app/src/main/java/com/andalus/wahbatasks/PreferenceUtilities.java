package com.andalus.wahbatasks;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;

public class PreferenceUtilities
{

    private static int mediaPlayerIndex=0;
    private static String indexKey="key";
    private static int defaultIndex=0;

    public static int getMediaPlayerIndex(Context context)
    {
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(context);
        int index=sharedPreferences.getInt(indexKey, defaultIndex);
        return index;
    }

    public static void incrementMediaPlayerIndex(Context context)
    {
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt(indexKey, ++mediaPlayerIndex);
    }
    public static void decrementMediaPlayerIndex(Context context)
    {
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt(indexKey, --mediaPlayerIndex);
    }



}
