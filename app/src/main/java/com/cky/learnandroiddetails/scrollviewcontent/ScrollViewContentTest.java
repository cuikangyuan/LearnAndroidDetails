package com.cky.learnandroiddetails.scrollviewcontent;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cky.learnandroiddetails.R;

public class ScrollViewContentTest extends AppCompatActivity {


    private HorizontalScrollView horizontalScrollView;

    private LinearLayout linearLayout;

    private ImageView mImageView1;
    private ImageView mImageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_view_content_test);

        horizontalScrollView = (HorizontalScrollView) findViewById(R.id.scroll_view);

        linearLayout = (LinearLayout) findViewById(R.id.linear_layout);


        mImageView1 = (ImageView) findViewById(R.id.image1);
        mImageView2 = (ImageView) findViewById(R.id.image2);

        init();

        startAnimation(mImageView1, -20f, 20f);
        startAnimation(mImageView2, -5f, 5f);
    }

    private void startAnimation(View view, float a, float b) {
        //摇摆
        RotateAnimation mAnimation =new RotateAnimation(
                a,b,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);

        mAnimation.setDuration(600);
        mAnimation.setRepeatCount(Animation.INFINITE);
        mAnimation.setRepeatMode(Animation.REVERSE);
        view.setAnimation(mAnimation);
        mAnimation.start();
    }
    private void init() {

        int totalWidth = 0;

        for (int i = 0; i < 15; i++) {
            TextView textView = new TextView(this);
            textView.setText("标签栏目" + i);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0, 0, 20, 0);
            textView.setLayoutParams(layoutParams);
            linearLayout.addView(textView);


            textView.measure(0, 0);
            totalWidth += textView.getMeasuredWidth();
        }

        Log.d("TAG", "子View总宽度: " + totalWidth + "");
        linearLayout.measure(0, 0);
        Log.d("TAG", "父view宽度: " + linearLayout.getMeasuredWidth());
/*
        for (int i=0; i < linearLayout.getChildCount(); i++) {
            TextView view = (TextView) linearLayout.getChildAt(i);
            if (view != null && view.getVisibility() == View.GONE) {
                Log.d("TAG", "存在未显示的，未显示的第一个子View : " + view.getText());
                break;
            }
        }
*/

        horizontalScrollView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                // Ensure you call it only once :
                horizontalScrollView.getViewTreeObserver().removeGlobalOnLayoutListener(this);

                // Here you can get the size :)

                Rect scrollBounds = new Rect();
                horizontalScrollView.getDrawingRect(scrollBounds);

                for (int i=0; i < linearLayout.getChildCount(); i++) {
                    TextView view = (TextView) linearLayout.getChildAt(i);

                    int rightEdge = view.getRight();
                    int leftEdge = rightEdge - view.getWidth();
                    int width = view.getWidth();
                    /*
                    if ((scrollBounds.right > (rightEdge + width) && scrollBounds.left < leftEdge)) {
                        //return true;
                    } else {
                        //return false;
                        Log.d("TAG", "存在未显示的，View : " + view.getText());
                    }
                    */
                    if (rightEdge > scrollBounds.right) {
                        Log.d("TAG", "存在未显示的，View : " + view.getText());
                    }

                }

            }
        });
    }
}
