package com.cky.learnandroiddetails.learnGcsSloopView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by cuikangyuan on 2017/2/4.
 */

public class LearnGcsSloopView extends View {

    private Paint mPaint = new Paint();

    private void initPaint() {
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5f);
    }

    public LearnGcsSloopView(Context context) {
        this(context, null);
    }

    public LearnGcsSloopView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LearnGcsSloopView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initPaint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //canvas.drawColor(Color.CYAN);
        //绘制点
        /*
        canvas.drawPoint(200, 200, mPaint);
        canvas.drawPoints(
                new float[]{
                        500, 500,
                        500, 600,
                        500, 700
                },
                mPaint);
        */

        //绘制线
        /*
        canvas.drawLine(200, 200, 300, 300, mPaint);
        canvas.drawLines(new float[] {500, 500, 600, 500, 500, 600, 600, 600}, mPaint);
        */

        //绘制矩形
        /*
        canvas.drawRect(100, 100, 500, 500, mPaint);
        Rect rect = new Rect(100, 100, 300, 300);
        canvas.drawRect(rect, mPaint);
        RectF rectF = new RectF(100, 100, 500, 500);
        canvas.drawRect(rectF, mPaint);
        */

        //绘制圆角矩形
        //RectF rectF = new RectF(100, 100, 500, 500);
        //canvas.drawRoundRect(rectF, 30, 30, mPaint);
        //API 21
        //canvas.drawRoundRect(100, 100, 500, 500, 30, 30, mPaint);

        //绘制椭圆
        //RectF rectF = new RectF(100, 100, 700, 500);
        //canvas.drawOval(rectF, mPaint);
        //API 21
        //canvas.drawOval(100, 100, 700, 500, mPaint);

        //绘制圆
        //canvas.drawCircle(500, 500, 300, mPaint);

        //绘制圆弧(椭圆圆弧) -> 开始角度, 扫过角度, 是否使用中心
        /*
        RectF rectF = new RectF(100, 100, 500, 400);
        mPaint.setColor(Color.GRAY);
        canvas.drawRect(rectF, mPaint);
        mPaint.setColor(Color.MAGENTA);
        canvas.drawArc(rectF, 0, 90, true, mPaint);
        */

        //绘制圆弧(正圆圆弧)
        /*
        RectF rectF = new RectF(100, 100, 500, 500);
        mPaint.setColor(Color.GRAY);
        canvas.drawRect(rectF, mPaint);
        mPaint.setColor(Color.MAGENTA);
        canvas.drawArc(rectF, 0, 90, false, mPaint);
        */
        /*
        mPaint.setColor(Color.MAGENTA);
        mPaint.setStrokeWidth(20);

        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(300, 200, 100, mPaint);

        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(300, 500, 100, mPaint);

        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(300, 800, 100, mPaint);
        */
        //画布位移
        /*
        mPaint.setColor(Color.BLACK);
        canvas.translate(200, 200);
        canvas.drawCircle(0, 0, 100, mPaint);

        mPaint.setColor(Color.BLUE);
        canvas.translate(200, 200);
        canvas.drawCircle(0, 0, 100, mPaint);
        */
        //画布缩放 缩放中心在坐标原点
        /*
        canvas.translate(getWidth() / 2, getHeight() / 2);

        RectF rectF = new RectF(0, -400, 400, 0);

        mPaint.setColor(Color.BLACK);
        canvas.drawRect(rectF, mPaint);

        canvas.scale(0.5f, 0.5f);

        mPaint.setColor(Color.BLUE);
        canvas.drawRect(rectF, mPaint);
        */

        //画布缩放 并修改缩放中心
        /*
        canvas.translate(getWidth() / 2, getHeight() / 2);

        RectF rectF = new RectF(0, -400, 400, 0);

        mPaint.setColor(Color.BLACK);
        canvas.drawRect(rectF, mPaint);

        //缩放中心向右移动200
        canvas.scale(0.5f, 0.5f, 200, 0);

        mPaint.setColor(Color.BLUE);
        canvas.drawRect(rectF, mPaint);
        */

        //画布缩放 并 翻折 缩放轴 就是中心轴 此种情况下 就是 x轴 和 y轴
        /*
        canvas.translate(getWidth() / 2, getHeight() / 2);

        RectF rectF = new RectF(0, -400, 400, 0);

        mPaint.setColor(Color.BLACK);
        canvas.drawRect(rectF, mPaint);

        //缩放中心向右移动200
        canvas.scale(-0.5f, -0.5f);

        mPaint.setColor(Color.BLUE);
        canvas.drawRect(rectF, mPaint);
        */

        //画布缩放 并 翻折 缩放轴 就是中心轴 此种情况下(缩放中心向右移动200)
        /*
        canvas.translate(getWidth() / 2, getHeight() / 2);

        RectF rectF = new RectF(0, -400, 400, 0);

        mPaint.setColor(Color.BLACK);
        canvas.drawRect(rectF, mPaint);

        //缩放中心向右移动200
        canvas.scale(-0.5f, -0.5f, 200, 0);

        mPaint.setColor(Color.BLUE);
        canvas.drawRect(rectF, mPaint);
        */

        //缩放的叠加
        /*
        canvas.translate(getWidth() / 2, getHeight() / 2);

        RectF rectF = new RectF(-400, -400, 400, 400);

        for (int i = 0; i <= 20; i++) {
            canvas.scale(0.9f, 0.9f);
            canvas.drawRect(rectF, mPaint);
        }
        */
    }
}
