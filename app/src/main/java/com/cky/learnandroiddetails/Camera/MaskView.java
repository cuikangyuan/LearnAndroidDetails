package com.cky.learnandroiddetails.Camera;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by cuikangyuan on 2017/6/13.
 */

public class MaskView extends ImageView{

    private static final String TAG = MaskView.class.getSimpleName();

    private Paint mLinePaint;
    private Paint mAreaPaint;
    private Rect mCenterRect = null;
    private Context mContext;

    private int widthScreen;
    private int heightScreen;

    public MaskView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initPaint();
        Point screenMetrics = DisplayUtil.getScreenMetrics(context);
        widthScreen = screenMetrics.x;
        heightScreen = screenMetrics.y;
    }

    private void initPaint() {
        //中间透明区域矩形边界
        mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLinePaint.setColor(Color.BLUE);
        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setStrokeWidth(5f);
        mLinePaint.setAlpha(30);

        //四周阴影区域
        mAreaPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mAreaPaint.setColor(Color.GRAY);
        mAreaPaint.setStyle(Paint.Style.FILL);
        mAreaPaint.setAlpha(180);
    }

    public void setCenterRect(Rect rect) {
        this.mCenterRect = rect;
        postInvalidate();
    }

    public void clearCenterRect() {
        this.mCenterRect = null;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mCenterRect == null) {
            return;
        }

        //绘制四周阴影区域
        canvas.drawRect(0, 0, widthScreen, mCenterRect.top, mAreaPaint);
        canvas.drawRect(0, mCenterRect.bottom + 1, widthScreen, heightScreen, mAreaPaint);
        canvas.drawRect(0, mCenterRect.top, mCenterRect.left - 1, mCenterRect.bottom + 1, mAreaPaint);
        canvas.drawRect(mCenterRect.right + 1, mCenterRect.top, widthScreen, mCenterRect.bottom + 1, mAreaPaint);

        //绘制目标透明区域
        canvas.drawRect(mCenterRect, mLinePaint);
    }
}
