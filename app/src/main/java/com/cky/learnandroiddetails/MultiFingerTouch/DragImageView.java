package com.cky.learnandroiddetails.MultiFingerTouch;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.cky.learnandroiddetails.CustomViewBase.CustomView;
import com.cky.learnandroiddetails.R;

/**
 * Created by cuikangyuan on 2017/5/17.
 */

public class DragImageView extends CustomView {

    Bitmap mBitmap;
    RectF mBitmapRectF;
    Matrix mBitmapMatrix;

    boolean canDrag = false;
    PointF lastPoint = new PointF(0, 0);

    public DragImageView(Context context) {
        this(context, null);
    }

    public DragImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        BitmapFactory.Options options = new BitmapFactory.Options();

        options.outWidth = 960 / 2;
        options.outHeight = 800 / 2;

        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.chinese_400x300, options);
        mBitmapRectF = new RectF(0, 0, mBitmap.getWidth(), mBitmap.getHeight());
        mBitmapMatrix = new Matrix();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                //判断是否是第一个手指 是否包含在图片区域内
                if (event.getPointerId(event.getActionIndex()) == 0
                        && mBitmapRectF.contains(event.getX(), event.getY())) {
                    canDrag = true;
                    lastPoint.set(event.getX(), event.getY());
                }

                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                //判断是否是第一个手指
                if (event.getPointerId(event.getActionIndex()) == 0) {
                    canDrag = false;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                //如果存在第一个手指 落点在图片区域内
                if (canDrag) {
                    int index = event.findPointerIndex(0);
                    mBitmapMatrix.postTranslate(
                            event.getX(index) - lastPoint.x,
                            event.getY(index) - lastPoint.y);

                    lastPoint.set(event.getX(index), event.getY(index));

                    mBitmapRectF = new RectF(0, 0, mBitmap.getWidth(), mBitmap.getHeight());

                    mBitmapMatrix.mapRect(mBitmapRectF);

                    invalidate();
                }
                break;
        }

        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(mBitmap, mBitmapMatrix, mDefaultPaint);
    }
}
