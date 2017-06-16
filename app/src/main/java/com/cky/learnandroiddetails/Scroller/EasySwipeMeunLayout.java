package com.cky.learnandroiddetails.Scroller;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by cuikangyuan on 2017/6/16.
 */

public class EasySwipeMeunLayout extends ViewGroup{

    private int touchSlop;
    private int contentWidth;
    private int rightMenuWidth;
    private float lastX;
    private float lastY;
    private float firstX;
    private float firstY;
    private Scroller mScroller;

    public EasySwipeMeunLayout(Context context) {
        this(context, null);
    }

    public EasySwipeMeunLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EasySwipeMeunLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        touchSlop = viewConfiguration.getScaledTouchSlop();
        mScroller = new Scroller(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int height = 0;
        contentWidth = 0;

        int childCount = getChildCount();

        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            childAt.setClickable(true);
            if (childAt.getVisibility() != GONE) {
                measureChild(childAt, widthMeasureSpec, heightMeasureSpec);
                height = Math.max(height, childAt.getMeasuredHeight());

                if (i == 0) {
                    //内容view
                    contentWidth = childAt.getMeasuredWidth();
                } else {
                    //左滑view
                    rightMenuWidth = childAt.getMeasuredWidth();
                }
            }
        }

        setMeasuredDimension(
                getPaddingLeft() + getPaddingRight() + contentWidth,
                getPaddingTop() + getPaddingBottom() + height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() != GONE) {
                if (i == 0) {
                    childAt.layout(
                            getPaddingLeft(),
                            getPaddingTop(),
                            getPaddingLeft() + childAt.getMeasuredWidth(),
                            getPaddingTop() + childAt.getMeasuredHeight());
                } else {
                    childAt.layout(
                            contentWidth,
                            getPaddingTop(),
                            contentWidth + childAt.getMeasuredWidth(),
                            getPaddingTop() + childAt.getMeasuredHeight()
                            );
                }
            }
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = ev.getRawX();
                firstX = ev.getRawX();

                lastY = ev.getRawY();
                firstY = ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                float distance = lastX - ev.getRawX();
                if (Math.abs(distance) > touchSlop) {
                    //此时判断为已滑动
                    return true;
                }
                break;
        }

        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                float distance = lastX - event.getRawX();
                lastX = event.getRawX();
                lastY = event.getRawY();
                scrollBy((int)distance, 0);
                break;
            case MotionEvent.ACTION_UP:
                if (getScrollX() <= 0 ) {
                    //右边界处理 不让滑出
                    mScroller.startScroll(getScrollX(), 0, -getScrollX(), 0);
                } else if (getScrollX() > 0 && getScrollX() >= rightMenuWidth / 3) {
                    //删除按钮滑出区域大于1／3，滑出删除按钮
                    mScroller.startScroll(
                            getScrollX(), 0,
                            rightMenuWidth - getScrollX(), 0);
                } else {
                    //删除按钮滑出区域小于1／3，滑回原来的位置
                    mScroller.startScroll(getScrollX(), 0, -getScrollX(), 0);
                }
                invalidate();
                break;
        }

        return super.onTouchEvent(event);
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }
}
