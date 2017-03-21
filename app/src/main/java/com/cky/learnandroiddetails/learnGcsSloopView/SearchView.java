package com.cky.learnandroiddetails.learnGcsSloopView;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by cuikangyuan on 2017/3/20.
 */

public class SearchView extends View {

    private Paint mPaint;

    private int mWidth;
    private int mHeight;


    public static enum State {
        NONE,
        STARTING,
        SEARCHING,
        ENDING
    }

    private State mCurrentState = State.NONE;

    private Path mSearchPath;
    private Path mCirclePath;

    private PathMeasure mMeasure;

    private int defaultDuration = 2000;

    private ValueAnimator mStartingAnimator;
    private ValueAnimator mSearchingAnimator;
    private ValueAnimator mEndingAnimator;

    private float mAnimatorValue = 0;

    private ValueAnimator.AnimatorUpdateListener mUpdateListener;
    private Animator.AnimatorListener mAnimatorListener;

    private Handler mAnimatorHandler;

    private boolean isOver = false;

    private int count = 0;


    public SearchView(Context context) {
        this(context, null);
    }

    public SearchView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SearchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        initPaint();

        initPath();

        initListener();

        initHandler();

        initAnimator();
    }

    private void initAnimator() {
    }

    private void initHandler() {
    }

    private void initListener() {
    }

    private void initPath() {
    }

    private void initPaint() {
    }
}
