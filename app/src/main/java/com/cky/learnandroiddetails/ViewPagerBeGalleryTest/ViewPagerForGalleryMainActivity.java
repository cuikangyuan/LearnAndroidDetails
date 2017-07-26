package com.cky.learnandroiddetails.ViewPagerBeGalleryTest;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.cky.learnandroiddetails.R;

import java.util.ArrayList;
import java.util.List;

/**
 *  viewpager设置setPageTransformer（）方法一定要在setAdapter（）方法之前
 */
public class ViewPagerForGalleryMainActivity extends AppCompatActivity {

    private View mRoot;
    private ViewPager mViewPager;
    private List<Integer> imgs = new ArrayList<>();
    private int pagerWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_for_gallery_main);

        imgs.add(R.mipmap.vp_1);
        //imgs.add(R.mipmap.vp_2);
        //imgs.add(R.mipmap.vp_3);
        //imgs.add(R.mipmap.vp_4);

        mRoot = findViewById(R.id.root);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);

        pagerWidth = (int) (getResources().getDisplayMetrics().widthPixels * 3.0f / 5.0f);
        ViewGroup.LayoutParams lp = mViewPager.getLayoutParams();
        if (lp == null) {
            lp = new ViewGroup.LayoutParams(pagerWidth, ViewGroup.LayoutParams.MATCH_PARENT);
        } else {
            lp.width = pagerWidth;
        }

        mViewPager.setLayoutParams(lp);
        mViewPager.setPageMargin(-50);
        findViewById(R.id.root).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return mViewPager.dispatchTouchEvent(motionEvent);
            }
        });
        //mViewPager.setPageTransformer(true, new GallyPageTransformer());
        //mViewPager.setAdapter(new MyViewPagerAdapter(imageViews));

    }
}
