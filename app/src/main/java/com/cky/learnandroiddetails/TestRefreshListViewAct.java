package com.cky.learnandroiddetails;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cky.learnandroiddetails.widget.PullRefreshListView;

import java.util.ArrayList;

public class TestRefreshListViewAct extends AppCompatActivity {

    private PullRefreshListView pullRefreshListView;

    private ArrayList<String> datas = new ArrayList<String>();

    PullRefreshListAdapter pullRefreshListAdapter;

    private Handler handler =  new Handler() {
        @Override
        public void handleMessage(Message msg) {
            pullRefreshListAdapter.notifyDataSetChanged();
            pullRefreshListView.refreshFinished();
        }
    };

    private static final String TAG = TestRefreshListViewAct.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();

    }

    private void initView() {
        setContentView(R.layout.activity_test_refresh_list_view);
        pullRefreshListView = (PullRefreshListView) findViewById(R.id.pullRefreshView);
    }

    private void initData() {
        for (int i = 0; i < 20; i++) {
            datas.add("原有数据-" + i);
        }
        /*
        final View headerView = View.inflate(this, R.layout.refresh_header, null);


        headerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                headerView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                //只有在 onLayout 之后执行 才能获得高
                int headerViewHeight = headerView.getHeight();

                //获取测量完的高度
                //int headerViewHeight = headerView.getMeasuredHeight();

                Log.d(TAG, "headerViewHeight -> " + headerViewHeight);

                headerView.setPadding(0, -headerViewHeight, 0, 0);

                pullRefreshListView.addHeaderView(headerView);
            }
        });


        headerView.measure(0, 0);//主动通知系统去测量
        int headerViewHeight = headerView.getMeasuredHeight();
        */
        pullRefreshListAdapter = new PullRefreshListAdapter();
        pullRefreshListView.setAdapter(pullRefreshListAdapter);

        pullRefreshListView.setOnRefreshListener(new PullRefreshListView.OnRefreshListener() {
            @Override
            public void onPullRefresh() {
                //在此处 请求服务器 数据
                getDataFromServer();
            }

            @Override
            public void onLoadingMore() {
                getMoreDataFromServer();
            }
        });
    }

    private void getDataFromServer() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(3000);
                datas.add(0, "下拉刷新的数据");

                handler.sendEmptyMessage(0);

            }
        }).start();
    }

    private void getMoreDataFromServer() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(3000);
                datas.add("加载更多的数据-1");
                datas.add("加载更多的数据-2");
                datas.add("加载更多的数据-3");

                handler.sendEmptyMessage(0);

            }
        }).start();
    }
    class PullRefreshListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            TextView textView = new TextView(TestRefreshListViewAct.this);
            textView.setPadding(60, 60, 60, 60);
            textView.setTextSize(18);
            textView.setText(datas.get(position));


            return textView;
        }

    }

}
