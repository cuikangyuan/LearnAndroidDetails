package com.cky.learnandroiddetails;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.jaeger.library.StatusBarUtil;

/**
 * 作者：cky
 * 时间：2016/9/22 13:32
 * 描述：测试沉浸式模式
 * 参考：http://jaeger.itscoder.com/android/2016/03/27/statusbar-util.html
 */

public class TestImmersiveModeAct extends AppCompatActivity {

    ImageView mImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mImageView = (ImageView) findViewById(R.id.iv_banner);

        setContentView(R.layout.activity_test_immersive_mode);
        //showImmersiveMode();

        StatusBarUtil.setTranslucentForImageView(TestImmersiveModeAct.this, 0, mImageView);

    }
    //【废弃】
    private void showImmersiveMode() {
        //要求系统5.0+
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = (View.SYSTEM_UI_FLAG_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

    }
}
