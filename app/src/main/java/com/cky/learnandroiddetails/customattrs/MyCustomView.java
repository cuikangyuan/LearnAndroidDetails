package com.cky.learnandroiddetails.customattrs;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

import com.cky.learnandroiddetails.R;

/**
 * Created by cuikangyuan on 2017/1/20.
 */

public class MyCustomView extends View {

    public MyCustomView(Context context) {
        super(context, null);
    }

    public MyCustomView(Context context, AttributeSet attrs) {
        super(context, attrs, R.attr.custom_style);
    }

    public MyCustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.custom_attrs, defStyleAttr, R.style.default_style);
    }
}
