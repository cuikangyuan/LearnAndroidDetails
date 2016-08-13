package com.cky.learnandroiddetails;

import android.os.Bundle;
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

        View headerView = View.inflate(this, R.layout.refresh_header, null);
        int headerViewHeight = headerView.getHeight();

        headerView.setPadding(0, -headerViewHeight, 0, 0);

        pullRefreshListView.addHeaderView(headerView);

        pullRefreshListAdapter = new PullRefreshListAdapter();
        pullRefreshListView.setAdapter(pullRefreshListAdapter);
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
            textView.setPadding(20, 20, 20, 20);
            textView.setTextSize(18);
            textView.setText(datas.get(position));


            return textView;
        }

    }

}
