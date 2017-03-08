package com.cky.learnandroiddetails.learnGcsSloopView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by cuikangyuan on 2017/3/8.
 */

public class RadarView extends View {

    private int count = 6;
    private float angle = (float) (Math.PI * 2 / count);
    private float radius;
    private int centerX;
    private int centerY;
    private String[] titles = {"a", "b", "c", "d", "e", "f"};
    private double[] data = {100, 32, 56, 70, 10, 89, 20};
    private float maxValue = 100;
    private Paint mainPaint = new Paint();
    private Paint valuePaint = new Paint();
    private Paint textPaint = new Paint();

    public RadarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public RadarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RadarView(Context context) {
        super(context);
        init();
    }

    private void init() {
        mainPaint.setAntiAlias(true);
        mainPaint.setColor(Color.GRAY);
        mainPaint.setStyle(Paint.Style.STROKE);
        mainPaint.setStrokeWidth(5f);

        valuePaint.setAntiAlias(true);
        valuePaint.setColor(Color.BLUE);
        valuePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        textPaint.setTextSize(20);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(Color.BLACK);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        radius = Math.min(h, w)/2*0.9f;
        centerX = w/2;
        centerY = h/2;
        postInvalidate();
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        //canvas.drawColor(Color.CYAN);

        drawPolygon(canvas);

        drawLines(canvas);

        drawText(canvas);

        drawRegion(canvas);
    }

    /*
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        radius = Math.min(h, w) / 2 * 0.9f;
        centerX = w / 2;
        centerY = h / 2;
        postInvalidate();
        super.onSizeChanged(w, h, oldw, oldh);
    }
    */

    //绘制正多边形
    private void drawPolygon(Canvas canvas) {
        Path path = new Path();
        float r = radius / (count - 1);//多边形之间的距离

        for (int i=1; i<count; i++) {
            float curRadius = r * i;
            path.reset();

            for (int j=0; j<count; j++) {
                if (j == 0) {
                    path.moveTo(centerX + curRadius, centerY);
                } else {
                    float x = (float) (centerX + curRadius * Math.cos(angle * j));
                    float y = (float) (centerY + curRadius * Math.sin(angle * j));
                    path.lineTo(x, y);
                }
            }
            path.close();
            canvas.drawPath(path, mainPaint);
        }
    }

    //绘制中心到末端的直线
    private void drawLines(Canvas canvas) {
        Path path = new Path();
        for (int i=0; i<count; i++) {
            path.reset();
            path.moveTo(centerX, centerY);
            float x = (float) (centerX + radius * Math.cos(angle * i));
            float y = (float) (centerY + radius * Math.sin(angle * i));

            path.lineTo(x, y);
            canvas.drawPath(path, mainPaint);
        }
    }

    //绘制文字
    private void drawText(Canvas canvas) {
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float fontHeight = fontMetrics.descent - fontMetrics.ascent;

        for (int i=0; i<count; i++) {
            float x = (float) (centerX + (radius + fontHeight/2) * Math.cos(angle * i));
            float y = (float) (centerY + (radius + fontHeight/2) * Math.sin(angle * i));

            if (angle*i >=0 && angle*i <= Math.PI / 2) {
                //四象限
                canvas.drawText(titles[i], x, y, textPaint);
            } else if (angle*i >= 3*Math.PI/2 && angle*i <= Math.PI * 2) {
                //三象限
                canvas.drawText(titles[i], x, y, textPaint);
            } else if (angle*i > Math.PI/2 && angle*i <= Math.PI) {
                //二象限
                float dis = textPaint.measureText(titles[i]);
                canvas.drawText(titles[i], x - dis, y, textPaint);
            } else if (angle*i >= Math.PI && angle*i < 3*Math.PI/2) {
                //一象限
                float dis = textPaint.measureText(titles[i]);
                canvas.drawText(titles[i], x - dis, y, textPaint);
            }

        }
    }

    //绘制覆盖区域
    private void drawRegion(Canvas canvas) {
        Path path = new Path();
        valuePaint.setAlpha(255);
        for (int i=0; i<count; i++){
            double percent = data[i] / maxValue;
            float x = (float) (centerX + radius*Math.cos(angle*i)*percent);
            float y = (float) (centerY + radius*Math.sin(angle*i)*percent);

            if (i == 0) {
                path.moveTo(x, centerY);
            } else {
                path.lineTo(x, y);
            }

            canvas.drawCircle(x, y, 10, valuePaint);
        }
        valuePaint.setAlpha(127);
        canvas.drawPath(path, valuePaint);

        //填充区域
        //valuePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        //canvas.drawPath(path, valuePaint);
    }
}
