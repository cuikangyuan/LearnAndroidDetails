package com.cky.learnandroiddetails.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;

/**
 * 在 焦点变化时 和 输入的内容变化时 都需要判断是否显示  清除图标
 */

public class CleanableEditText extends EditText {

    private Drawable mRightDrawable;

    private boolean mHasFocus;

    public CleanableEditText(Context context) {
        super(context);
        init();
    }

    public CleanableEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CleanableEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        Drawable[] drawables = this.getCompoundDrawables();
        //相当于布局文件中设置的 android:drawableRight
        mRightDrawable = drawables[2];

        this.setOnFocusChangeListener(new OnFocusChangeListenerImpl());

        this.addTextChangedListener(new TextWatcherImpl());

        setClearDrawableVisible(false);

    }

    private class OnFocusChangeListenerImpl implements OnFocusChangeListener {

        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            mHasFocus = hasFocus;
            //EditText 此时获得焦点
            if (hasFocus) {
                boolean hasText = getText().toString().length() >= 1;
                if (hasText) {
                    setClearDrawableVisible(hasText);
                }
            } else {
                setClearDrawableVisible(false);
            }
        }
    }

    private class TextWatcherImpl implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            boolean isVisiable = getText().toString().length() >= 1;
            setClearDrawableVisible(isVisiable);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        /*
        * 当手指 抬起位置 在 清除图标 内时 视为进行了清除操作
        * */
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                boolean isClean = ((event.getX() > getWidth() - getTotalPaddingRight())
                                    && (event.getX() < getWidth() - getPaddingRight()));
                if (isClean) {
                    setText("");
                    setClearDrawableVisible(false);
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    protected void setClearDrawableVisible(boolean isVisible) {
        Drawable rightDrawable;
        if (isVisible) {
            rightDrawable = mRightDrawable;
        } else {
            rightDrawable = null;
        }

        setCompoundDrawables(getCompoundDrawables()[0],
                getCompoundDrawables()[1],
                rightDrawable,
                getCompoundDrawables()[3]);
    }

    public void setShakeAnimation() {
        this.shakeAnimation(5);
    }

    public Animation shakeAnimation(int cycleTimes) {
        Animation translateAnimation = new TranslateAnimation(0, 10, 0, 10);
        translateAnimation.setInterpolator(new CycleInterpolator(cycleTimes));
        translateAnimation.setDuration(1000);
        return translateAnimation;
    }
}
