package com.cky.learnandroiddetails;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TestInterceptActivity extends AppCompatActivity {

    /*
    * 解决滑动冲突
    * 1.外部拦截法
    *   点击事件先经过父容器的拦截处理 如果父容器需要此事件 就拦截 如果不需要 则 不用拦截
    *   需要重写父容器 onInterceptTouchEvent
    *   父容器 ACTION_DOWN事件必须返回 false
    * 2.内部拦截法
    *   父容器不拦截任何事件 所有的事件都传递给子元素 如果子元素需要此事件 就直接消耗掉  否则则交由父容器进行处理
    *   配合requestDisallowInterceptTouchEvent方法使用
    * */

    /*
    * 当viewGroup决定拦截事件后 后续的点击事件将默认交给它处理并且不会再调用onInterceptTouchEvent方法
    * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_intercept);
    }
}
