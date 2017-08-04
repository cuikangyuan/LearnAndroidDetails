package com.cky.learnandroiddetails.LayoutTransition;

import android.animation.AnimatorInflater;
import android.animation.LayoutTransition;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.cky.learnandroiddetails.R;

/**
 *
 * 容器动画
 *
 * 每一个叶子View需要动画时，它的直接父容器需要设置动画属性
 *
 * android:animateLayoutChanges="true"
 *
 */
public class LayoutTransitionMainActivity extends AppCompatActivity {

    RelativeLayout mRoot;

    Button mButton1;
    Button mButton2;
    Button mButton3;
    LinearLayout mButton3Container;

    LayoutTransition mLayoutTransition = new LayoutTransition();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_transition_main);

        mRoot = (RelativeLayout) findViewById(R.id.root);
        mButton1 = (Button) findViewById(R.id.button1);
        mButton2 = (Button) findViewById(R.id.button2);
        mButton3 = (Button) findViewById(R.id.button3);
        mButton3Container = (LinearLayout) findViewById(R.id.button3_container);

        mLayoutTransition.setDuration(2000);
        //mLayoutTransition.setAnimator(LayoutTransition.APPEARING, AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.layout_transition_anim));
        mLayoutTransition.setAnimator(LayoutTransition.APPEARING,mLayoutTransition.getAnimator(LayoutTransition.APPEARING));
        mLayoutTransition.setAnimator(LayoutTransition.CHANGE_APPEARING, mLayoutTransition.getAnimator(LayoutTransition.CHANGE_APPEARING));
        mLayoutTransition.setAnimator(LayoutTransition.DISAPPEARING, mLayoutTransition.getAnimator(LayoutTransition.DISAPPEARING));
        mLayoutTransition.setAnimator(LayoutTransition.CHANGE_DISAPPEARING,mLayoutTransition.getAnimator(LayoutTransition.CHANGE_DISAPPEARING));
        mRoot.setLayoutTransition(mLayoutTransition);


        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mButton2.getVisibility() == View.VISIBLE) {
                    mButton2.setVisibility(View.GONE);
                } else {
                    mButton2.setVisibility(View.VISIBLE);
                }
/*
                if(mButton3.getVisibility() == View.VISIBLE) {
                    mButton3.setVisibility(View.GONE);
                } else {
                    mButton3.setVisibility(View.VISIBLE);
                }
*/

                if (mButton3Container.getVisibility() == View.VISIBLE) {
                    mButton3Container.setVisibility(View.GONE);
                } else {
                    mButton3Container.setVisibility(View.VISIBLE);
                }

            }
        });

    }
}
