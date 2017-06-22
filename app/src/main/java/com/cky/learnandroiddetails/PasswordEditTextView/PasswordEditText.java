package com.cky.learnandroiddetails.PasswordEditTextView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

/**
 * Created by cuikangyuan on 2017/6/22.
 */

public class PasswordEditText extends EditText {

    private int mWidth;
    private int mHeight;

    private static final int MAX_COUNT = 6;

    private int mCircleStartX; //圆心起始位置x
    private int mCircleStartY; //圆心起始位置y

    private int mDivideLineStartX; //分割线起始位置

    private int mBottomLineWidth;

    private RectF mRectF = new RectF();

    private Paint mPaint;

    private int mLength;

    private static final int CIRCLE_RADIUS = 10;
    private static final int RECT_ROUND_RADIUS = 10;

    public PasswordEditText(Context context) {
        this(context, null);
    }

    public PasswordEditText(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);
    }

    public PasswordEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL);

        this.setFocusable(true);
        this.setEnabled(true);
        this.setBackgroundColor(Color.TRANSPARENT);
        this.setCursorVisible(false);
        this.setFilters(new InputFilter[]{new InputFilter.LengthFilter(MAX_COUNT)});


        //禁止长按全选功能 step 1
        setCustomSelectionActionModeCallback(new ActionMode.Callback() {
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {

            }
        });
        setLongClickable(false);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mWidth = w;
        mHeight = h;

        mDivideLineStartX = mWidth / MAX_COUNT;

        mCircleStartX = mDivideLineStartX / 2;
        mCircleStartY = mHeight / 2;

        mBottomLineWidth = mWidth / (MAX_COUNT + 2);

        mRectF.set(0, 0, mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        //super.onDraw(canvas);

        //画分割线
        for (int i = 1; i <= MAX_COUNT; i++) {
            canvas.drawLine(
                    mDivideLineStartX * i,
                    0,
                    mDivideLineStartX * i,
                    mHeight,
                    mPaint);
        }

        //画矩形
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRoundRect(mRectF, RECT_ROUND_RADIUS, RECT_ROUND_RADIUS, mPaint);
        mPaint.setStyle(Paint.Style.FILL);

        drawCircle(canvas);
    }

    //画圆
    private void drawCircle(Canvas canvas) {
        for (int i = 0; i < mLength; i++) {
            canvas.drawCircle(
                    mCircleStartX + (i * 2) * mCircleStartX  ,
                    mCircleStartY,
                    CIRCLE_RADIUS,
                    mPaint);
        }
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);

        mLength = text.toString().length();

        invalidate();
    }

    @Override
    protected void onSelectionChanged(int selStart, int selEnd) {
        super.onSelectionChanged(selStart, selEnd);
        //保证光标始终在最后
        if (selStart == selEnd) {
            setSelection(getText().length());
        }
    }
}
