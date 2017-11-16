package com.cky.learnandroiddetails.ActivityLifeCycleTest;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.cky.learnandroiddetails.R;

public class ActivityLifeCycleTestSecondAct extends Activity {

    private static final String TAG = "ActivityLifeCycleTest->";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle_test_second);
        Log.d(TAG, "2 onCreate: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "2 onStart: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "2 onRestart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "2 onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "2 onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "2 onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "2 onDestroy: ");
    }
}
