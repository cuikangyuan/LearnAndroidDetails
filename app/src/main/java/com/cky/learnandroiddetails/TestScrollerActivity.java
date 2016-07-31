package com.cky.learnandroiddetails;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class TestScrollerActivity extends AppCompatActivity {

    private Button mBtnScrollTo;

    private Button mBtnScrollBy;

    private LinearLayout mRootLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_scroller);

        mBtnScrollTo = (Button)findViewById(R.id.btnScrollTo);
        mBtnScrollBy = (Button)findViewById(R.id.btnScrollBy);

        mRootLayout = (LinearLayout) findViewById(R.id.root_layout);

        mBtnScrollTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //移动的都是View中的内容 也就是指 线性布局中的按钮
                //相对于View的初始位置移动
                mRootLayout.scrollTo(-60, -100);
                //mBtnScrollTo.scrollTo(-60, -100);
            }
        });

        mBtnScrollBy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //相对于View的当前位置移动
                mRootLayout.scrollBy(-60, -100);
                //mBtnScrollBy.scrollBy(-60, -100);
            }
        });
    }
}
