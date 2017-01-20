package com.cky.learnandroiddetails.customattrs;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.cky.learnandroiddetails.R;
import com.cky.learnandroiddetails.ToastUtil;

/**
 * Created by cuikangyuan on 2017/1/20.
 */

public class MyCustomView extends View {

    private static final String TAG = MyCustomView.class.getSimpleName();

    private int mBgColor;

    public MyCustomView(Context context) {
        this(context, null);
    }

    public MyCustomView(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.custom_style);
    }

    public MyCustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.custom_attrs, R.attr.custom_style, R.style.default_style);

        //setBackgroundColor(typedArray.getColor(R.styleable.custom_attrs_custom_color1, Color.BLACK));

        mBgColor = typedArray.getColor(R.styleable.custom_attrs_custom_color1, Color.BLACK);

        typedArray.recycle();


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setBackgroundColor(mBgColor);
    }
}
