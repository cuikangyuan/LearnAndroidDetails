package com.cky.learnandroiddetails.ActivityLifeCycleTest;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.cky.learnandroiddetails.R;

public class ActivityLifeCycleTestSecondAct extends Activity {

    private static final String TAG = "ActivityLifeCycleTest->";

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d(TAG, "onConfigurationChanged:  orientation->" +newConfig.orientation + " screenWidthDp-> "+ newConfig.screenWidthDp + " screenHeightDp-> " +  newConfig.screenHeightDp);
    }

    /*
        * EditText设置id 后, 系统才会自动为我们保存 并恢复相关的状态
        * */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "2 onRestoreInstanceState: ");

        /*
        * 只要调用到该方法 savedInstanceState就不会为空
        * 不像在onCreate中 需要对savedInstanceState进行判断
        * */
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, "2 onNewIntent: ");
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "2 onSaveInstanceState: ");
    }

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
