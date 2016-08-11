package com.cky.learnandroiddetails.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.cky.learnandroiddetails.R;

/**
 * Created by cuikangyuan on 16/8/11.
 */
public class DashedView extends View{

    private float dashWidth;

    private float dashGap;

    private Paint mPaint;

    public DashedView(Context context) {
        super(context);
    }

    public DashedView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttr(context, attrs);
    }

    public DashedView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(context, attrs);
    }

    private void initAttr(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DashedView);

        dashGap = typedArray.getDimension(R.styleable.DashedView_dashGap, 10);
        dashWidth = typedArray.getDimension(R.styleable.DashedView_dashWidth, 10);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(1);

        PathEffect pathEffect = new DashPathEffect(new float[]{10, 10}, 1);

        mPaint.setPathEffect(pathEffect);

        canvas.drawRect(new Rect(0, 0, this.getWidth(), this.getHeight()), mPaint);


    }
}
