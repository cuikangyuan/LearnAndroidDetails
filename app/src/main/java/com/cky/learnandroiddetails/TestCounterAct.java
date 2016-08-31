package com.cky.learnandroiddetails;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TestCounterAct extends Activity {

    Button mGetCodeBtn;

    TextView mShape;

    private MyCounter mCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_counter);

        mGetCodeBtn = (Button) findViewById(R.id.btn_get_code);

        mGetCodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCounter == null) {
                    mCounter = (MyCounter) new MyCounter(10000, 1000).start();
                }
            }
        });
    }

    public class MyCounter extends CountDownTimer {

        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */

        public MyCounter(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            if (mGetCodeBtn.isEnabled()) {
                mGetCodeBtn.setEnabled(false);
            }
            mGetCodeBtn.setText("重新发送(" + millisUntilFinished / 1000 + ")");
        }

        @Override
        public void onFinish() {
            if (!mGetCodeBtn.isEnabled()) {
                mGetCodeBtn.setEnabled(true);
            }
            mGetCodeBtn.setText("获取验证码");
            mCounter = null;
        }
    }
}
