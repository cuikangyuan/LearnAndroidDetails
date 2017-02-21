package com.cky.learnandroiddetails.learnGcsSloopView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.PictureDrawable;
import android.util.AttributeSet;
import android.view.View;

import com.cky.learnandroiddetails.R;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by cuikangyuan on 2017/2/4.
 */

public class LearnGcsSloopView extends View {

    private Paint mPaint = new Paint();

    private Picture mPicture = new Picture();

    private Context mContext;

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

        mContext = context;

        initPaint();

        recording();
    }

    private void recording() {
        Canvas canvas = mPicture.beginRecording(500, 500);

        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);

        canvas.translate(250, 250);
        canvas.drawCircle(0, 0, 100, paint);

        mPicture.endRecording();
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

        //Rotate操作 默认旋转中心为原点
        /*
        canvas.translate(getWidth() / 2, getHeight() / 2);
        RectF rectF = new RectF(0, -400, 400, 0);

        mPaint.setColor(Color.BLACK);
        canvas.drawRect(rectF, mPaint);

        canvas.rotate(180);

        mPaint.setColor(Color.BLUE);
        canvas.drawRect(rectF, mPaint);
        */

        //Rotate操作 默认旋转中心向右偏移200 旋转角度也是可以叠加的
        /*
        canvas.translate(getWidth() / 2, getHeight() / 2);
        RectF rectF = new RectF(0, -400, 400, 0);

        mPaint.setColor(Color.BLACK);
        canvas.drawRect(rectF, mPaint);

        canvas.rotate(180, 200, 0);

        mPaint.setColor(Color.BLUE);
        canvas.drawRect(rectF, mPaint);
        */
        /*
        canvas.translate(getWidth() / 2, getHeight() / 2);
        mPaint.setColor(Color.BLACK);
        canvas.drawCircle(0, 0, 400, mPaint);
        canvas.drawCircle(0, 0, 380, mPaint);

        for (int i = 10; i <= 360; i++) {
            canvas.drawLine(0, 380, 0, 400, mPaint);
            canvas.rotate(10);
        }
        */

        //错切
        //X = x + sx * y
        //Y = y + sy * x
        //(0, -200) -> (-200, -200)
        //(200, 0) -> (200, 0)
        /*
        canvas.translate(getWidth() / 2, getHeight() / 2);
        RectF rectF = new RectF(0, -200, 200,0);
        mPaint.setColor(Color.BLACK);
        canvas.drawRect(rectF, mPaint);

        canvas.skew(1, 0);
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(rectF, mPaint);
        */

        //Picture
        //mPicture.draw(canvas);//1.在一些低版本的系统上绘制后可能会影响Canvas的状态

        //2.绘制后不会影响Canvas的状态 如果绘制的内容比选区大，会进行相应的缩放
        //canvas.drawPicture(mPicture, new RectF(0, 0, 100, mPicture.getHeight()));

        //3.包装成PictureDrawable 绘制
        /*
        PictureDrawable pictureDrawable = new PictureDrawable(mPicture);
        //绘制的内容比选区大 不会缩放 会直接不显示
        pictureDrawable.setBounds(0, 0, 300, mPicture.getHeight());
        pictureDrawable.draw(canvas);
        */

        //Bitmap
        //BitmapFactory 从不同位置获取Bitmap
        Bitmap bitmap = null;
        //res 文件夹
        bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.chinese_400x300);
        //assets 文件夹
        try {
            InputStream inputStream = mContext.getAssets().open("chinese_400x300.png");
            bitmap = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //sd card file
        bitmap = BitmapFactory.decodeFile("/sdcard/bitmap.png");

        //network file
        /*
        bitmap = BitmapFactory.decodeStream(inputStream);
        inputStream.close();
        */
    }
}
