package com.cky.learnandroiddetails.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.cky.learnandroiddetails.Camera.DisplayUtil;
import com.cky.learnandroiddetails.UnitTestExample.Util;

import java.text.DecimalFormat;

/**
 * Created by cuikangyuan on 2017/6/27.
 */

public class ProgressBarWithTip extends View {

    private Context mContext;

    private int viewHeight;
    private int mWidth;
    private int mHeight;

    private Paint mBgPaint;
    private Paint mProgressBarPaint;
    private Paint mTipPaint;
    private Paint mTextPaint;

    private int tipHeight;
    private int tipWidth;

    private int triangleBorder;
    private int triangleHeight;

    private int progressBarMarginTop = 15;

    private float mProgress;

    private float currentProgress;

    private ValueAnimator progressAnimator;
    private int duration = 5000;
    private float moveDis;//tip移动的距离

    private RectF mRectF = new RectF();

    private String textString = "0";

    private int roundRectRadius;

    private int tipPaintWidth;
    private int progressPaintWidth;
    private int textPaintWidth;

    private RectF textRect = new RectF();

    public ProgressBarWithTip(Context context) {
        this(context, null);
    }

    public ProgressBarWithTip(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressBarWithTip(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();
    }

    private void init() {

        progressPaintWidth = DisplayUtil.dip2px(mContext, 8);
        tipPaintWidth = DisplayUtil.dip2px(mContext, 10);
        textPaintWidth = DisplayUtil.dip2px(mContext, 12);

        tipHeight = DisplayUtil.dip2px(mContext, 15);
        tipWidth = DisplayUtil.dip2px(mContext, 30);

        triangleBorder = DisplayUtil.dip2px(mContext, 10);
        triangleHeight = (int) (triangleBorder * Math.sin(Math.PI / 3));

        progressBarMarginTop = DisplayUtil.dip2px(mContext, 3);

        tipWidth = DisplayUtil.dip2px(mContext, 30);

        //view真实高度
        viewHeight = tipHeight + tipPaintWidth + triangleHeight + progressBarMarginTop + progressPaintWidth;

        mBgPaint = new Paint();
        mBgPaint.setAntiAlias(true);
        mBgPaint.setStyle(Paint.Style.STROKE);
        mBgPaint.setColor(Color.GRAY);
        mBgPaint.setStrokeWidth(progressPaintWidth);

        mProgressBarPaint = new Paint();
        mProgressBarPaint.setAntiAlias(true);
        mProgressBarPaint.setStyle(Paint.Style.STROKE);
        mProgressBarPaint.setColor(Color.MAGENTA);
        mProgressBarPaint.setStrokeWidth(progressPaintWidth);

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(Color.WHITE);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setTextSize(textPaintWidth);

        mTipPaint = new Paint();
        mTipPaint.setAntiAlias(true);
        mTipPaint.setStyle(Paint.Style.FILL);
        mTipPaint.setColor(Color.MAGENTA);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(
                measureWidth(widthMode, width),
                measureHeight(heightMode, height)
                );
    }

    private int measureWidth(int mode, int width) {
        switch (mode) {
            case MeasureSpec.UNSPECIFIED:
            case MeasureSpec.AT_MOST:
                break;
            case MeasureSpec.EXACTLY:
                mWidth = width;
                break;
        }

        return mWidth;
    }

    private int measureHeight(int mode, int height) {
        switch (mode) {
            case MeasureSpec.UNSPECIFIED:
            case MeasureSpec.AT_MOST:
                mHeight = viewHeight;
                break;
            case MeasureSpec.EXACTLY:
                mHeight = height;
                break;
        }

        return mHeight;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(0, 0);
        //背景
        canvas.drawLine(
                getPaddingLeft(),
                tipHeight + progressBarMarginTop + triangleHeight,
                getWidth(),
                tipHeight + progressBarMarginTop + triangleHeight,
                mBgPaint
        );

        //进度条
        canvas.drawLine(
                getPaddingLeft(),
                tipHeight + progressBarMarginTop + triangleHeight,
                currentProgress,
                tipHeight + progressBarMarginTop + triangleHeight,
                mProgressBarPaint
        );

        //画移动的标识
        drawTip(canvas);
        drawText(canvas, textString);
    }

    private void drawText(Canvas canvas, String str) {
        textRect.left = (int) moveDis;
        textRect.top = 0;
        textRect.right = (int) (tipWidth + moveDis);
        textRect.bottom = tipHeight;

        Paint.FontMetricsInt fontMetricsInt = mTextPaint.getFontMetricsInt();
        int baseline = (int) ((textRect.bottom + textRect.top - fontMetricsInt.bottom - fontMetricsInt.top) / 2);

        canvas.drawText(textString + "%", textRect.centerX(), baseline, mTextPaint);
    }

    private void drawTip(Canvas canvas) {
        drawRoundRect(canvas);
        drawTriangle(canvas);
    }

    private void drawRoundRect(Canvas canvas) {
        mRectF.set(moveDis, 0, moveDis + tipWidth, tipHeight);
        canvas.drawRoundRect(mRectF, 10, 10, mTipPaint);
    }

    private void drawTriangle(Canvas canvas) {
        Path path = new Path();
        path.moveTo(tipWidth / 2 - triangleBorder / 2 + moveDis, tipHeight);
        path.lineTo(tipWidth / 2 + moveDis, tipHeight + triangleHeight);
        path.lineTo(tipWidth / 2 + triangleBorder / 2 + moveDis, tipHeight);

        canvas.drawPath(path, mTipPaint);
    }

    public void setProgress(float progress) {
        mProgress = progress;
        initAnimation();
    }

    private void initAnimation() {
        progressAnimator = ValueAnimator.ofFloat(0, mProgress);
        progressAnimator.setInterpolator(new LinearInterpolator());
        progressAnimator.setStartDelay(2000);
        progressAnimator.setDuration(duration);
        progressAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                float value = (float) animation.getAnimatedValue();
                textString = formatNum(format2Int(value));
                currentProgress = value * mWidth / 100;

                if (currentProgress >= (tipWidth / 2)
                        && currentProgress <=  (mWidth - tipWidth / 2)) {
                    moveDis = currentProgress - tipWidth / 2;
                }

                invalidate();
            }
        });

        progressAnimator.start();
    }

    /**
     * 格式化数字(保留两位小数)
     *
     * @param money
     * @return
     */
    public static String formatNumTwo(double money) {
        DecimalFormat format = new DecimalFormat("0.00");
        return format.format(money);
    }

    /**
     * 格式化数字(保留一位小数)
     *
     * @param money
     * @return
     */
    public static String formatNum(int money) {
        DecimalFormat format = new DecimalFormat("0");
        return format.format(money);
    }

    public static int format2Int(double i) {
        return (int) i;
    }
}
