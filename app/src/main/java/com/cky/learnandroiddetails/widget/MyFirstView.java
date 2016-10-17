package com.cky.learnandroiddetails.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.cky.learnandroiddetails.R;

/**
 * 作者：cky
 * 时间：2016/10/17 17:03
 * 描述：
 */

public class MyFirstView extends View {

    private int textColor;

    private int textSize;

    private String textContent;

    private Paint mPaint;

    private Rect mRect;

    private int viewWidth;

    private int viewHeight;

    public MyFirstView(Context context) {
        super(context);
    }

    public MyFirstView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyFirstView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray array = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.MyFirstView,
                defStyleAttr,
                0);

        textColor = array.getColor(R.styleable.MyFirstView_textColor, Color.MAGENTA);

        textSize = array.getDimensionPixelSize(R.styleable.MyFirstView_textSize,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                        20,
                        getResources().getDisplayMetrics()));

        textContent = array.getString(R.styleable.MyFirstView_textContent);

        array.recycle();

        mPaint = new Paint();
        mPaint.setTextSize(textSize);
        mPaint.setAntiAlias(true);

        mRect = new Rect();
        mPaint.getTextBounds(textContent, 0, textContent.length(), mRect);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(myMeasure(widthMeasureSpec, 0), myMeasure(heightMeasureSpec, 1));
    }

    private int myMeasure(int measureSpec, int type) {
        int result = 0;

        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            if (type == 0) {
                result = getPaddingLeft() + mRect.width() + getPaddingRight();
                viewWidth = result;
            } else {
                result = getPaddingTop() + mRect.width() + getPaddingBottom();
                viewHeight = result;
            }
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.YELLOW);
        canvas.drawCircle(viewWidth/2, viewHeight/2, viewWidth/2, mPaint);
        mPaint.setColor(textColor);
        canvas.drawText(textContent,
                viewWidth/2 - mRect.width()/2,
                viewHeight/2 + mRect.height()/2,
                mPaint);
    }
}
