package com.cky.learnandroiddetails.FlipAnimTest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.cky.learnandroiddetails.R;

/**
 * http://blog.csdn.net/oQiHaoGongYuan/article/details/49869137
 */

public class FlipAnimTestAct extends AppCompatActivity {

    private ImageView imageView1;
    private ImageView imageView2;

    //x缩小
    private ScaleAnimation scaleAnimation1 = new ScaleAnimation(
            1,
            0,
            1,
            1,
            Animation.RELATIVE_TO_PARENT,
            0.5f,
            Animation.RELATIVE_TO_PARENT,
            0.5f);
    //x放大
    private ScaleAnimation scaleAnimation2 = new ScaleAnimation(
            0,
            1,
            1,
            1,
            Animation.RELATIVE_TO_PARENT,
            0.5f,
            Animation.RELATIVE_TO_PARENT,
            0.5f);
    //y缩小
    private ScaleAnimation scaleAnimation3 = new ScaleAnimation(
            1,
            1,
            1,
            0,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f);
    //y放大
    private ScaleAnimation scaleAnimation4 = new ScaleAnimation(
            1,
            1,
            0,
            1,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flip_anim_test);

        init();
    }

    private void init() {
        imageView1 = (ImageView) findViewById(R.id.image1);
        imageView2 = (ImageView) findViewById(R.id.image2);

        showImage1();

        scaleAnimation1.setDuration(500);
        scaleAnimation2.setDuration(500);

        scaleAnimation3.setDuration(500);
        scaleAnimation4.setDuration(500);

        scaleAnimation1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //x的动画

                if (imageView1.getVisibility() == View.VISIBLE) {
                    imageView1.clearAnimation();
                    showImage2();
                    imageView2.startAnimation(scaleAnimation2);
                } else {
                    imageView2.clearAnimation();
                    showImage1();
                    imageView1.startAnimation(scaleAnimation2);
                }

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        scaleAnimation3.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //y的动画
                if (imageView1.getVisibility() == View.VISIBLE) {
                    imageView1.clearAnimation();
                    showImage2();
                    imageView2.startAnimation(scaleAnimation4);
                } else {
                    imageView2.clearAnimation();
                    showImage1();
                    imageView1.startAnimation(scaleAnimation4);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });




        findViewById(R.id.root).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //x的动画
                /*
                if (imageView1.getVisibility() == View.VISIBLE) {
                    imageView1.startAnimation(scaleAnimation1);
                } else {
                    imageView2.startAnimation(scaleAnimation1);
                }
                */
                //y的动画
                if (imageView1.getVisibility() == View.VISIBLE) {
                    imageView1.startAnimation(scaleAnimation3);
                } else {
                    imageView2.startAnimation(scaleAnimation3);
                }
            }
        });
    }

    private void showImage1() {
        imageView1.setVisibility(View.VISIBLE);
        imageView2.setVisibility(View.GONE);
    }

    private void showImage2() {
        imageView1.setVisibility(View.GONE);
        imageView2.setVisibility(View.VISIBLE);
    }
}
