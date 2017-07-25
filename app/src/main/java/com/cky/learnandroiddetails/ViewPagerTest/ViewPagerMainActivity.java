package com.cky.learnandroiddetails.ViewPagerTest;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.cky.learnandroiddetails.Camera.DisplayUtil;
import com.cky.learnandroiddetails.R;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ViewPagerMainActivity extends AppCompatActivity {

    public MyViewPagerAdapter.MyHandler mMyHandler;
    public RollPagerView mRollViewPager;
    private TestLoopAdapter mAdapter;

    private List<Integer> imgs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_main);


        mRollViewPager = (RollPagerView) findViewById(R.id.view_pager);
        mRollViewPager.setHintView(null);
        mAdapter = new TestLoopAdapter(mRollViewPager, imgs);
        mRollViewPager.setAdapter(mAdapter);

        imgs.add(R.mipmap.vp_1);
        imgs.add(R.mipmap.vp_2);
        imgs.add(R.mipmap.vp_3);
        imgs.add(R.mipmap.vp_4);

        mAdapter.notifyDataSetChanged();
        int width = DisplayUtil.getScreenMetrics(this).x - DisplayUtil.dip2px(this, 15) * 2;
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(width, ViewGroup.LayoutParams.MATCH_PARENT);
        mRollViewPager.setGravity(Gravity.CENTER);
        mRollViewPager.getViewPager().setOffscreenPageLimit(imgs.size());
        //设置下面属性导致左右两侧阴影部分不显示
        //mRollViewPager.getViewPager().setPageMargin(DisplayUtil.dip2px(this, 15));
        mRollViewPager.getViewPager().setLayoutParams(layoutParams);
        mRollViewPager.getViewPager().setPageTransformer(true, new ZoomOutPageTransformer());
        mRollViewPager.getViewPager().setOverScrollMode(ViewPager.OVER_SCROLL_NEVER);

        int mScrollSpeed = 450;
        try {
            Field mScroller =ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            AccelerateDecelerateInterpolator interpolator = new AccelerateDecelerateInterpolator();
            FixedSpeedScroller myScroller = new FixedSpeedScroller(this, interpolator, mScrollSpeed);
            mScroller.set(mRollViewPager.getViewPager(), myScroller);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private class TestLoopAdapter extends LoopPagerAdapter {
        private List<Integer> imgs = new ArrayList<>();

        public TestLoopAdapter(RollPagerView viewPager) {
            super(viewPager);
        }

        public TestLoopAdapter(RollPagerView viewPager, List<Integer> imgs) {
            super(viewPager);
            this.imgs = imgs;
        }

        @Override
        public View getView(ViewGroup container, int position) {
            ImageView view = new ImageView(container.getContext());
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setImageResource(imgs.get(position));

            return view;
        }

        @Override
        public int getRealCount() {
            return imgs.size();
        }
    }
}
