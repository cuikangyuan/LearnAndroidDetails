package com.cky.learnandroiddetails.ViewPagerTest;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

/**
 * Created by cuikangyuan on 2017/7/25.
 */

public class FixedSpeedScroller extends Scroller {
    private int mScrollSpeed = 450;

    public FixedSpeedScroller(Context context) {
        super(context);
    }

    public FixedSpeedScroller(Context context, Interpolator interpolator, int scrollSpeed) {
        super(context, interpolator);
        this.mScrollSpeed = scrollSpeed;
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        super.startScroll(startX, startY, dx, dy, this.mScrollSpeed);
    }
}
