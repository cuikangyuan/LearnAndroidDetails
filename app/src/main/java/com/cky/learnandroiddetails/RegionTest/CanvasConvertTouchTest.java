package com.cky.learnandroiddetails.RegionTest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;


import com.cky.learnandroiddetails.CustomViewBase.CustomView;
import com.gcssloop.view.utils.CanvasAidUtils;

/**
 * Created by cuikangyuan on 2017/5/4.
 */

public class CanvasConvertTouchTest extends CustomView {

    float mX = -1;
    float mY = -1;

    public CanvasConvertTouchTest(Context context) {
        super(context);
    }

    public CanvasConvertTouchTest(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvasConvertTouchTest(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                mX = event.getX();
                mY = event.getY();
                invalidate();
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                mX = mY = -1;
                invalidate();
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float x = mX;
        float y = mY;

        //触摸的坐标系 灰色
        drawTouchCoordinateSpace(canvas);

        //画布转移
        canvas.translate(mWidth/2, mHeight/2);

        //平移后的坐标系 红色
        drawTranslateCoordinateSpace(canvas);

        if (x == -1 && y == -1){
            return;
        }

        canvas.drawCircle(x, y, 40, mDefaultPaint);
    }

    private void drawTouchCoordinateSpace(Canvas canvas) {
        canvas.save();
        canvas.translate(10, 10);
        CanvasAidUtils.set2DAxisLength(1000, 0, 1400, 0);
        CanvasAidUtils.setLineColor(Color.GRAY);
        CanvasAidUtils.draw2DCoordinateSpace(canvas);
        canvas.restore();
    }


    private void drawTranslateCoordinateSpace(Canvas canvas) {
        CanvasAidUtils.set2DAxisLength(500, 500, 700, 700);
        CanvasAidUtils.setLineColor(Color.RED);
        CanvasAidUtils.draw2DCoordinateSpace(canvas);
    }

}
