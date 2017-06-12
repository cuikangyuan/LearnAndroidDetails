package com.cky.learnandroiddetails.Camera;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;

import java.io.IOException;
import java.util.List;

/**
 * Created by cuikangyuan on 2017/6/6.
 */

public class CameraInterface {

    private static final String TAG = CameraInterface.class.getSimpleName();
    private Camera mCamera;
    private Camera.Parameters mParameters;
    private boolean isPreviewing = false;
    private float mPreviewRate = -1f;
    private static CameraInterface mCameraInterface;

    public interface CamOpenOverCallback {
        public void cameraHasOpened();
    }

    private CameraInterface() {

    }

    public static synchronized CameraInterface getInstance() {
        if (mCameraInterface == null) {
            mCameraInterface = new CameraInterface();
        }

        return mCameraInterface;

    }

    /**
     * 打开Camera
     *
     * @param callback
     */
    public void doOpenCamera(CamOpenOverCallback callback) {
        mCamera = Camera.open();
        callback.cameraHasOpened();
    }

    /**
     * 开启预览
     *使用Surfaceview开启预览
     * @param holder
     * @param previewRate
     */
    public void doStartPreview(SurfaceHolder holder, float previewRate) {
        if (isPreviewing && mCamera != null) {
            mCamera.stopPreview();
            return;
        }

        if (mCamera != null) {
            try {
                mCamera.setPreviewDisplay(holder);
            } catch (IOException e) {
                e.printStackTrace();
            }
            initCamera(previewRate);
        }
    }

    /**
     * 使用surfaceTexture开启预览
     * @param surfaceTexture
     * @param previewRate
     */
    public void doStartPreview(SurfaceTexture surfaceTexture, float previewRate) {
        if (isPreviewing && mCamera != null) {
            mCamera.stopPreview();
            return;
        }

        if (mCamera != null) {
            try {
                mCamera.setPreviewTexture(surfaceTexture);
            } catch (IOException e) {
                e.printStackTrace();
            }
            initCamera(previewRate);
        }
    }

    private void initCamera(float previewRate) {
        if (mCamera != null) {
            mParameters = mCamera.getParameters();
            mParameters.setPictureFormat(PixelFormat.JPEG);

            Camera.Size pictureSize = CamParaUtil.getPropPreviewOrPictureSize(
                    mParameters.getSupportedPictureSizes(),
                    previewRate,
                    800);

            mParameters.setPictureSize(pictureSize.width, pictureSize.height);

            Camera.Size previewSize = CamParaUtil.getPropPreviewOrPictureSize(
                    mParameters.getSupportedPreviewSizes(),
                    previewRate,
                    800);

            mParameters.setPreviewSize(previewSize.width, previewSize.height);

            mCamera.setDisplayOrientation(90);

            List<String> supportedFocusModes = mParameters.getSupportedFocusModes();

            if (supportedFocusModes.contains("continous-video")) {
                mParameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO);
            }

            mCamera.setParameters(mParameters);
            mCamera.startPreview();

            isPreviewing = true;
            mPreviewRate = previewRate;

            mParameters = mCamera.getParameters();
            Log.i(TAG, "最终设置:PreviewSize--With = " + mParameters.getPreviewSize().width
                    + "Height = " + mParameters.getPreviewSize().height);
            Log.i(TAG, "最终设置:PictureSize--With = " + mParameters.getPictureSize().width
                    + "Height = " + mParameters.getPictureSize().height);
        }
    }

    /**
     * 停止预览 释放Camera
     */
    public void doStopCamera() {
        if (null != mCamera) {
            mCamera.setPreviewCallback(null);
            mCamera.stopPreview();
            isPreviewing = false;
            mCamera.release();
            mCamera = null;
        }
    }

    /**
     * 拍照
     */
    public void doTakePicture() {
        if (isPreviewing && (mCamera != null)) {
            mCamera.takePicture(mShutterCallback, null, mJpegPictureCallback);
        }
    }

    //快门按下的回调 在此处可以设置播放特定相机快门声音 默认有快门声音
    Camera.ShutterCallback mShutterCallback = new Camera.ShutterCallback() {
        @Override
        public void onShutter() {

        }
    };

    //拍摄的未压缩原数据的回调 可以为null
    Camera.PictureCallback mRawCallback = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {

        }
    };

    //对jpeg图像数据的回调
    Camera.PictureCallback mJpegPictureCallback = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            Bitmap bitmap = null;
            if (data != null) {
                bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);

                mCamera.stopPreview();
                isPreviewing = false;
            }
            //保存图片到本地
            if (bitmap != null) {
                //设置FOCUS_MODE_CONTINUOUS_VIDEO)之后，myParam.set("rotation", 90)失效。
                //图片竟然不能旋转了，故这里要旋转下
                Bitmap rotateBitmap = ImageUtil.getRotatedBitmap(bitmap, 90.0f);
                FileUtil.saveBitmap(rotateBitmap);
            }

            mCamera.startPreview();
            isPreviewing = true;
        }
    };
}
