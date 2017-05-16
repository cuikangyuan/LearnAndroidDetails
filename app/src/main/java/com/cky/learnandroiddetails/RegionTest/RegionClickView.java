package com.cky.learnandroiddetails.RegionTest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by cuikangyuan on 2017/5/3.
 */

public class RegionClickView extends View {

    Region circleRegion;
    Path circlePath;

    protected Context mContext;

    protected int mWidth;

    protected int mHeight;

    protected Paint mDefaultPaint = new Paint();

    protected Paint mDefaultTextPaint = new Paint();

    public RegionClickView(Context context) {
        this(context, null);
    }

    public RegionClickView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RegionClickView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mDefaultPaint.setColor(0xFF4E5268);
        circlePath = new Path();
        circleRegion = new Region();
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        circlePath.addCircle(w/2, h/2, 300, Path.Direction.CW);

        Region region = new Region(-w, -h, w, h);

        circleRegion.setPath(circlePath, region);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
       switch (event.getAction()) {
           case MotionEvent.ACTION_DOWN:
               int x = (int) event.getX();
               int y = (int) event.getY();

               if (circleRegion.contains(x, y)) {
                   Toast.makeText(this.getContext(),"圆被点击",Toast.LENGTH_SHORT).show();
               }

               break;
       }

       return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Path circle = circlePath;
        canvas.drawPath(circle, mDefaultPaint);
    }
}
