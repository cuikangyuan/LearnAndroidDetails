package com.cky.learnandroiddetails;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by Administrator on 2016/8/15.
 */

public class RecyclerViewPlus extends RecyclerView {

    public RecyclerViewPlus(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public RecyclerViewPlus(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RecyclerViewPlus(Context context) {
        super(context);
        init();
    }

    private void init() {

    }
}
