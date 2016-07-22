package com.cky.learnandroiddetails;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class TestDispatchEventActivity extends AppCompatActivity {


    Button mBtnTestEvent;

    private static final String TAG = TestDispatchEventActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_dispatch_event);

        mBtnTestEvent = (Button) findViewById(R.id.btnTestEvent);

        mBtnTestEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick executed");
            }
        });

        mBtnTestEvent.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d(TAG, "onTouch executed action " + event.getAction());

                /*
                * 可以理解为 onTouch 返回true时 此事件被onTouch消费掉 不再继续向下传递
                * */
                return true;//此时 onClick 方法将不再执行
                //return false;
            }
        });
    }
}
