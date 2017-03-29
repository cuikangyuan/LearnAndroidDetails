package com.cky.learnandroiddetails.learnGcsSloopView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.cky.learnandroiddetails.R;

/**
 * Created by cuikangyuan on 2017/3/29.
 */

public class MatrixSetPolyToPolyTestView extends View {

    private Bitmap mBitmap;
    private Matrix mPolyMatrix;

    public MatrixSetPolyToPolyTestView(Context context) {
        super(context);

        initBitmapAndMatrix();
    }

    public MatrixSetPolyToPolyTestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initBitmapAndMatrix();
    }

    public MatrixSetPolyToPolyTestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initBitmapAndMatrix();
    }

    private void initBitmapAndMatrix() {
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.chinese_400x300);
        mPolyMatrix = new Matrix();

        //左上 右上 右下 左下
        float[] src = {0, 0,
                        mBitmap.getWidth(), 0,
                        mBitmap.getWidth(), mBitmap.getHeight(),
                        0, mBitmap.getHeight()};

        float[] dst = {0, 0,
                mBitmap.getWidth(), 100,
                mBitmap.getWidth(), mBitmap.getHeight()-200,
                0, mBitmap.getHeight()};


        mPolyMatrix.setPolyToPoly(src, 0, dst, 0, 4);

        //mPolyMatrix.postScale(0.26f, 0.26f);
        //mPolyMatrix.postTranslate(0, 200);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(mBitmap, mPolyMatrix, null);
    }
}
