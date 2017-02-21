package com.cky.learnandroiddetails.valueAnimForPayBarCode;

import android.animation.ValueAnimator;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.cky.learnandroiddetails.R;

public class DialogHeightChangeWithAnimMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_height_change_with_anim_main);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initDialog();
            }
        });


        //initDialog();
    }

    private void initDialog() {
        float mDensity = getResources().getDisplayMetrics().density;

        int mHiddenViewMeasuredHeight = (int) (mDensity * 120 + 0.5);

        AlertDialog dialog = new AlertDialog.Builder(DialogHeightChangeWithAnimMainActivity.this).create();

        dialog.show();

        dialog.setCanceledOnTouchOutside(true);

        Window window = dialog.getWindow();

        window.setContentView(R.layout.height_change_dialog);

        //ImageView dialogImage = (ImageView) window.findViewById(R.id.dialog_image);

        //TextView dialigText = (TextView) window.findViewById(R.id.dialog_text);

        //AnimatorUtil.animHeightToView(this, dialigText, true, 1000);

        //animateOpen(dialogImage, 600);
        ImageView dialogImage1 = (ImageView) window.findViewById(R.id.dialog_image1);

        ImageView dialogImage2 = (ImageView) window.findViewById(R.id.dialog_image2);

        AnimatorUtil.animHeightToView(this, dialogImage2, true, 800);
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
