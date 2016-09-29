package com.cky.learnandroiddetails.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by cuikangyuan on 16/9/29.
 */

public class CanvasView extends ImageView{

    public CanvasView(Context context) {
        super(context);
    }

    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvasView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        interSect(canvas);
    }

    private void clipRect(Canvas canvas) {
        /*
        * 裁剪画布
        * */
        canvas.drawColor(Color.BLUE);
        canvas.clipRect(0, 0, 500, 500);
        canvas.drawColor(Color.RED);
    }

    private void interSect(Canvas canvas) {
        /*
        * 裁剪画布
        * */
        canvas.drawColor(Color.BLUE);

        Rect rect = new Rect(0, 0, 500, 500);

        boolean result = rect.intersect(100, 100, 500, 500);

        canvas.clipRect(rect);
        canvas.drawColor(Color.RED);
    }


}
