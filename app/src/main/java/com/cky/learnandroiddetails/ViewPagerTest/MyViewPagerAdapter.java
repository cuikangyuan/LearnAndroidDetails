package com.cky.learnandroiddetails.ViewPagerTest;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

import java.lang.ref.WeakReference;
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

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        position %= viewList.size();
        if (position < 0) {
            position = viewList.size() + position;
        }

        ImageView view = viewList.get(position);

        ViewParent viewParent = view.getParent();
        if (viewParent != null) {
            ViewGroup viewGroup = (ViewGroup) viewParent;
            viewGroup.removeView(view);
        }

        container.addView(view);

        return view;
    }

    public static class MyHandler extends Handler {
        //更新显示
        protected static final int MSG_UPDATE_VIEW = 1;
        //暂停轮播
        protected static final int MSG_KEEP_SILENT = 2;
        //恢复轮播
        protected static final int MSG_BREAK_SILENT = 3;
        //记录最新页码
        protected static final int MSG_UPDATE_INDEX = 4;

        protected static final int DELAY_TIME = 3000;

        private WeakReference<ViewPagerMainActivity> mWeakReference;

        private int currentIndex = 0;

        protected MyHandler(WeakReference<ViewPagerMainActivity> weakReference) {
            this.mWeakReference = weakReference;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ViewPagerMainActivity activity = mWeakReference.get();
            if (activity == null) {
                return;
            }

            if (activity.mMyHandler.hasMessages(MSG_UPDATE_VIEW)) {
                activity.mMyHandler.removeMessages(MSG_UPDATE_VIEW);
            }
            switch (msg.what) {
                case MSG_UPDATE_VIEW:
                    currentIndex++;
                    //activity.mViewPager.setCurrentItem(currentIndex);
                    activity.mMyHandler.sendEmptyMessageDelayed(MSG_UPDATE_VIEW, DELAY_TIME);
                    break;
                case MSG_KEEP_SILENT:
                    break;
                case MSG_BREAK_SILENT:
                    activity.mMyHandler.sendEmptyMessageDelayed(MSG_UPDATE_VIEW, DELAY_TIME);
                    break;
                case MSG_UPDATE_INDEX:
                    currentIndex = msg.arg1;
                    break;
                default:
                    break;
            }


        }
    }
}
