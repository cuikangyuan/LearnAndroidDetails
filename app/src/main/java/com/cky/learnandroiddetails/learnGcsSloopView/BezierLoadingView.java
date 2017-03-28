package com.cky.learnandroiddetails.learnGcsSloopView;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.cky.learnandroiddetails.R;

/**
 * Created by cuikangyuan on 2017/3/28.
 */

public class BezierLoadingView extends View {

    private static final int DEFAULT_RADIUS = 80;
    private static final int DEFAULT_DURATION = 1500;
    private static final int DEFAULT_COLOR = 0xff00dddd;

    private float mRadius;
    private float mFloatCircleRadius;
    private int mLoadingColor;
    private int mDuration;
    private int[] mCircleX;
    private int mCircleY;
    private float mFloatCircleX;
    private int mMinDistance;//两圆相距多少时开始贝塞尔曲线

    private Paint mPaint;
    private Path mPath;


    public BezierLoadingView(Context context) {
        this(context, null);
    }

    public BezierLoadingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BezierLoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mPaint = new Paint();
        mPath = new Path();
        mCircleX = new int[3];
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BezierLoadingView);
        mLoadingColor = typedArray.getColor(R.styleable.BezierLoadingView_loadingcolor, DEFAULT_COLOR);
        mRadius = typedArray.getDimension(R.styleable.BezierLoadingView_loadingradius, DEFAULT_RADIUS);
        mDuration = typedArray.getInt(R.styleable.BezierLoadingView_loadingduration, DEFAULT_DURATION);

        mPaint.setColor(mLoadingColor);
        mPaint.setAntiAlias(true);

        mFloatCircleRadius = mRadius * 0.9f;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int mWidth;
        int mHeight;

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY) {
            mWidth = widthSize;
        } else {
            mWidth = getPaddingLeft() + 480 + getPaddingRight();
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = heightSize;
        } else {
            mHeight = getPaddingTop() + 100 + getPaddingBottom();
        }

        setMeasuredDimension(mWidth, mHeight);

        //x方向三个圆心
        int radiusInterval = mWidth / 4;
        for (int i = 0; i < 3; i++) {
            mCircleX[i] = radiusInterval * (i + 1);
        }

        mCircleY = mHeight / 2;

        if (mRadius >= radiusInterval / 4) {
            mRadius = radiusInterval / 4;
            mFloatCircleRadius = mRadius * 0.9f;
        }

        mMinDistance = radiusInterval;

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(mRadius, mWidth - mRadius);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setDuration(mDuration);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mFloatCircleX = (float) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        valueAnimator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (int i = 0; i < 3; i++) {
            canvas.drawCircle(mCircleX[i], mCircleY, mRadius, mPaint);
        }

        canvas.drawCircle(mFloatCircleX, mCircleY, mFloatCircleRadius, mPaint);

        drawBezierLine(canvas);
    }

    private void drawBezierLine(Canvas canvas) {
        float minDis = mMinDistance;
        int mLocationIndex = 0;
        for (int i = 0; i < 3; i++) {
            float dis = Math.abs(mFloatCircleX - mCircleX[i]);
            if (dis < minDis) {
                minDis = dis;
                mLocationIndex = i;
            }
        }

        if (minDis < mMinDistance) {
            float middleX = (mCircleX[mLocationIndex] + mFloatCircleX) / 2;

            mPath.moveTo(mCircleX[mLocationIndex], mCircleY + mRadius);
            mPath.quadTo(middleX, mCircleY, mFloatCircleX, mCircleY + mFloatCircleRadius);

            mPath.lineTo(mFloatCircleX, mCircleY - mFloatCircleRadius);

            mPath.quadTo(middleX, mCircleY, mCircleX[mLocationIndex], mCircleY - mRadius);

            mPath.lineTo(mCircleX[mLocationIndex], mCircleY + mRadius);

            mPath.close();

            canvas.drawPath(mPath, mPaint);
            mPath.reset();

            float f = 1 + (mMinDistance - minDis * 2) / mMinDistance * 0.2f;
            canvas.drawCircle(mCircleX[mLocationIndex], mCircleY, mRadius * f, mPaint);
        }

    }
}
