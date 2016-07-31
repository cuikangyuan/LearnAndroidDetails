package com.cky.learnandroiddetails;

import android.content.Context;
import android.support.v4.view.ViewConfigurationCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by cuikangyuan on 16/7/30.
 */
public class ScrollerLayout extends ViewGroup {

    private Scroller mScroller;//用于完成滚动操作的示例

    private int mTouchSlop;//判定拖动的最小移动像素数

    private float mXDown;//按下时的屏幕坐标

    private float mXMove;//当前所处的屏幕坐标

    private float mXLastMove;//上次触发Move事件时的屏幕坐标

    private int leftBorder; //界面可滚动的左边界

    private int rightBorder; //界面可滚动的右边界

    public ScrollerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        //1. 创建Scroller实例
        mScroller = new Scroller(context);
        ViewConfiguration configuration = ViewConfiguration.get(context);
        //获取 TouchSlop值
        mTouchSlop = ViewConfigurationCompat.getScaledPagingTouchSlop(configuration);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            //测量scrollerLayout中每一个 子控件的大小
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed) {
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childView = getChildAt(i);
                //将布局中每一个 子控件进行水平方向的布局
                childView.layout(
                        i * childView.getMeasuredWidth(),
                        0,
                        (i+1) * childView.getMeasuredWidth(),
                        childView.getMeasuredHeight());
                //初始化左右边界
                leftBorder = getChildAt(0).getLeft();
                rightBorder = getChildAt(childCount - 1).getRight();
            }
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mXDown = ev.getRawX();
                mXLastMove = mXDown;
                break;
            case MotionEvent.ACTION_MOVE:
                mXMove = ev.getRawX();
                float diff = Math.abs(mXMove - mXDown);
                mXLastMove = mXMove;
                //当手指拖动值大于TouchSlop时 此时认为应该进行 滚动 拦截事件
                if (diff > mTouchSlop) {
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
                mXMove = event.getRawX();
                int scrolledX = (int) (mXLastMove - mXMove);
                if (getScrollX() + scrolledX < leftBorder) {
                    scrollTo(leftBorder, 0);
                    return true;
                } else if(getScrollX() + getWidth() + scrolledX > rightBorder) {
                    scrollTo(rightBorder - getWidth(), 0);
                    return true;
                }
                scrollBy(scrolledX, 0);
                mXLastMove = mXMove;
                break;
            case MotionEvent.ACTION_UP:
                //手指抬起时 根据当前滚动值判定应该滚到哪个子控件的界面
                int targetIndex = (getScrollX() + getWidth() / 2) / getWidth();
                int dx = targetIndex * getWidth() - getScrollX();
                //2.调用以下方法 初始化滚动数据并刷新界面
                mScroller.startScroll(getScrollX(), 0, dx, 0);//该方法只是对参数的一些保存 并不产生滑动动作
                invalidate();//重绘
                break;
        }
        return super.onTouchEvent(event);
    }

    //真正实现滑动的地方
    @Override
    public void computeScroll() {
        //3.完成平滑滑动的逻辑
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }
}
