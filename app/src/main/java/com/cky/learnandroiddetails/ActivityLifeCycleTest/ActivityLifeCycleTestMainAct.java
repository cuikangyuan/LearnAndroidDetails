package com.cky.learnandroiddetails.ActivityLifeCycleTest;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.cky.learnandroiddetails.R;

public class ActivityLifeCycleTestMainAct extends Activity {

    private static final String TAG = "ActivityLifeCycleTest->";


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "1 onRestoreInstanceState: ");
        //通常在onStart之后执行
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, "1 onNewIntent: ");
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "1 onSaveInstanceState: ");
        //通常在onStop之前调用，但是可能在onPause之前 也可能在之后
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle_test_main);

        Log.d(TAG, "1 onCreate: ");

        findViewById(R.id.button).setOnClickListener(v-> {

                Intent intent = new Intent(ActivityLifeCycleTestMainAct.this, ActivityLifeCycleTestSecondAct.class);
                startActivity(intent);

        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "1 onRestart: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "1 onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "1 onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "1 onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "1 onStop: ");
        //如果新的activity采用透明的主题，那么启动这个activity的活动只会调用onPause 不会调用onStop
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "1 onDestroy: ");
    }
}
