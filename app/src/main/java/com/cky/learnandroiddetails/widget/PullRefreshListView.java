package com.cky.learnandroiddetails.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;

import com.cky.learnandroiddetails.R;

/**
 * Created by cuikangyuan on 16/8/13.
 */
public class PullRefreshListView extends ListView {


    private View headerView;
    private int headerHeight;

    public PullRefreshListView(Context context) {
        super(context);
        init();
    }

    public PullRefreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        initHeaderView();
    }

    private void initHeaderView() {
        headerView = View.inflate(getContext(), R.layout.refresh_header, null);
        headerView.measure(0, 0);
        headerHeight = headerView.getMeasuredHeight();
        headerView.setPadding(0, -headerHeight, 0, 0);

        addHeaderView(headerView);
    }
}
