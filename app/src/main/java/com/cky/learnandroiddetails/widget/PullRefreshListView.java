package com.cky.learnandroiddetails.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by cuikangyuan on 16/8/13.
 */
public class PullRefreshListView extends ListView {

    public PullRefreshListView(Context context) {
        super(context);
        init();
    }

    public PullRefreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {

    }
}
