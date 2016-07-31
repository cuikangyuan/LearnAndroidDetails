package com.cky.learnandroiddetails;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class TestScrollerActivity extends AppCompatActivity {

    private Button mBtnScrollTo;

    private Button mBtnScrollBy;

    private LinearLayout mRootLayout;

    private static final String TAG = TestScrollerActivity.class.getSimpleName();

    private static final int MESSAGE_SCROLL_TO = 1;
    private static final int FRAME_COUNT = 30;
    private static final int DELAYED_TIME = 500;

    private int mCount = 0;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_SCROLL_TO:
                    mCount++;
                    if (mCount <= FRAME_COUNT) {
                        Log.d(TAG, "mCount -> " + mCount);
                        float fraction = mCount / (float)FRAME_COUNT;
                        int scrollX = (int) fraction * (-60);
                        int scrollY = (int) fraction * (-100);
                        mRootLayout.scrollTo(scrollX, scrollY);
                        Log.d(TAG, "mCount -> " + mCount + " scrollX -> " + scrollX + " scrollY -> " + scrollY);
                        mHandler.sendEmptyMessageDelayed(MESSAGE_SCROLL_TO, DELAYED_TIME);
                    }
                    break;
                default:
                    break;
            }
        }
    };

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
                //mRootLayout.scrollTo(-60, -100);
                //mBtnScrollTo.scrollTo(-60, -100);

                /*
                //值动画
                final ValueAnimator animator = ValueAnimator.ofInt(0, 1).setDuration(2000);
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float fraction = animator.getAnimatedFraction();
                        mRootLayout.scrollTo((int)(-60 * fraction), (int)(-100 * fraction));
                    }
                });
                animator.start();
                */
                //采用延时策略
                mHandler.sendEmptyMessageDelayed(MESSAGE_SCROLL_TO, DELAYED_TIME);
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
