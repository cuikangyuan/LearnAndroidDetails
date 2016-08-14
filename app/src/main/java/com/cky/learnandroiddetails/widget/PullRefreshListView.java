package com.cky.learnandroiddetails.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cky.learnandroiddetails.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by cuikangyuan on 16/8/13.
 */
public class PullRefreshListView extends ListView implements AbsListView.OnScrollListener{


    private View headerView;
    private View footerView;
    private int headerHeight;
    private int footerHeight;

    private int downY;//按下时 Y 坐标

    private final int PULL_REFRESH = 0; //下拉刷新状态
    private final int RELEASE_REFRESH = 1; // 松开刷新状态
    private final int REFRESHING = 2; //正在刷新状态

    private int currentState = PULL_REFRESH;


    private ImageView ivArrow;
    private TextView tvRefreshState;
    private ProgressBar pbRefresh;
    private TextView tvLastRefreshTime;

    private RotateAnimation upAnim, downAnim;

    private OnRefreshListener onRefreshListener;

    private boolean isLoadingMore = false;

    public PullRefreshListView(Context context) {
        super(context);
        init();
    }

    public PullRefreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setOnScrollListener(this);
        initHeaderView();
        initFooterView();
        initRotateAnim();
    }

    private void initRotateAnim() {
        upAnim = new RotateAnimation(0, -180, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        upAnim.setDuration(300);
        upAnim.setFillAfter(true);

        downAnim = new RotateAnimation(-180, -360, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        downAnim.setDuration(300);
        downAnim.setFillAfter(true);
    }

    private void initHeaderView() {
        headerView = View.inflate(getContext(), R.layout.refresh_header, null);

        ivArrow = (ImageView) headerView.findViewById(R.id.iv_arrow);
        tvRefreshState = (TextView) headerView.findViewById(R.id.tv_refresh_state);
        tvLastRefreshTime = (TextView) headerView.findViewById(R.id.tv_last_refresh_time);
        pbRefresh = (ProgressBar) headerView.findViewById(R.id.pb_refresh);

        headerView.measure(0, 0);
        headerHeight = headerView.getMeasuredHeight();
        headerView.setPadding(0, -headerHeight, 0, 0);

        addHeaderView(headerView);
    }

    private void initFooterView() {
        footerView = View.inflate(getContext(), R.layout.refresh_footer, null);

        footerView.measure(0, 0);
        footerHeight = footerView.getMeasuredHeight();
        footerView.setPadding(0, -footerHeight, 0, 0);

        addFooterView(footerView);
    }
    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downY = (int)ev.getY();
                break;
            case MotionEvent.ACTION_UP:
                if (currentState == PULL_REFRESH) {
                    //隐藏 header
                    headerView.setPadding(0, -headerHeight, 0, 0);
                } else if (currentState == RELEASE_REFRESH) {
                    //header 完全显示 并显示 刷新进度条
                    headerView.setPadding(0, 0, 0, 0);
                    currentState = REFRESHING;
                    refreshHeaderView();
                }
                break;
            case MotionEvent.ACTION_MOVE:

                if (currentState == REFRESHING) {
                    break;
                }

                int deltaY = (int) (ev.getY() - downY);
                int paddingTop = -headerHeight + deltaY;

                if (paddingTop > -headerHeight && getFirstVisiblePosition() == 0) {

                    headerView.setPadding(0, paddingTop, 0, 0);

                    if (paddingTop >= 0 && currentState == PULL_REFRESH) {
                        //从下拉刷新状态进入松开刷新状态
                        currentState = RELEASE_REFRESH;
                        refreshHeaderView();

                    } else if (paddingTop < 0 && currentState == RELEASE_REFRESH) {
                        //从松开刷新状态进入下拉刷新状态
                        currentState = PULL_REFRESH;
                        refreshHeaderView();
                    }

                    return true;//不让 ListView 处理该事件
                }
                break;
        }
        return super.onTouchEvent(ev);
    }

    /*
    * 根据当前状态 更新 headerView
    * */
    private void refreshHeaderView() {
        switch (currentState) {
            case PULL_REFRESH:
                tvRefreshState.setText("下拉刷新");
                ivArrow.startAnimation(downAnim);
                break;
            case RELEASE_REFRESH:
                tvRefreshState.setText("松开刷新");
                ivArrow.startAnimation(upAnim);
                break;
            case REFRESHING:
                ivArrow.clearAnimation();
                ivArrow.setVisibility(View.INVISIBLE);
                pbRefresh.setVisibility(View.VISIBLE);
                tvRefreshState.setText("正在刷新");

                if (onRefreshListener != null) {
                    onRefreshListener.onPullRefresh();
                }
                /*
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshFinished();
                    }
                }, 3000);
                */
                break;

        }
    }

    //完成刷新 获取完数据并 更新完Adapter时 调用此 方法(UI线程)
    public void refreshFinished() {
        if (isLoadingMore) {
            footerView.setPadding(0, -footerHeight, 0, 0);
            isLoadingMore = false;
        } else {
            headerView.setPadding(0, -headerHeight, 0, 0);
            currentState = PULL_REFRESH;
            pbRefresh.setVisibility(View.INVISIBLE);
            ivArrow.setVisibility(View.VISIBLE);
            tvRefreshState.setText("下拉刷新");
            tvLastRefreshTime.setText("最后刷新: " + getCurrentTime());
        }

    }

    private String getCurrentTime() {
        SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        return format.format(new Date());
    }

    public void setOnRefreshListener(OnRefreshListener listener) {
        onRefreshListener = listener;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == OnScrollListener.SCROLL_STATE_IDLE &&
                getLastVisiblePosition() == (getCount() - 1) &&
                !isLoadingMore) {
            isLoadingMore = true;
            //此时显示 footer
            footerView.setPadding(0, 0, 0, 0);
            setSelection(getCount());

            if (onRefreshListener != null) {
                onRefreshListener.onLoadingMore();
            }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }

    public interface OnRefreshListener {
        void onPullRefresh();
        void onLoadingMore();
    }
}
