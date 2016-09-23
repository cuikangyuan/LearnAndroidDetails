package com.cky.learnandroiddetails.model;

import com.cky.learnandroiddetails.R;

/**
 * 作者：cky
 * 时间：2016/9/23 13:22
 * 描述：
 */

public enum CustomPagerEnum {
    RED(R.string.red, R.layout.view_red),
    GREEN(R.string.green, R.layout.view_green),
    YELLOW(R.string.yellow, R.layout.view_yellow);

    private int mTitleResId;
    private int mLayoutResId;

    CustomPagerEnum(int titleResId, int layoutResId) {
        mTitleResId = titleResId;
        mLayoutResId = layoutResId;
    }

    public int getTitleResId() {
        return mTitleResId;
    }

    public void setTitleResId(int titleResId) {
        mTitleResId = titleResId;
    }

    public int getLayoutResId() {
        return mLayoutResId;
    }

    public void setLayoutResId(int layoutResId) {
        mLayoutResId = layoutResId;
    }
}
