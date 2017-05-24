package com.cky.learnandroiddetails.Matrix;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.cky.learnandroiddetails.CustomViewBase.CustomView;
import com.cky.learnandroiddetails.R;

/**
 * Created by cuikangyuan on 2017/5/24.
 */

public class MatrixTestView extends CustomView {

    private Bitmap mBitmap;

    private Matrix mMatrix;

    public MatrixTestView(Context context) {
        this(context, null);
    }

    public MatrixTestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    private void init() {

        mDefaultPaint.setAntiAlias(true);

        mDefaultPaint.setStyle(Paint.Style.FILL);

        BitmapFactory.Options options = new BitmapFactory.Options();

        options.outWidth = 960 / 2;
        options.outHeight = 800 / 2;

        mBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.chinese_400x300);

        mMatrix = new Matrix();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(getWidth() / 2, getHeight() / 2);
        //位移移动
        //mMatrix.setTranslate(100, 0);

        //顺时针旋转 旋转中心 0, 0
        int degree1 = 90;
        int degree2 = -90;

        //mMatrix.setRotate(degree2);
        //mMatrix.setRotate(degree2, mBitmap.getWidth() / 2, mBitmap.getHeight() / 2);

        //错切 错切因子 为 错切角度 tan 值
        //水平错切 是 改变 x 坐标 为 x + my
        //垂直错切  是 改变 y 坐标 为 y + mx
        //mMatrix.setSkew(0.1f, 0);

        //mMatrix.setSkew(0.1f, 0, mBitmap.getWidth() / 2, mBitmap.getHeight() / 2);
        //mMatrix.setSkew(0.1f, 0, mBitmap.getWidth() / 2, mBitmap.getHeight() / 2);
        //mMatrix.setSkew(0.1f, 0, 0, 0);
        //错切中心 好像 没什么效果

        /*
        矩阵 的 前乘  后乘 效果不同
        前乘 可以使 不同效果叠加
        后乘 对于不同效果的叠加 可能会 互相影响
        */

        //mMatrix.preScale(1, 1);//前乘 mMatrix 矩阵在前  scale矩阵在后 这样实现 效果的叠加

        /*
        setPolyToPoly 通过指定的0-4个点  原始坐标以及变化后的坐标  获取变换矩阵
         */

        //一个点  平移
        /*
        float[] src = {0, 0};
        int dx = 100;
        float[] dst = {0 + dx, 0 - dx};
        mMatrix.setPolyToPoly(src, 0, dst, 0, 1);
        */

        /*
        两个点
        旋转   或者  缩放
         */
        int dx = 100;
        /*
        float[] src = {0 - dx, 0 - dx, mBitmap.getWidth(), mBitmap.getHeight()};

        float[] dst = {0 - dx, 0 - dx, mBitmap.getWidth(), mBitmap.getHeight()};
        mMatrix.setPolyToPoly(src, 0, dst, 0, 2);
        */

        int bw = mBitmap.getWidth();
        int bh = mBitmap.getHeight();
        /*
        float[] src = {bw / 2, bh / 2, bw, 0};

        float[] dst = {bw / 2, bh / 2, bh / 2 + bw / 2, bw / 2 + bh / 2};
        mMatrix.setPolyToPoly(src, 0, dst, 0, 2);
        */

        //三个点 错切
        /*
        float[] src = {0, 0, 0, bh, bw, bh};
        float[] dst = {0, 0, 200, bh, bw + 200, bh};
        mMatrix.setPolyToPoly(src, 0, dst, 0, 3);
        */

        //透视
        float[] src = {0, 0, 0, bh, bw, 0, bw, bh};
        float[] dst = {0 + dx, 0, 0, bh, bw - dx, 0, bw, bh};
        mMatrix.setPolyToPoly(src, 0, dst, 0, 4);

        //翻转
        //invert 能反转就 返回true 并将反转后的值 写进 方法形参 否则返回false

        Matrix matrix = new Matrix();
/*
        if (mMatrix.invert(matrix)) {
            canvas.drawBitmap(mBitmap, matrix, mDefaultPaint);

        } else {
            canvas.drawBitmap(mBitmap, mMatrix, mDefaultPaint);

        }
*/

        //mMatrix.preTranslate(- mBitmap.getWidth() / 2, - mBitmap.getHeight() / 2);
        /*
        matrix.setScale(0.5f, 0.5f);
        matrix.preTranslate(- mBitmap.getWidth() / 2, - mBitmap.getHeight() / 2);
        matrix.preRotate(90);
        */
        //matrix.setTranslate(- mBitmap.getWidth() / 2, - mBitmap.getHeight() / 2);
        //matrix.setScale(0.5f, 0.5f);
        matrix.setTranslate(- mBitmap.getWidth() / 2, - mBitmap.getHeight() / 2);
        matrix.preRotate(90, mBitmap.getWidth() / 2, mBitmap.getHeight() / 2);
        matrix.preRotate(90, mBitmap.getWidth() / 2, mBitmap.getHeight() / 2);
        matrix.preScale(0.5f, 0.5f, mBitmap.getWidth() / 2, mBitmap.getHeight() / 2);
        canvas.drawBitmap(mBitmap, matrix, mDefaultPaint);
    }
}
