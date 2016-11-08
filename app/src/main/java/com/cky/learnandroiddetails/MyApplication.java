package com.cky.learnandroiddetails;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

import com.alipay.euler.andfix.patch.PatchManager;
import com.cky.learnandroiddetails.AndFix.*;

import java.io.File;
import java.io.IOException;

/**
 * Created by cuikangyuan on 16/7/9.
 */
public class MyApplication extends Application {


    private static final String APATCH_PATH = "/cky.apatch";

    private PatchManager mPatchManager;

    @Override
    public void onCreate() {
        super.onCreate();
        //initHotFix();
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

    public void initHotFix() {
        mPatchManager = new PatchManager(this);
        mPatchManager.init("1.0");

        mPatchManager.loadPatch();

        String apatchPatheString = Environment.getExternalStorageDirectory().getAbsolutePath() + APATCH_PATH;
        File apatchFile = new File(apatchPatheString);

        if (apatchFile.exists()) {
            Toast.makeText(MyApplication.this, "补丁文件存在", Toast.LENGTH_LONG).show();
            try {
                mPatchManager.addPatch(apatchPatheString);
            } catch (IOException e) {
                Toast.makeText(MyApplication.this, "修复失败", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(MyApplication.this, "补丁文件不存在", Toast.LENGTH_LONG).show();
        }
    }
}
