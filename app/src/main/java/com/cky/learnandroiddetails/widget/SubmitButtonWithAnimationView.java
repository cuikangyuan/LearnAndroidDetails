package com.cky.learnandroiddetails.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PathMeasure;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * Created by cuikangyuan on 2017/6/23.
 */

public class SubmitButtonWithAnimationView extends View {

    private int width;
    private int height;
    //圆角半径
    private int circleAngle;
    //默认两个圆心之间的距离  即 需要移动的距离
    private int default_two_circle_distance;

    private int two_circle_distance;
    private int bg_color = 0xffbc7d53;

    private String buttonString = "确认完成";

    private int duration = 1000;

    private int move_distance = 300;

    private Paint mRectPaint;//圆角矩形画笔
    private Paint mTextPaint;//文字画笔
    private Paint mOkPaint;//对勾画笔

    private Rect textRect = new Rect();

    private AnimatorSet mAnimatorSet = new AnimatorSet();

    private ValueAnimator animator_rect_to_angle;
    private ValueAnimator animator_rect_to_square;
    private ObjectAnimator animator_move_to_top;
    private ValueAnimator animator_draw_ok;

    private boolean startDrawOk = false;

    private RectF mRectF = new RectF();

    private Path mPath = new Path();
    private PathMeasure mPathMeasure;
    private PathEffect mPathEffect;

    public ButtonOnClickListener getListener() {
        return mListener;
    }

    public void setListener(ButtonOnClickListener listener) {
        mListener = listener;
    }

    private ButtonOnClickListener mListener;

    public SubmitButtonWithAnimationView(Context context) {
        this(context, null);
    }

    public SubmitButtonWithAnimationView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SubmitButtonWithAnimationView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onClick(v);
                }
            }
        });

        mAnimatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        mRectPaint = new Paint();
        mRectPaint.setAntiAlias(true);
        mRectPaint.setStrokeWidth(4);
        mRectPaint.setStyle(Paint.Style.FILL);
        mRectPaint.setColor(bg_color);

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(40);
        mTextPaint.setColor(Color.WHITE);
        mTextPaint.setTextAlign(Paint.Align.CENTER);

        mOkPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mOkPaint.setStyle(Paint.Style.STROKE);
        mOkPaint.setStrokeWidth(10);
        mOkPaint.setColor(Color.WHITE);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        width = w;
        height = h;

        default_two_circle_distance = (w - h) / 2;

        initOk();
        initAnimation();
    }

    private void initOk() {
        mPath.moveTo(default_two_circle_distance + height / 8 * 3, height / 2);
        mPath.lineTo(default_two_circle_distance + height / 2, height / 5 * 3);
        mPath.lineTo(default_two_circle_distance + height / 3 *2, height / 5 * 2);

        mPathMeasure = new PathMeasure(mPath, true);
    }

    private void initAnimation() {
        set_rect_to_angle_animation();
        set_rect_to_circle_animation();
        set_move_to_up_animation();
        set_draw_ok_animation();

        mAnimatorSet
                .play(animator_move_to_top)
                .before(animator_draw_ok)
                .after(animator_rect_to_angle)
                .after(animator_rect_to_square);
    }

    private void set_rect_to_angle_animation() {
        animator_rect_to_angle = ValueAnimator.ofInt(0, height / 2);
        animator_rect_to_angle.setDuration(duration);
        animator_rect_to_angle.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                circleAngle = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
    }

    private void set_rect_to_circle_animation() {
        animator_rect_to_square = ValueAnimator.ofInt(0, default_two_circle_distance);
        animator_rect_to_square.setDuration(duration);
        animator_rect_to_square.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                two_circle_distance = (int) animation.getAnimatedValue();

                int alpha = 255 - (two_circle_distance * 255) / default_two_circle_distance;

                mTextPaint.setAlpha(alpha);

                invalidate();

            }
        });
    }

    private void set_move_to_up_animation() {
        float currentTranslationY = this.getTranslationY();
        animator_move_to_top = ObjectAnimator.ofFloat(
                this,
                "translationY",
                currentTranslationY,
                currentTranslationY - move_distance
                );
        animator_move_to_top.setDuration(duration);
        animator_move_to_top.setInterpolator(new AccelerateDecelerateInterpolator());
    }

    private void set_draw_ok_animation() {
        animator_draw_ok = ValueAnimator.ofFloat(1, 0);
        animator_draw_ok.setDuration(duration);
        animator_draw_ok.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                startDrawOk = true;

                float value = (float) animation.getAnimatedValue();

                mPathEffect = new DashPathEffect(
                        new float[]{mPathMeasure.getLength(), mPathMeasure.getLength()},
                        value * mPathMeasure.getLength());

                mOkPaint.setPathEffect(mPathEffect);

                invalidate();
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        draw_oval_to_circle(canvas);
        draw_text(canvas);

        if (startDrawOk) {
            canvas.drawPath(mPath, mOkPaint);
        }
    }

    /**
     *
     * @param canvas
     */
    private void draw_oval_to_circle(Canvas canvas) {
        mRectF.left = two_circle_distance;
        mRectF.top = 0;
        mRectF.right = width - two_circle_distance;
        mRectF.bottom = height;

        canvas.drawRoundRect(mRectF, circleAngle, circleAngle, mRectPaint);
    }

    private void draw_text(Canvas canvas) {
        textRect.left = 0;
        textRect.top = 0;
        textRect.right = width;
        textRect.bottom = height;

        Paint.FontMetricsInt fontMetricsInt = mTextPaint.getFontMetricsInt();

        int baseline = (textRect.bottom + textRect.top - fontMetricsInt.bottom - fontMetricsInt.top) / 2;

        canvas.drawText(buttonString, textRect.centerX(), baseline, mTextPaint);
    }

    public void start() {
        mAnimatorSet.start();
    }

    public interface ButtonOnClickListener {
        void onClick(View view);
    }
}
