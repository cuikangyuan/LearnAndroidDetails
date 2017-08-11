package com.cky.learnandroiddetails.ThreeDRotateAnimation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.cky.learnandroiddetails.R;

public class ThreeDRotationAct extends AppCompatActivity {

    View mRoot;

    ImageView mImageView1;
    ImageView mImageView2;

    boolean mShouldUpdateViewStatus = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_drotation);

        mRoot = findViewById(R.id.root);

        mImageView1 = (ImageView) findViewById(R.id.image1);
        mImageView2 = (ImageView) findViewById(R.id.image2);

        mImageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //翻转测试
                float cX = mRoot.getWidth() / 2.0f;
                float cY = mRoot.getHeight() / 2.0f;
                mShouldUpdateViewStatus = true;
                MyRotation rotateAnim = new MyRotation(cX, cY, MyRotation.ROTATE_INCREASE);
                rotateAnim.setInterpolatedTimeListener(new MyRotation.InterpolatedTimeListener() {
                    @Override
                    public void interpolatedTime(float interpolatedTime) {
                        if (mShouldUpdateViewStatus && interpolatedTime > 0.5f) {
                            Log.d("interpolatedTime", interpolatedTime + "");
                            if(mImageView1.getVisibility() == View.VISIBLE){
                                mImageView1.setVisibility(View.GONE);
                                mImageView2.setVisibility(View.VISIBLE);
                            }else{
                                mImageView1.setVisibility(View.VISIBLE);
                                mImageView2.setVisibility(View.GONE);
                            }
                            mShouldUpdateViewStatus = false;
                        }
                    }
                });
                mRoot.startAnimation(rotateAnim);
            }
        });

        mImageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //翻转测试
                float cX = mRoot.getWidth() / 2.0f;
                float cY = mRoot.getHeight() / 2.0f;
                mShouldUpdateViewStatus = true;
                MyRotation rotateAnim = new MyRotation(cX, cY, MyRotation.ROTATE_INCREASE);
                rotateAnim.setInterpolatedTimeListener(new MyRotation.InterpolatedTimeListener() {
                    @Override
                    public void interpolatedTime(float interpolatedTime) {
                        if (mShouldUpdateViewStatus && interpolatedTime > 0.5f) {
                            Log.d("interpolatedTime", interpolatedTime + "");
                            if(mImageView1.getVisibility() == View.VISIBLE){
                                mImageView1.setVisibility(View.GONE);
                                mImageView2.setVisibility(View.VISIBLE);
                            }else{
                                mImageView1.setVisibility(View.VISIBLE);
                                mImageView2.setVisibility(View.GONE);
                            }
                            mShouldUpdateViewStatus = false;
                        }
                    }
                });
                mRoot.startAnimation(rotateAnim);
            }
        });
    }
}
