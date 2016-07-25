package com.cky.learnandroiddetails;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.widget.LinearLayout;

/**
 * Created by cuikangyuan on 16/7/19.
 */
public class MyLayout extends LinearLayout {

    /*
    *Scroller 弹性滑动对象
    * */


    public MyLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //return super.onInterceptTouchEvent(ev);
        return false;
    }

    /*
    *VelocityTracker 速度追踪
    * */
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        VelocityTracker velocityTracker = VelocityTracker.obtain();
        velocityTracker.addMovement(event);

        velocityTracker.computeCurrentVelocity(1000);// 计算速度 一段时间内手指所划过的像素数 1000ms
        //速度 = (终点位置 - 起点位置) / 时间
        int xVelocity = (int) velocityTracker.getXVelocity();
        int yVelocity = (int) velocityTracker.getYVelocity();

        //重置回收 内存
        velocityTracker.clear();
        velocityTracker.recycle();



        return super.onTouchEvent(event);
    }
}
