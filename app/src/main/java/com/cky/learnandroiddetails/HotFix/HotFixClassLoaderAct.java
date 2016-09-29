package com.cky.learnandroiddetails.HotFix;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.cky.learnandroiddetails.R;

public class HotFixClassLoaderAct extends AppCompatActivity {

    private static final String TAG = HotFixClassLoaderAct.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_fix_class_loader);

        //LogOutClassLoaderName();
    }

    private void LogOutClassLoaderName() {
        ClassLoader classLoader = HotFixClassLoaderAct.class.getClassLoader();

        while (classLoader != null) {

            Log.d(TAG, classLoader.toString());

            classLoader = classLoader.getParent();
        }
    }

    private void loadHotFixPack() {

    }
}
