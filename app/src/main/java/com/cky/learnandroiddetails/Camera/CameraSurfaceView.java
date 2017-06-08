package com.cky.learnandroiddetails.Camera;

import android.content.Context;
import android.graphics.PixelFormat;
import android.util.AttributeSet;

import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by cuikangyuan on 2017/6/8.
 */

public class CameraSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    private static final String TAG = CameraSurfaceView.class.getSimpleName();

    CameraInterface mCameraInterface;
    Context mContext;
    SurfaceHolder mSurfaceHolder;

    public CameraSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContext = context;
        mSurfaceHolder = getHolder();
        mSurfaceHolder.setFormat(PixelFormat.TRANSPARENT);
        mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        mSurfaceHolder.addCallback(this);
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        CameraInterface.getInstance().doStopCamera();
    }
}
