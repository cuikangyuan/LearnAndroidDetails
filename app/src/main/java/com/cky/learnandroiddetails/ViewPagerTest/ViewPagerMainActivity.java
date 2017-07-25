package com.cky.learnandroiddetails.ViewPagerTest;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cky.learnandroiddetails.R;

public class ViewPagerMainActivity extends AppCompatActivity {

    public MyViewPagerAdapter.MyHandler mMyHandler;
    public ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_main);
    }
}
