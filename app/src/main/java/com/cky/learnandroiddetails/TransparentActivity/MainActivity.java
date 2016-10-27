package com.cky.learnandroiddetails.TransparentActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.cky.learnandroiddetails.R;

/**
 * 作者：cky
 * 时间：2016/10/27 16:45
 * 描述：
 */

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transparent_act);
    }
}
