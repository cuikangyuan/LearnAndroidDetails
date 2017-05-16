package com.cky.learnandroiddetails.RegionTest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.cky.learnandroiddetails.CustomViewBase.CustomView;
import com.cky.learnandroiddetails.R;

/**
 * Created by cuikangyuan on 2017/5/16.
 */

public class RemoteControlMenu extends CustomView {

    Path upPath, downPath, leftPath, rightPath, centerPath;
    Region up, down, left, right, center;

    Matrix mMapMatrix = null;

    int CENTER = 0;
    int UP = 1;
    int RIGHT = 2;
    int DOWN = 3;
    int LEFT = 4;

    int touchFlag = -1;
    int currentFlag = -1;

    int mDefaultColor = 0xFF4E5268;
    int mTouchedColor = 0xFFDF9C81;

    MenuListener mListener = null;

    RectF bigCircle;
    RectF smallCircle;

    public RemoteControlMenu(Context context) {
        this(context, null);
    }

    public RemoteControlMenu(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        upPath = new Path();
        downPath = new Path();
        leftPath = new Path();
        rightPath = new Path();
        centerPath = new Path();

        up = new Region();
        down = new Region();
        left = new Region();
        right = new Region();
        center = new Region();

        mDefaultPaint.setColor(mDefaultColor);
        mDefaultPaint.setAntiAlias(true);

        mMapMatrix = new Matrix();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mMapMatrix.reset();

        Region globalRegion = new Region(-w, -h, w, h);

        int minWidth = w > h ? h : w;
        minWidth *= 0.8;

        int br = minWidth / 2;
        bigCircle = new RectF(-br, -br, br, br);

        int sr = minWidth / 4;
        smallCircle = new RectF(-sr, -sr, sr, sr);

        float bigSweepAngle = 84;
        float smallSweepAngle = -80;

        //初始化Path相关
        centerPath.addCircle(0, 0, 0.2f * minWidth, Path.Direction.CCW);
        center.setPath(centerPath, globalRegion);

        rightPath.addArc(bigCircle, -40, bigSweepAngle);
        rightPath.arcTo(smallCircle, 40, smallSweepAngle);
        rightPath.close();
        right.setPath(rightPath, globalRegion);

        downPath.addArc(bigCircle, 50, bigSweepAngle);
        downPath.arcTo(smallCircle, 130, smallSweepAngle);
        downPath.close();
        down.setPath(downPath, globalRegion);

        leftPath.addArc(bigCircle, 140, bigSweepAngle);
        leftPath.arcTo(smallCircle, 220, smallSweepAngle);
        leftPath.close();
        left.setPath(leftPath, globalRegion);

        upPath.addArc(bigCircle, 230, bigSweepAngle);
        upPath.arcTo(smallCircle, 310, smallSweepAngle);
        upPath.close();
        up.setPath(upPath, globalRegion);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(mWidth / 2, mHeight / 2);

        //???
        if (mMapMatrix.isIdentity()) {
            canvas.getMatrix().invert(mMapMatrix);
        }

        //绘制默认状态
        canvas.drawPath(centerPath, mDefaultPaint);
        canvas.drawPath(upPath, mDefaultPaint);
        canvas.drawPath(rightPath, mDefaultPaint);
        canvas.drawPath(downPath, mDefaultPaint);
        canvas.drawPath(leftPath, mDefaultPaint);

        //绘制触摸区域颜色
        mDefaultPaint.setColor(mTouchedColor);
        if (currentFlag == CENTER) {
            canvas.drawPath(centerPath, mDefaultPaint);
        } else if (currentFlag == UP) {
            canvas.drawPath(upPath, mDefaultPaint);
        } else if (currentFlag == RIGHT) {
            canvas.drawPath(rightPath, mDefaultPaint);
        } else if (currentFlag == DOWN) {
            canvas.drawPath(downPath, mDefaultPaint);
        } else if (currentFlag == LEFT) {
            canvas.drawPath(leftPath, mDefaultPaint);
        }

        mDefaultPaint.setColor(Color.RED);
        mDefaultPaint.setStyle(Paint.Style.STROKE);

        canvas.drawRect(bigCircle, mDefaultPaint);

        canvas.drawRect(smallCircle, mDefaultPaint);

        mDefaultPaint.setStyle(Paint.Style.FILL);
        mDefaultPaint.setColor(mDefaultColor);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float[] pts = new float[2];
        pts[0] = event.getRawX();
        pts[1] = event.getRawY();

        mMapMatrix.mapPoints(pts);

        int x = (int) pts[0];
        int y = (int) pts[1];

        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                touchFlag = getTouchedPath(x, y);
                currentFlag = touchFlag;
                break;
            case MotionEvent.ACTION_MOVE:
                currentFlag = getTouchedPath(x, y);
                break;
            case MotionEvent.ACTION_UP:
                currentFlag = getTouchedPath(x, y);
                if (currentFlag == touchFlag
                        && currentFlag != -1
                        && mListener != null) {
                    if (currentFlag == CENTER) {
                        mListener.onCenterClicked();
                    } else if (currentFlag == UP) {
                        mListener.onUpClicked();
                    } else if (currentFlag == RIGHT) {
                        mListener.onRightClicked();
                    } else if (currentFlag == DOWN) {
                        mListener.onDownClicked();
                    } else if (currentFlag == LEFT) {
                        mListener.onLeftClicked();
                    }
                }
                touchFlag = currentFlag = -1;
                break;
            case MotionEvent.ACTION_CANCEL:
                touchFlag = currentFlag = -1;
                break;
        }

        invalidate();
        return true;
    }

    public int getTouchedPath(int x, int y) {
        if (center.contains(x, y)) {
            return CENTER;
        } else if (up.contains(x, y)) {
            return UP;
        } else if (right.contains(x, y)) {
            return RIGHT;
        } else if (left.contains(x, y)) {
            return LEFT;
        } else if (down.contains(x, y)) {
            return DOWN;
        } else {
            return -1;
        }

    }

    public void setListener(MenuListener listener) {
        mListener = listener;
    }

    public interface MenuListener {
        void onCenterClicked();

        void onUpClicked();

        void onRightClicked();

        void onDownClicked();

        void onLeftClicked();
    }
}
