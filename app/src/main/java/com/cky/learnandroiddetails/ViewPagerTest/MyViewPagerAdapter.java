package com.cky.learnandroiddetails.ViewPagerTest;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by cuikangyuan on 2017/7/24.
 */

public class MyViewPagerAdapter extends PagerAdapter {

    private ArrayList<ImageView> viewList = new ArrayList<>();

    private MyViewPagerAdapter(ArrayList<ImageView> viewList) {
        this.viewList = viewList;
    }

    @Override
    public int getCount() {
        //设置成最大 用户看不到边界
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //super.destroyItem(container, position, object);
        //不需要在此处removeView
    }
}
