package com.cky.learnandroiddetails.ViewPagerAdapterExtract;

import android.database.DataSetObserver;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.jude.rollviewpager.RollPagerView;

import java.util.ArrayList;

/**
 * Created by cuikangyuan on 2017/7/28.
 */

public abstract class LoopPagerAdapterExtracted2 extends PagerAdapter{

    private RollPagerView mViewPager;

    private ArrayList<View> mViewList = new ArrayList<>();

    public LoopPagerAdapterExtracted2(RollPagerView viewPager) {
        this.mViewPager = viewPager;
    }

    @Override
    public int getCount() {
        if (getRealCount() <= 1) {
            return getRealCount();
        } else {
            return getRealCount() + 2;
        }
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        int realPosition = getRealPosition(position);
        View itemView = findViewByPosition(container,realPosition);
        container.addView(itemView);
        return itemView;
    }

    private int getRealPosition(int position) {
        Log.d("looptag", "position -> " + position);
        int realCount = getRealCount();
        if (realCount == 0)
            return 0;
        int realPosition = (position - 1) % realCount;
        if (realPosition < 0) {
            realPosition += realCount;
        }
        Log.d("looptag", "realPosition-> " + realPosition);
        return realPosition;
    }

    private View findViewByPosition(ViewGroup container,int position){
        for (View view : mViewList) {
            if (((int)view.getTag()) == position && view.getParent() == null){
                return view;
            }
        }
        View view = getView(container,position);
        view.setTag(position);
        mViewList.add(view);
        return view;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        super.registerDataSetObserver(observer);
    }

    @Override
    public void notifyDataSetChanged() {
        mViewList.clear();
        super.notifyDataSetChanged();
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }


    public abstract View getView(ViewGroup container, int position);
    public abstract int getRealCount();
}
