package com.cky.learnandroiddetails.ViewPagerBeGalleryTest;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cky.learnandroiddetails.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuikangyuan on 2017/7/26.
 */

public class GalleryViewPagerAdapter extends PagerAdapter {

    private List<Integer> imgs = new ArrayList<>();

    @Override
    public int getCount() {
        return imgs.size();
    }

    public GalleryViewPagerAdapter(List<Integer> imgs) {
        this.imgs = imgs;
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        /*
        ImageView view = new ImageView(container.getContext());
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        view.setLayoutParams(new RelativeLayout.LayoutParams(
                (int) DisplayUtil.dpToPx(container.getContext(), 200),
                (int) DisplayUtil.dpToPx(container.getContext(), 100)));
        view.setScaleType(ImageView.ScaleType.FIT_XY);
        view.setImageResource(imgs.get(position));
        */
        ImageView view = (ImageView) LayoutInflater.from(container.getContext()).inflate(R.layout.vp_image,null);
        view.setImageResource(imgs.get(position));
        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

}
