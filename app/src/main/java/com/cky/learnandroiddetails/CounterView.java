package com.cky.learnandroiddetails;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by cuikangyuan on 16/7/5.
 * 自绘控件
 */
public class CounterView extends View implements View.OnClickListener{

    private Paint mPaint;

    private Rect mBounds;

    private int mCount;

    public CounterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBounds = new Rect();
        setOnClickListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);
        mPaint.setColor(Color.YELLOW);
        mPaint.setTextSize(30);
        String text = String.valueOf(mCount);
        mPaint.getTextBounds(text, 0, text.length(), mBounds);
        float textWidth = mBounds.width();
        float textHeight = mBounds.height();
        canvas.drawText(text, getWidth()/2 - textWidth/2, getHeight()/2+textHeight/2, mPaint);
    }

    @Override
    public void onClick(View v) {
        mCount++;
        invalidate();
    }

    /*
    * 1.
    * scrollBy 实际上调用了 scrollTo, 以实现相对滑动
    * scrollTo实现了基于所传参数的绝对滑动
    *
    * 在滑动过程中
    * mScrollX = View左边缘View内容左边缘在水平方向的距离
    * mScrollY = View上边缘和View内容上边缘在竖直方向的距离
    *
    * scrollBy scrollTo只能改变View内容的位置而不能改变View在布局中的位置
    *
    * View左边缘在View内容左边缘右边时 mScrollX 正值
    *
    * View上边缘在View内容上边缘下边时 mScrollY 正值
    *
    * 2.
    * View动画是对View影像的操作
    * fillAfter 动画结束后 保留动画结束的状态 无法改变View的参数
    * 但属性动画可以解决这个问题 但是 属性动画涉及一个兼容性的问题
    *
    * 3.
    * 改变布局参数
    * LayoutParams
    * */
}
