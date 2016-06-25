package com.cky.learnandroiddetails;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by cuikangyuan on 16/6/25.
 */
public class MyIntentService extends IntentService{

    private static final String TAG = MyIntentService.class.getSimpleName();

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "Thread id is " + Thread.currentThread().getId());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy executed");
    }
}
