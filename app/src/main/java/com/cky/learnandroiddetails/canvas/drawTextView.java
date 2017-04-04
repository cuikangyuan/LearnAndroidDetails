package com.cky.learnandroiddetails.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by cuikangyuan on 2017/4/4.
 */

public class drawTextView extends View {
    public drawTextView(Context context) {
        this(context, null);
    }

    public drawTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public drawTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        /*
        RectF targetRect = new RectF(50, 50, 1000, 200);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(3);
        paint.setTextSize(80);
        //：ijkJQKA:1234
        String testString = "测试";
        Rect textRect = new Rect();
        paint.getTextBounds(testString, 0, testString.length(), textRect);
        int badgeHeight = textRect.height();
        int badgeWidth;

        if (testString.length() == 1 || testString.length() == 0) {
            badgeWidth = badgeHeight;
        } else {
            badgeWidth = textRect.width();
        }
        paint.setColor(Color.CYAN);
        canvas.drawRoundRect(targetRect, badgeHeight / 2, badgeHeight / 2, paint);
        paint.setColor(Color.RED);
        canvas.drawText(testString, targetRect.left, targetRect.bottom, paint);
        */
        /*
        红线是baseline，最上面的灰线是FontMetrics.top，最下面的绿线是FontMetrics.bottom。（绿色的bottom和蓝色的descent非常接近）

从图中可知，字符本身是在灰线和绿线之间居中的，知道这个就好办了。网上说的使用paint.getTextBounds的方法都不靠谱，可以看到对一个“测”字和6个字得到的bounds是不同的，图中的矩形能很好地表示这个函数得到的是字符的边界，而不是字体的边界。

FontMetrics.top的数值是个负数，其绝对值就是字体绘制边界到baseline的距离。
所以如果是把文字画在 FontMetrics高度的矩形中， drawText就应该传入 -FontMetrics.top。
要画在targetRect的居中位置，baseline的计算公式就是：
targetRect.centerY() - (FontMetrics.bottom - FontMetrics.top) / 2 - FontMetrics.top
优化后即：

(targetRect.bottom + targetRect.top - fontMetrics.bottom - fontMetrics.top) / 2

         */
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(3);
        paint.setTextSize(80);
        Paint.FontMetricsInt fmi = paint.getFontMetricsInt();
        String testString = "测试：ijkJQKA:1234";
        Rect bounds1 = new Rect();
        paint.getTextBounds("测", 0, 1, bounds1);
        Rect bounds2 = new Rect();
        paint.getTextBounds("测试：ijk", 0, 6, bounds2);
        // 随意设一个位置作为baseline
        int x = 200;
        int y = 400;
        // 把testString画在baseline上
        canvas.drawText(testString, x, y, paint);
        // bounds1
        paint.setStyle(Paint.Style.STROKE);  // 画空心矩形
        canvas.save();
        canvas.translate(x, y);  // 注意这里有translate。getTextBounds得到的矩形也是以baseline为基准的
        paint.setColor(Color.GREEN);
        canvas.drawRect(bounds1, paint);
        canvas.restore();
        // bounds2
        canvas.save();
        paint.setColor(Color.MAGENTA);
        canvas.translate(x, y);
        canvas.drawRect(bounds2, paint);
        canvas.restore();
        // baseline
        paint.setColor(Color.RED);
        canvas.drawLine(x, y, 1024, y, paint);
        // ascent
        paint.setColor(Color.YELLOW);
        canvas.drawLine(x, y+fmi.ascent, 1024, y+fmi.ascent, paint);
        // descent
        paint.setColor(Color.BLUE);
        canvas.drawLine(x, y+fmi.descent, 1024, y+fmi.descent, paint);
        // top
        paint.setColor(Color.DKGRAY);
        canvas.drawLine(x, y+fmi.top, 1024, y+fmi.top, paint);
        // bottom
        paint.setColor(Color.GREEN);
        canvas.drawLine(x, y+fmi.bottom, 1024, y+fmi.bottom, paint);
/*
        Rect targetRect = new Rect(50, 50, 1000, 200);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(3);
        paint.setTextSize(80);
        String testString = "测试：ijkJQKA:1234";
        paint.setColor(Color.CYAN);
        canvas.drawRect(targetRect, paint);
        paint.setColor(Color.RED);
        Paint.FontMetricsInt fontMetrics = paint.getFontMetricsInt();
        // 转载请注明出处：http://blog.csdn.net/hursing
        int baseline = (targetRect.bottom + targetRect.top - fontMetrics.bottom - fontMetrics.top) / 2;
        // 下面这行是实现水平居中，drawText对应改为传入targetRect.centerX()
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(testString, targetRect.centerX(), baseline, paint);
 */
    }
}
