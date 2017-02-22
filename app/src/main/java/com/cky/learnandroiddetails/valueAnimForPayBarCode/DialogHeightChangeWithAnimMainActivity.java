package com.cky.learnandroiddetails.valueAnimForPayBarCode;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cky.learnandroiddetails.R;

public class DialogHeightChangeWithAnimMainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_height_change_with_anim_main);

        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initDialog();
                //showAnim();
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAnim();
            }
        });

        //initDialog();
    }

    private void showAnim() {
        final View rootLayout = findViewById(R.id.root_layout);

        final ViewGroup.LayoutParams layoutParams = rootLayout.getLayoutParams();

        int targetHeight = getHeight(rootLayout);

        ValueAnimator animator = ValueAnimator.ofInt(0, targetHeight);

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int animatedValue = (int) animation.getAnimatedValue();

                layoutParams.height = animatedValue;
                Log.d("TAG", animatedValue + "");
                rootLayout.setLayoutParams(layoutParams);
            }
        });

        animator.setDuration(1000);

        animator.start();
    }

    private void initDialog() {
        float mDensity = getResources().getDisplayMetrics().density;

        int mHiddenViewMeasuredHeight = (int) (mDensity * 120 + 0.5);

        AlertDialog dialog = new AlertDialog.Builder(DialogHeightChangeWithAnimMainActivity.this).create();

        dialog.show();

        dialog.setCanceledOnTouchOutside(true);

        final Window window = dialog.getWindow();

        //View dialogView = LayoutInflater.from(this).inflate(R.layout.height_change_dialog, null);

        //dialogView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        window.setContentView(R.layout.height_change_dialog);

        //ImageView dialogImage = (ImageView) window.findViewById(R.id.dialog_image);

        //TextView dialigText = (TextView) window.findViewById(R.id.dialog_text);

        //AnimatorUtil.animHeightToView(this, dialigText, true, 1000);

        //animateOpen(dialogImage, 600);
        //ImageView dialogImage1 = (ImageView) window.findViewById(R.id.dialog_image1);

        //final ImageView dialogImage2 = (ImageView) window.findViewById(R.id.dialog_image2);

        //AnimatorUtil.animHeightToView(this, dialogImage2, true, 800);



        final View rootLayout = window.findViewById(R.id.root_layout);

        final ViewGroup.LayoutParams layoutParams = rootLayout.getLayoutParams();

        int targetHeight = getHeight(rootLayout);

        ValueAnimator animator = ValueAnimator.ofInt(0, targetHeight);

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int animatedValue = (int) animation.getAnimatedValue();

                layoutParams.height = animatedValue;
                Log.d("TAG", animatedValue + "");
                rootLayout.setLayoutParams(layoutParams);

                rootLayout.requestLayout();
            }
        });


        animator.setDuration(1000);

        animator.start();
    }




    private int getHeight(View view) {

        int height = 0;

        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(1000, View.MeasureSpec.AT_MOST);
        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(1000, View.MeasureSpec.AT_MOST);

        view.measure(widthMeasureSpec, heightMeasureSpec);

        return view.getMeasuredHeight();

    }

    private void animateOpen(View v, int mHiddenViewMeasuredHeight) {
        v.setVisibility(View.VISIBLE);
        ValueAnimator animator = createDropAnimator(v, 0,
                mHiddenViewMeasuredHeight);
        animator.start();

    }

    private ValueAnimator createDropAnimator(final View v, int start, int end) {
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator arg0) {
                int value = (int) arg0.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
                layoutParams.height = value;
                v.setLayoutParams(layoutParams);

            }
        });
        return animator;
    }
}
