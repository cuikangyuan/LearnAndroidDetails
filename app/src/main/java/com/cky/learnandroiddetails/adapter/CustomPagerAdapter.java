package com.cky.learnandroiddetails.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cky.learnandroiddetails.model.CustomPagerEnum;

/**
 * 作者：cky
 * 时间：2016/9/23 13:30
 * 描述：
 */

public class CustomPagerAdapter extends PagerAdapter{

    private Context mContext;

    public CustomPagerAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return CustomPagerEnum.values().length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        CustomPagerEnum customPagerEnum = CustomPagerEnum.values()[position];
        ViewGroup view = (ViewGroup) LayoutInflater.from(mContext).inflate(customPagerEnum.getLayoutResId(), container, false);
        container.addView(view);

        return view;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        CustomPagerEnum customPagerEnum = CustomPagerEnum.values()[position];
        return mContext.getString(customPagerEnum.getTitleResId());
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
       container.removeView((View)object);
    }
}
