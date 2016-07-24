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
    * */

    /*
    *
    * View 的位置参数
    * top left right bottom
    *
    * 相对于 View的父容器而言的
    *
    * top 左上角纵坐标
    * left 左上角横坐标
    * right 右下角横坐标
    * bottom 右下角纵坐标
    *
    * width = right - left
    * height = bottom - top
    *
    * x = left + translationX(默认0)
    * y = top + translationY(默认0)
    *
    * 在View 的平移过程中 top left表示原始左上角的位置 不发生改变
    *
    * MotionEvent
    * getX/getY 获得相对于当前View左上角的x和y坐标
    * getRawX/getRawY 获得相当于手机屏幕左上角的x和y坐标
    *
    * TouchSlop
    * 系统所能识别的滑动的最小距离
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
