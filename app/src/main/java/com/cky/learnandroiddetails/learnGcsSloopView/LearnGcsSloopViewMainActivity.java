package com.cky.learnandroiddetails.learnGcsSloopView;

import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.ColorRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cky.learnandroiddetails.R;

public class LearnGcsSloopViewMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_gcs_sloop_view_main);

        initCanvas();

    }

    //定义颜色
    private void initColor() {

        int color = Color.BLUE;

        color = Color.argb(127, 255, 0, 0);

        color = 0xaaff0000;

        color = this.getResources().getColor(R.color.aqua);
    }

    //画布相关
    private void initCanvas() {
        //Canvas canvas = new Canvas();
        //canvas.drawColor(Color.BLUE);
    }

}
