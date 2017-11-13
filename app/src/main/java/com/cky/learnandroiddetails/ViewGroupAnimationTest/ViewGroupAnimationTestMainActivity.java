package com.cky.learnandroiddetails.ViewGroupAnimationTest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LayoutAnimationController;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.LinearLayout;

import com.cky.learnandroiddetails.R;

public class ViewGroupAnimationTestMainActivity extends AppCompatActivity {

    LinearLayout mLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_group_animation_test_main);

        mLayout = (LinearLayout) findViewById(R.id.root);

        //android:animateLayoutChanges="true" 这个会使用默认的动画 可以使用LayoutAnimationController替换掉

        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1, 0, 1);
        scaleAnimation.setDuration(500);


        LayoutAnimationController layoutAnimationController = new LayoutAnimationController(scaleAnimation);
        layoutAnimationController.setOrder(LayoutAnimationController.ORDER_NORMAL);


        findViewById(R.id.add_button).setOnClickListener(v-> {
            Button button = new Button(ViewGroupAnimationTestMainActivity.this);
            button.setText("ddddddd");
            mLayout.addView(button);
            mLayout.setLayoutAnimation(layoutAnimationController);
        });
    }
}
