package com.cky.learnandroiddetails.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by cuikangyuan on 16/9/11.
 * 优惠卡劵 锯齿效果
 */
public class CouponDisplayView extends RelativeLayout{

    private Paint mPaint;
    /**
     * 圆间距
     */
    private float gap = 8;
    /**
     * 半径
     */
    private float radius = 10;
    /**
     * 圆数量
     */
    private int circleNum;

    private float remain;


    public CouponDisplayView(Context context) {
        super(context);
    }

    public CouponDisplayView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setDither(true);
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (remain==0){
            remain = (int)(w-gap)%(2*radius+gap);
        }
        circleNum = (int) ((w-gap)/(2*radius+gap));
    }


    public CouponDisplayView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i=0;i<circleNum;i++){
            float x = gap+radius+remain/2+((gap+radius*2)*i);
            //canvas.drawCircle(x, 0, radius, mPaint);
            canvas.drawCircle(x, getHeight(), radius, mPaint);
        }

        addDashdedLine(canvas);
    }

    private void addDashdedLine(Canvas canvas) {
        /*
        第一个参数 数组 数组的元素个数应大于等于2
        第一个元素代表 第一个实线的长度
        第二个元素代表 第一个虚线的长度
        如果数组中还有别的元素 则 依次类推
        */
        DashPathEffect pathEffect = new DashPathEffect(new float[] {20, 10}, 1);
        mPaint.reset();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(1);
        mPaint.setColor(Color.WHITE);
        mPaint.setAntiAlias(true);
        mPaint.setPathEffect(pathEffect);
        Path path = new Path();
        path.moveTo(getWidth()/2, getHeight()/2 - 300);
        path.lineTo(getWidth()/2, getHeight()/2 + 300);
        canvas.drawPath(path, mPaint);
    }
}
