package com.cky.learnandroiddetails.sharedPreferencesTest;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cky.learnandroiddetails.R;

public class SharedPreferenceMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preference_main);
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences preferences1 = getPreferences(MODE_PRIVATE);
        preferences1.edit().putString("preferences1", "activity_getPreferences").apply();

        SharedPreferences preferences2 = getSharedPreferences("my_sp", MODE_PRIVATE);
        preferences2.edit().putString("preferences2", "context_getSharedPreferences").apply();

        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        defaultSharedPreferences.edit().putString("defaultSharedPreferences", "PreferenceManager getDefaultSharedPreferences").apply();
    }
}
