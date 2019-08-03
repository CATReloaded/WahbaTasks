package com.andalus.wahbatasks;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;

public class SettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        addPreferencesFromResource(R.xml.pref_room_task);

        SharedPreferences sharedPreferences= getPreferenceScreen().getSharedPreferences();
        PreferenceScreen prefScreen=getPreferenceScreen();
        int count =prefScreen.getPreferenceCount();

        for(int i =0 ; i < count ; i++)
        {
            Preference p=prefScreen.getPreference(i);
                    if(!( p instanceof CheckBoxPreference))
                    {
                        String value =sharedPreferences.getString(p.getKey(),"");
                        setPreferenceSummary(p, value);
                    }
        }
    }

    private void setPreferenceSummary(Preference preference, String value)
    {
        if( preference instanceof ListPreference)
        {
            ListPreference listPreference=(ListPreference) preference;
            int index=listPreference.findIndexOfValue(value);
            if(index>0) listPreference.setSummary(listPreference.getEntries()[index]);
        }
    }
}
