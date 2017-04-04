package com.cky.learnandroiddetails.redpoint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.cky.learnandroiddetails.util.DisplayUtil;

/**
 * Created by cuikangyuan on 2017/4/4.
 */

public class badgeView extends View{


    private Context mContext;

    public badgeView(Context context) {
        this(context, null);
    }

    public badgeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public badgeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private void init() {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        String badgeText = "9";
        Paint mBadgePaint = new Paint();

        int mBadgePadding = 4;
        int mBadgeVerticalMargin = 0;
        int mBadgeHorizontalMargin = 0;
        int mBadgeBorderWidth = 1;
        int mBadgeBorderColor = Color.GREEN;
        int mBadgeBgColor = Color.RED;
        int mBadgeTextColor = Color.WHITE;
        int mBadgeTextSize = 10;

        mBadgePaint.setAntiAlias(true);
        mBadgePaint.setStyle(Paint.Style.FILL);
        // 设置mBadgeText居中，保证mBadgeText长度为1时，文本也能居中
        mBadgePaint.setTextAlign(Paint.Align.CENTER);
        mBadgePaint.setTextSize(DisplayUtil.dpToPx(mContext, mBadgeTextSize));

        Rect textRect = new Rect();
        RectF badgeRectF = new RectF();

        mBadgePaint.getTextBounds(badgeText, 0, badgeText.length(), textRect);

        int badgeHeight = textRect.height() + mBadgePadding * 2;
        int badgeWidth;

        if (badgeText.length() == 1 || badgeText.length() == 0) {
            badgeWidth = badgeHeight;
        } else {
            badgeWidth = textRect.width() + mBadgePadding * 2;
        }

        badgeRectF.top = mBadgeVerticalMargin;
        badgeRectF.bottom = this.getHeight() - mBadgeVerticalMargin;

        badgeRectF.right = this.getWidth() - mBadgeHorizontalMargin;
        badgeRectF.left = badgeRectF.right - badgeWidth;


        //边框 + 背景
        if (mBadgeBorderWidth > 0) {
            mBadgePaint.setColor(mBadgeBorderColor);
            canvas.drawRoundRect(badgeRectF, badgeHeight / 2, badgeHeight / 2, mBadgePaint);

            mBadgePaint.setColor(mBadgeBgColor);
            canvas.drawRoundRect(
                    new RectF(badgeRectF.left + mBadgeBorderWidth,
                            badgeRectF.top + mBadgeBorderWidth,
                            badgeRectF.right - mBadgeBorderWidth,
                            badgeRectF.bottom - mBadgeBorderWidth),
                    badgeHeight / 2,
                    badgeHeight / 2,
                    mBadgePaint);

        } else {
            mBadgePaint.setColor(mBadgeBorderColor);
            canvas.drawRoundRect(badgeRectF, badgeHeight / 2, badgeHeight / 2, mBadgePaint);
        }

        //文字
        if (!TextUtils.isEmpty(badgeText)) {
            mBadgePaint.setColor(mBadgeTextColor);
            //设置了mBadgeText居中，此处的x为徽章背景的中心点y
            float x = badgeRectF.left + badgeWidth / 2;
            float y = badgeRectF.bottom - mBadgePadding;
            //x默认是这个字符串的左边在屏幕的位置，如果设置了paint.setTextAlign(Paint.Align.CENTER);那就是字符的中心，y是指定这个字符baseline在屏幕上的位置
            canvas.drawText(badgeText, x, y, mBadgePaint);

        }
    }
}
