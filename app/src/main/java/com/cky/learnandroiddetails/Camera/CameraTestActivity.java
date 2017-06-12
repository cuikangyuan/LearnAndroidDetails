package com.cky.learnandroiddetails.Camera;


import android.graphics.Point;
import android.graphics.SurfaceTexture;
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

    CameraTextureView mCameraTextureView = null;

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
        Point point = DisplayUtil.getScreenMetrics(this);
        previewRate = DisplayUtil.getScreenRate(this);
        /*
        mCameraSurfaceView = (CameraSurfaceView) findViewById(R.id.camera_surfaceview);

        ViewGroup.LayoutParams layoutParams = mCameraSurfaceView.getLayoutParams();

        layoutParams.width = point.x;
        layoutParams.height = point.y;
        mCameraSurfaceView.setLayoutParams(layoutParams);
        */
        mImageButton = (ImageButton) findViewById(R.id.btn_shutter);
        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CameraInterface.getInstance().doTakePicture();
            }
        });

        mCameraTextureView = (CameraTextureView) findViewById(R.id.camera_textureview);
        ViewGroup.LayoutParams layoutParams = mCameraTextureView.getLayoutParams();

        layoutParams.width = point.x;
        layoutParams.height = point.y;
        mCameraTextureView.setLayoutParams(layoutParams);

    }

    @Override
    public void cameraHasOpened() {
        //1.
        //SurfaceHolder holder = mCameraSurfaceView.getHolder();
        //CameraInterface.getInstance().doStartPreview(holder, previewRate);
        //2.
        SurfaceTexture surfaceTexture = mCameraTextureView.getSurfaceTexture();
        CameraInterface.getInstance().doStartPreview(surfaceTexture, previewRate);
    }
}
