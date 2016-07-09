package com.cky.learnandroiddetails;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/**
 * Created by cuikangyuan on 16/7/9.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //API 14 引入的 用来监听所以Activity的生命回调
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {
                MyActivityManager.getInstance().setCurrentActivity(activity);
            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }
}
