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
        canvas.drawBitmap(mBitmap, mMatrix, mDefaultPaint);

        /*
        矩阵 的 前乘  后乘 效果不同
        前乘 可以使 不同效果叠加
        后乘 对于不同效果的叠加 可能会 互相影响
        */

        mMatrix.preScale(1, 1);//前乘 mMatrix 矩阵在前  scale矩阵在后 这样实现 效果的叠加
    }
}
