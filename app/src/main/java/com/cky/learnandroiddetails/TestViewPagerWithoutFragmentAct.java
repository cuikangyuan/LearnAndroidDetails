package com.cky.learnandroiddetails;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cky.learnandroiddetails.adapter.CustomPagerAdapter;

/*
* https://www.bignerdranch.com/blog/viewpager-without-fragments/
* http://stackoverflow.com/questions/18710561/can-i-use-view-pager-with-views-not-with-fragments
* */
public class TestViewPagerWithoutFragmentAct extends AppCompatActivity {

    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_view_pager_without_fragment);

        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mViewPager.setAdapter(new CustomPagerAdapter(this));
    }
}
