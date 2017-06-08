package com.cky.learnandroiddetails.Camera;

import android.app.ActivityManager;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.cky.learnandroiddetails.R;

public class CameraTestActivity extends AppCompatActivity implements CameraInterface.CamOpenOverCallback{

    CameraSurfaceView mCameraSurfaceView = null;
    ImageButton mImageButton;
    float previewRate = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Thread openThread = new Thread() {
            @Override
            public void run() {
                super.run();
                CameraInterface.getInstance().doOpenCamera(CameraTestActivity.this);
            }
        };
        openThread.start();
        setContentView(R.layout.activity_camera_test);

        initViews();
    }

    private void initViews () {
        mCameraSurfaceView = (CameraSurfaceView) findViewById(R.id.camera_surfaceview);
        mImageButton = (ImageButton) findViewById(R.id.btn_shutter);

        ViewGroup.LayoutParams layoutParams = mCameraSurfaceView.getLayoutParams();
        Point point = DisplayUtil.getScreenMetrics(this);

        layoutParams.width = point.x;
        layoutParams.height = point.y;

        previewRate = DisplayUtil.getScreenRate(this);
        mCameraSurfaceView.setLayoutParams(layoutParams);

        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CameraInterface.getInstance().doTakePicture();
            }
        });

    }

    @Override
    public void cameraHasOpened() {
        SurfaceHolder holder = mCameraSurfaceView.getHolder();
        CameraInterface.getInstance().doStartPreview(holder, previewRate);
    }
}
