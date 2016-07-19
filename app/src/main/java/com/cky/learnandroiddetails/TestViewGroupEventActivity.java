package com.cky.learnandroiddetails;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class TestViewGroupEventActivity extends AppCompatActivity {

    private static final String TAG = TestDispatchEventActivity.class.getSimpleName();

    /*
    *当点击某个控件 首先会去调用该控件所在布局的dispatchTouchEvent方法
    *然后在布局的dispatchTouchEvent的方法中找到被点击的相应控件
    * 再去调用该控件的dispatchTouchEvent方法
    *
    * */
    MyLayout myLayout;
    Button mButton1;
    Button mButton2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_view_group_event);

        myLayout = (MyLayout) findViewById(R.id.myLayout);
        mButton1 = (Button) findViewById(R.id.button1);
        mButton2 = (Button) findViewById(R.id.button2);

        myLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                Log.d(TAG, "myLayout on touch");
                return false;
            }
        });

        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "button1 on click");
            }
        });

        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "button2 on click");
            }
        });
    }
}
