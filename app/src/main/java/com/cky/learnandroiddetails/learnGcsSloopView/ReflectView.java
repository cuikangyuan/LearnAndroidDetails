package com.cky.learnandroiddetails.learnGcsSloopView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.cky.learnandroiddetails.R;

/**
 * Created by cuikangyuan on 2017/11/13.
 */

public class ReflectView extends View {

    private Bitmap mSrcBitmap, mRefBitmap;
    private Paint mPaint;
    private PorterDuffXfermode mXfermode;

    private static final String TAG = "ReflectView.";

    public ReflectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        initRes(context);
    }

    private void initRes(Context context) {
        mSrcBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.chinese_400x300);

        Matrix matrix = new Matrix();
        Log.d(TAG, "initRes: matrix init " + matrix.toString());
        matrix.setScale(1F, -1F);
        Log.d(TAG, "initRes: matrix after set scale " + matrix.toString());
        mRefBitmap = Bitmap.createBitmap(mSrcBitmap, 0, 0, mSrcBitmap.getWidth(), mSrcBitmap.getHeight(), matrix, true);

        mPaint = new Paint();
        mPaint.setShader(new LinearGradient(
                0, mSrcBitmap.getHeight(),
                0, mSrcBitmap.getHeight() * 5 / 4,
                0XDD000000, 0X10000000, Shader.TileMode.CLAMP));
        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        canvas.drawBitmap(mSrcBitmap, 0, 0, null);
        canvas.drawBitmap(mRefBitmap, 0, mSrcBitmap.getHeight(), null);

        mPaint.setXfermode(mXfermode);

        canvas.drawRect(0, mSrcBitmap.getHeight(), mSrcBitmap.getWidth(), mSrcBitmap.getHeight() * 2, mPaint);

        mPaint.setXfermode(null);


    }
}
