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
import com.cky.learnandroiddetails.ViewPagerAdapterExtract.LoopPagerAdapterExtracted;
import com.cky.learnandroiddetails.ViewPagerAdapterExtract.LoopPagerAdapterExtracted2;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * 1.可以使用正常adapter实现单侧有缓存显示
 * 2.可以使用LoopAdapter实现双侧有缓存显示
 */

public class ViewPagerMainActivity extends AppCompatActivity {

    public MyViewPagerAdapter.MyHandler mMyHandler;
    public RollPagerView mRollViewPager;
    private TestLoopAdapter mAdapter;
    private TestNomalAdapter mAdapter1;
    private TestLoopAdapter1 mAdapter2;//提取出的adapter
    private TestLoopAdapter2 mAdapter3;//提取出的adapter

    private List<Integer> imgs = new ArrayList<>();
    int width;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_main);
        width = DisplayUtil.getScreenMetrics(this).x - DisplayUtil.dip2px(this, 15) * 2;


        mRollViewPager = (RollPagerView) findViewById(R.id.view_pager);
        mRollViewPager.setHintView(null);
        mAdapter = new TestLoopAdapter(mRollViewPager, imgs);


        mAdapter1 = new TestNomalAdapter(imgs);
        mAdapter2 = new TestLoopAdapter1(mRollViewPager, imgs);
        mAdapter3 = new TestLoopAdapter2(mRollViewPager, imgs);
        mRollViewPager.setAdapter(mAdapter2);

        imgs.add(R.mipmap.vp_1);
        imgs.add(R.mipmap.vp_2);
        imgs.add(R.mipmap.vp_3);
        //imgs.add(R.mipmap.vp_4);

        mAdapter.notifyDataSetChanged();
        mAdapter2.notifyDataSetChanged();
        mAdapter3.notifyDataSetChanged();
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(width, ViewGroup.LayoutParams.MATCH_PARENT);
        mRollViewPager.setGravity(Gravity.CENTER);


        if (imgs.size() == 1) {
            mRollViewPager.getViewPager().setOffscreenPageLimit(0);
            mRollViewPager.setPlayDelay(0);//不轮播
        } else {
            mRollViewPager.getViewPager().setOffscreenPageLimit(imgs.size());//两面都会出现阴影
            mRollViewPager.setPlayDelay(0);//不轮播
        }
        //设置下面属性导致左右两侧阴影部分不显示
        //mRollViewPager.getViewPager().setPageMargin(DisplayUtil.dip2px(this, 15));
        mRollViewPager.getViewPager().setLayoutParams(layoutParams);

        //mRollViewPager.getViewPager().setClipChildren(false);//设置此属性会导致 图片超出布局限制
        //mRollViewPager.getViewPager().setPageMargin(DisplayUtil.dip2px(this, 15));
        //设置拖拽时的动画，设置以后不显示左右两个局部视图
        mRollViewPager.getViewPager().setPageTransformer(true, new ZoomOutPageTransformer());
        //设置一下属性导致拉到头的光晕不显示
        mRollViewPager.getViewPager().setOverScrollMode(ViewPager.OVER_SCROLL_NEVER);

        //设置scroller

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

    public class TestNomalAdapter extends StaticPagerAdapter {

        private List<Integer> imgs = new ArrayList<>();

        public TestNomalAdapter(List<Integer> imgs) {
            this.imgs = imgs;
        }
        @Override
        public View getView(ViewGroup container, int position) {
            ImageView view = new ImageView(container.getContext());
            view.setImageResource(imgs.get(position));
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            return view;
        }

        @Override
        public int getCount() {
            return imgs.size();
        }
    }

    public class TestLoopAdapter extends LoopPagerAdapter {
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

    public class TestLoopAdapter1 extends LoopPagerAdapterExtracted {
        private List<Integer> imgs = new ArrayList<>();

        public TestLoopAdapter1(RollPagerView viewPager) {
            super(viewPager);
        }

        public TestLoopAdapter1(RollPagerView viewPager, List<Integer> imgs) {
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
            if (imgs != null) {
                return imgs.size();
            } else {
                return 0;
            }
        }
    }

    public class TestLoopAdapter2 extends LoopPagerAdapterExtracted2 {
        private List<Integer> imgs = new ArrayList<>();

        public TestLoopAdapter2(RollPagerView viewPager) {
            super(viewPager);
        }

        public TestLoopAdapter2(RollPagerView viewPager, List<Integer> imgs) {
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
