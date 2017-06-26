package com.cky.learnandroiddetails.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

/**
 * Created by cuikangyuan on 2017/6/26.
 */

public class FadeInTextView extends TextView {

    private StringBuffer mStringBuffer = new StringBuffer();
    private RectF mTextRect = new RectF();

    private ValueAnimator mValueAnimator;

    private static final int DURATION = 300;

    private String[] arr;

    private int textCount;

    private int currentIndex = -1;

    private TextAnimationListener mTextAnimationListener;

    public TextAnimationListener getTextAnimationListener() {
        return mTextAnimationListener;
    }

    public void setTextAnimationListener(TextAnimationListener textAnimationListener) {
        mTextAnimationListener = textAnimationListener;
    }
    public FadeInTextView(Context context) {
        this(context, null);
    }

    public FadeInTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FadeInTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /*
        if (mStringBuffer != null) {
            drawText(canvas, mStringBuffer.toString());
        }
        */

        //直接使用setText 可以不用自己控制绘制逻辑 支持多行展示
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    private void drawText(Canvas canvas, String content) {
        mTextRect.left = getPaddingLeft();
        mTextRect.top = getPaddingTop();
        mTextRect.right = getWidth() - getPaddingRight();
        mTextRect.bottom = getHeight() - getPaddingBottom();

        Paint.FontMetricsInt fontMetricsInt = getPaint().getFontMetricsInt();
        int baseLine = (int) (mTextRect.top + mTextRect.bottom - fontMetricsInt.top - fontMetricsInt.bottom) / 2;

        canvas.drawText(content, getPaddingLeft(), baseLine, getPaint());
    }


    public FadeInTextView setTextString(String textString) {
        if (!TextUtils.isEmpty(textString)) {
            textCount = textString.length();

            arr = new String[textCount];

            for (int i = 0; i < textCount; i++) {
                arr[i] = textString.substring(i, i+1);
            }

            initAnimation();
        }
        return this;
    }

    private void initAnimation() {
        if (textCount == 0) {
            return;
        }

        mValueAnimator = ValueAnimator.ofInt(0, textCount - 1);
        mValueAnimator.setDuration(textCount * DURATION);
        mValueAnimator.setInterpolator(new LinearInterpolator());
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int animatedValue = (int) animation.getAnimatedValue();
                if (currentIndex != animatedValue) {

                    mStringBuffer.append(arr[animatedValue]);
                    currentIndex = animatedValue;

                    setText(mStringBuffer.toString());

                    if (currentIndex == textCount - 1) {
                        if (mTextAnimationListener != null) {
                            mTextAnimationListener.animationFinish();
                        }
                    }

                    invalidate();
                }

            }
        });
    }

    public FadeInTextView startAnimation() {
        if (mValueAnimator != null) {
            mStringBuffer.setLength(0);
            currentIndex = -1;
            mValueAnimator.start();
        }
        return this;
    }

    private FadeInTextView stopAnimation() {
        if (mValueAnimator != null) {
            mValueAnimator.end();
        }
        return this;
    }

    public interface TextAnimationListener {
        void animationFinish();
    }


}
