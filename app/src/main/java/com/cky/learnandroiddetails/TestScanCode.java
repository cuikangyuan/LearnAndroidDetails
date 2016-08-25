package com.cky.learnandroiddetails;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.io.IOException;

public class TestScanCode extends AppCompatActivity {

    private Button mScanCodeBtn;

    private Button mScanCodeFromImgBtn;

    private static final int REQUEST_CODE = 991;
    private static final int REQUEST_IMAGE = 992;

    private static final String TAG = TestScanCode.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_scan_code);

        mScanCodeBtn = (Button) findViewById(R.id.btnScanCode);
        mScanCodeFromImgBtn = (Button) findViewById(R.id.btnScanCodeFromImg);

        mScanCodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //openFlashlight();
                Intent intent = new Intent(TestScanCode.this, CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        mScanCodeFromImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_IMAGE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE) {

            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(this, "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(TestScanCode.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        } else if(requestCode == REQUEST_IMAGE) {
            if (null != data) {
                Uri uri = data.getData();

                    Log.d(TAG, "uri -->" + uri.getPath());

                    CodeUtils.analyzeBitmap(ImageUtil.getImageAbsolutePath(TestScanCode.this, uri), new CodeUtils.AnalyzeCallback() {
                        @Override
                        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                            Toast.makeText(TestScanCode.this, "解析结果:" + result, Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onAnalyzeFailed() {
                            Toast.makeText(TestScanCode.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                        }
                    });

            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 打开闪光灯
     */
    public void openFlashlight() {
        android.hardware.Camera camera = android.hardware.Camera.open();
        android.hardware.Camera.Parameters parameters = camera.getParameters();
        parameters.setFlashMode(android.hardware.Camera.Parameters.FLASH_MODE_TORCH);
        camera.setParameters(parameters);
    }

    /**
     * 关闭散光灯
     */
    public void closeFlashlight() {
        android.hardware.Camera camera = android.hardware.Camera.open();
        android.hardware.Camera.Parameters parameters = camera.getParameters();
        parameters.setFlashMode(android.hardware.Camera.Parameters.FLASH_MODE_OFF);
        camera.setParameters(parameters);
        camera.release();
    }

    @Override
    protected void onDestroy() {
        //closeFlashlight();
        super.onDestroy();
    }
}
