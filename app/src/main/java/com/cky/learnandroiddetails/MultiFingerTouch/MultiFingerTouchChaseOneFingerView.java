package com.cky.learnandroiddetails.MultiFingerTouch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.cky.learnandroiddetails.CustomViewBase.CustomView;

/**
 * Created by cuikangyuan on 2017/5/17.
 */

public class MultiFingerTouchChaseOneFingerView extends CustomView {

    boolean haveSecondPoint = false;

    PointF mPointF = new PointF(0, 0);

    public MultiFingerTouchChaseOneFingerView(Context context) {
        this(context, null);
    }

    public MultiFingerTouchChaseOneFingerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mDefaultPaint.setAntiAlias(true);
        mDefaultPaint.setTextAlign(Paint.Align.CENTER);
        mDefaultPaint.setTextSize(30);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int index = event.getActionIndex();
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                //判断第二个手指按下
                if (event.getPointerId(index) == 1) {
                    haveSecondPoint = true;
                    mPointF.set(event.getX(), event.getY());
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                //判断第二个手指抬起
                if (event.getPointerId(index) == 1) {
                    haveSecondPoint = false;
                    mPointF.set(0, 0);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (haveSecondPoint) {
                    //通过 pointerId 获取 pointerIndex
                    int pointerIndex = event.findPointerIndex(1);
                    //通过pointerIndx 取出对应的坐标
                    mPointF.set(event.getX(pointerIndex), event.getY(pointerIndex));
                }
                break;
        }

        invalidate();

        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        canvas.translate(mWidth / 2, mHeight / 2);
        canvas.drawText("第二个手指位置", 0, 0, mDefaultPaint);
        canvas.restore();

        if (haveSecondPoint) {
            canvas.drawCircle(mPointF.x, mPointF.y, 50, mDefaultPaint);
        }
    }
}
