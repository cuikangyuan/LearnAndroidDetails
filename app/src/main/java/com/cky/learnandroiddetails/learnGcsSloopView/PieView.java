package com.cky.learnandroiddetails.learnGcsSloopView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by cuikangyuan on 2017/2/4.
 */

public class PieView extends View{

    private int[] mColors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
            0xFFE6B800, 0xFF7CFC00};

    private float mStartAngle = 0;

    private ArrayList<PieData> mData;

    private int mWidth, mHeight;

    private Paint mPaint = new Paint();


    public PieView(Context context) {
        this(context, null);
    }

    public PieView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PieView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mData == null) {
            return;
        }

        float currentStartAngle = mStartAngle;
        canvas.translate(mWidth / 2, mHeight / 2);
        float r = (float) (Math.min(mWidth, mHeight) / 2 * 0.8);
        RectF rectF = new RectF(-r, -r, r, r);

        for (int i = 0; i < mData.size(); i++) {
            PieData pieData = mData.get(i);
            mPaint.setColor(pieData.getmColor());
            canvas.drawArc(rectF, currentStartAngle, pieData.getmAngle(), true, mPaint);
            currentStartAngle += pieData.getmAngle();
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mWidth = w;
        mHeight = h;
    }

    public void setData(ArrayList<PieData> data) {
        this.mData = data;
        initData(this.mData);
        invalidate();
    }

    private void initData(ArrayList<PieData> data){
        if (data == null || data.size() <= 0) {
            return;
        }

        float sumValue = 0;
        for (int i = 0; i < data.size(); i++ ) {
            PieData pieData = data.get(i);

            sumValue += pieData.getmValue();

            int j = i % mColors.length;

            pieData.setmColor(mColors[j]);
        }

        for (int i = 0; i < data.size(); i++ ) {
            PieData pieData = data.get(i);

            float percentage = pieData.getmValue() / sumValue;

            float angle = percentage * 360;

            pieData.setmPercentage(percentage);

            pieData.setmAngle(angle);
        }
    }
}
