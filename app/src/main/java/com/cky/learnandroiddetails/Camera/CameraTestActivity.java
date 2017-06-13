package com.cky.learnandroiddetails.Camera;


import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.cky.learnandroiddetails.R;

public class CameraTestActivity extends AppCompatActivity implements CameraInterface.CamOpenOverCallback{

    int DST_CENTER_RECT_WIDTH = 200;
    int DST_CENTER_RECT_HEIGHT = 200;

    CameraSurfaceView mCameraSurfaceView = null;
    ImageButton mImageButton;
    float previewRate = -1;

    CameraTextureView mCameraTextureView = null;
    MaskView mMaskView = null;

    Point rectPictureSize = null;

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

        mCameraSurfaceView = (CameraSurfaceView) findViewById(R.id.camera_surfaceview);

        ViewGroup.LayoutParams layoutParams = mCameraSurfaceView.getLayoutParams();

        layoutParams.width = point.x;
        layoutParams.height = point.y;
        mCameraSurfaceView.setLayoutParams(layoutParams);

        mMaskView = (MaskView) findViewById(R.id.mask_view);

        mImageButton = (ImageButton) findViewById(R.id.btn_shutter);
        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //CameraInterface.getInstance().doTakePicture();

                if (rectPictureSize == null) {
                    rectPictureSize = createCenterPictureRect(
                            DisplayUtil.dip2px(CameraTestActivity.this, DST_CENTER_RECT_WIDTH),
                            DisplayUtil.dip2px(CameraTestActivity.this, DST_CENTER_RECT_HEIGHT)
                    );
                }

                CameraInterface.getInstance().doTakePicture(rectPictureSize.x, rectPictureSize.y);
            }
        });
        /*
        mCameraTextureView = (CameraTextureView) findViewById(R.id.camera_textureview);
        ViewGroup.LayoutParams layoutParams = mCameraTextureView.getLayoutParams();

        layoutParams.width = point.x;
        layoutParams.height = point.y;
        mCameraTextureView.setLayoutParams(layoutParams);
        */
    }

    @Override
    public void cameraHasOpened() {
        //1.
        SurfaceHolder holder = mCameraSurfaceView.getHolder();
        CameraInterface.getInstance().doStartPreview(holder, previewRate);
        //2.
        //SurfaceTexture surfaceTexture = mCameraTextureView.getSurfaceTexture();
        //CameraInterface.getInstance().doStartPreview(surfaceTexture, previewRate);

        if (mMaskView != null) {
            Rect screenCenterRect = createCenterScreenRect(
                    DisplayUtil.dip2px(this, DST_CENTER_RECT_WIDTH),
                    DisplayUtil.dip2px(this, DST_CENTER_RECT_HEIGHT)
                    );

            mMaskView.setCenterRect(screenCenterRect);
        }
    }

    /**生成拍照后图片的中间矩形的宽度和高度
     * @param w 屏幕上的矩形宽度，单位px
     * @param h 屏幕上的矩形高度，单位px
     * @return
     */
    private Point createCenterPictureRect(int w, int h) {
        int wScreen = DisplayUtil.getScreenMetrics(this).x;
        int hScreen = DisplayUtil.getScreenMetrics(this).y;
        //因为图片旋转了，所以此处宽高换位
        int wSavePicture = CameraInterface.getInstance().doGetPrictureSize().y;
        int hSavePicture = CameraInterface.getInstance().doGetPrictureSize().x;

        float wRate = (float)(wSavePicture) / (float) (wScreen);
        float hRate = (float)(hSavePicture) / (float) (hScreen);
        float rate = (wRate <= hRate) ? wRate : hRate;

        int wRectPicture = (int) (w * wRate);
        int hRectPicture = (int) (h * wRate);

        return new Point(wRectPicture, hRectPicture);
    }
    /**
     * 生成屏幕中间的矩形
     * @param w 目标矩形的宽度,单位px
     * @param h 目标矩形的高度,单位px
     * @return
     */
    private Rect createCenterScreenRect(int w, int h) {
        int x1 = DisplayUtil.getScreenMetrics(this).x / 2 - w / 2;
        int y1 = DisplayUtil.getScreenMetrics(this).y / 2 - h / 2;

        int x2 = x1 + w;
        int y2 = y1 + h;

        return new Rect(x1, y1, x2, y2);
    }
}
