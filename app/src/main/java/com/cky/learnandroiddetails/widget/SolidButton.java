package com.cky.learnandroiddetails.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;

import com.cky.learnandroiddetails.R;


/**
 * 作者：cky
 * 时间：2017/1/17 10:51
 * 描述：背景是纯色的按钮
 */

public class SolidButton extends TextView {

    private Context mContext;

    private int mNormalBgColor; //默认状态背景色
    private int mTouchedBgColor; //触摸状态背景色
    private int mUnavailableBgColor; //不可用状态背景色

    private int mCornerRadius = 0; //圆角半径

    private Rect mBound;
    private Paint mPaint;

    public SolidButton(Context context) {
        this(context, null);
    }

    public SolidButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SolidButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initAttrs(context, attrs);
    }

    /**
    * 时间：2017/1/17 17:37
    * 描述：获取布局文件中的参数
    * 参数：
    * 返回值：
    */
    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray styledAttributes = context.obtainStyledAttributes(attrs, R.styleable.SolidButton);

        mNormalBgColor = styledAttributes.getColor(R.styleable.SolidButton_normalBgColor, getResources().getColor(R.color.solid_button_normal_bg_color));
        mTouchedBgColor = styledAttributes.getColor(R.styleable.SolidButton_touchedBgColor, getResources().getColor(R.color.solid_button_touched_bg_color));
        mUnavailableBgColor = styledAttributes.getColor(R.styleable.SolidButton_unavailableBgColor, getResources().getColor(R.color.solid_button_disable_bg_color));

        //mCornerRadius = styledAttributes.getDimensionPixelSize(R.styleable.SolidButton_solidButtonCornerRadius, (int) TypedValue.applyDimension(
        //        TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics()));

        mCornerRadius = styledAttributes.getDimensionPixelSize(R.styleable.SolidButton_solidButtonCornerRadius, (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics()));

        styledAttributes.recycle();

        setupBackground();
    }

    /**
    * 时间：2017/1/17 13:21
    * 描述：根据参数组装drawable
    * 参数：
    * 返回值：
    */
    private void setupBackground() {
        //正常状态
        GradientDrawable normalDrawable = new GradientDrawable();
        normalDrawable.setCornerRadius(mCornerRadius);
        normalDrawable.setColor(mNormalBgColor);
        //按下状态
        GradientDrawable touchedDrawable = new GradientDrawable();
        touchedDrawable.setCornerRadius(mCornerRadius);
        touchedDrawable.setColor(mTouchedBgColor);
        //touchedDrawable.setAlpha(38);

        //不可用状态
        GradientDrawable disableDrawable = new GradientDrawable();
        disableDrawable.setCornerRadius(mCornerRadius);
        disableDrawable.setColor(mUnavailableBgColor);

        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[] {android.R.attr.state_pressed, android.R.attr.state_enabled}, touchedDrawable);
        stateListDrawable.addState(new int[] {android.R.attr.state_focused, android.R.attr.state_enabled}, touchedDrawable);
        stateListDrawable.addState(new int[] {-android.R.attr.state_enabled}, disableDrawable);
        stateListDrawable.addState(new int[] {}, normalDrawable);

        if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            this.setBackgroundDrawable(stateListDrawable);
        } else {
            this.setBackground(stateListDrawable);
        }

        mPaint = new Paint();
        mBound = new Rect();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    /*
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width;
        int height ;
        if (widthMode == MeasureSpec.EXACTLY)
        {
            width = widthSize;
        } else {
            mPaint.setTextSize(getTextSize());
            if (getText() != null && !TextUtils.isEmpty(getText().toString())) {
                mPaint.getTextBounds(getText().toString(), 0, getText().toString().length(), mBound);
            }
            float textWidth = mBound.width();
            int desired = (int) (getPaddingLeft() + textWidth + getPaddingRight());
            width = desired;
        }

        if (heightMode == MeasureSpec.EXACTLY)
        {
            height = heightSize;
        } else {
            mPaint.setTextSize(getTextSize());
            if (getText() != null && !TextUtils.isEmpty(getText().toString())) {
                mPaint.getTextBounds(getText().toString(), 0, getText().toString().length(), mBound);
            }
            float textHeight = mBound.height();
            int desired = (int) (getPaddingTop() + textHeight + getPaddingBottom());
            height = desired;
        }

        setMeasuredDimension(width, height);
    }

    */
    /**
    * 时间：2017/1/18 13:13
    * 描述：
    * 参数：
    * 返回值：
    */
    public void setNormalBgColor(int normalBgColor) {
        this.mNormalBgColor = normalBgColor;
        this.setupBackground();
    }
    
    /**
    * 时间：2017/1/18 13:13
    * 描述：
    * 参数：
    * 返回值：
    */
    public void setTouchedBgColor(int touchedBgColor) {
        this.mTouchedBgColor = touchedBgColor;
        this.setupBackground();
    }
    /**
    * 时间：2017/1/18 13:13
    * 描述：
    * 参数：
    * 返回值：
    */
    public void setUnavailableBgColor(int unavailableBgColor) {
        this.mUnavailableBgColor = unavailableBgColor;
        this.setupBackground();
    }
    /**
    * 时间：2017/1/18 13:13
    * 描述：注意参数的单位应该为px值
    * 参数：
    * 返回值：
    */
    public void setCornerRadius(int cornerRadius) {
        this.mCornerRadius = cornerRadius;
        this.setupBackground();
    }

    @Override
    public void setTextSize(float size) {
        setTextSize(TypedValue.COMPLEX_UNIT_DIP, size);
    }
}
