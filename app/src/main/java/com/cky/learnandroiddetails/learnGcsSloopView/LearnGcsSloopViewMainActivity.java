package com.cky.learnandroiddetails.learnGcsSloopView;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.drawable.Animatable;
import android.support.annotation.ColorRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.cky.learnandroiddetails.R;

import java.util.ArrayList;

public class LearnGcsSloopViewMainActivity extends AppCompatActivity {

    PieView mPieView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_gcs_sloop_view_main);

        //initCanvas();
        //initView();

        ImageView imageView = (ImageView) findViewById(R.id.image1);
        findViewById(R.id.button).setOnClickListener(v -> {
            ((Animatable)imageView.getDrawable()).start();
        });


        imageView.setOnClickListener(v -> {

        });

    }

    private void initView() {
        /*
        mPieView = (PieView) findViewById(R.id.pie_view);

        ArrayList<PieData> data = new ArrayList();

        for (int i = 1; i <= 5; i++) {
            PieData pieData = new PieData();
            pieData.setmValue(i * (i + 9));
            data.add(pieData);
        }

        mPieView.setData(data);
        */
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
