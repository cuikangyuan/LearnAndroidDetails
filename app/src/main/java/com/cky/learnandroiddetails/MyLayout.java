package com.cky.learnandroiddetails;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by cuikangyuan on 16/7/19.
 */
public class MyLayout extends LinearLayout {

    public MyLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //return super.onInterceptTouchEvent(ev);
        return false;
    }
}
