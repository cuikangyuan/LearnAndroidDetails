package com.cky.learnandroiddetails.ViewScreenShot;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cky.learnandroiddetails.R;

public class VIewScreenShotMainActivity extends AppCompatActivity {
    private RelativeLayout mLayoutSource;
    private Button mButtonGetShot;
    private Button mButtonChange;
    private ImageView mImageResult;
    private ImageView mImageSource;
    private TextView mTextViewSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_screen_shot_main);

        mLayoutSource = (RelativeLayout) findViewById(R.id.layout_source);
        mButtonGetShot = (Button) findViewById(R.id.btn_getshot);
        mButtonChange = (Button) findViewById(R.id.btn_change_picture);
        mImageResult = (ImageView) findViewById(R.id.img_result);
        mImageSource = (ImageView) findViewById(R.id.image_source);
        mTextViewSource = (TextView) findViewById(R.id.text_source);

        mButtonGetShot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLayoutSource.setDrawingCacheEnabled(true);
                Bitmap tBitmap = mLayoutSource.getDrawingCache();
                // 拷贝图片，否则在setDrawingCacheEnabled(false)以后该图片会被释放掉
                tBitmap = tBitmap.createBitmap(tBitmap);
                mLayoutSource.setDrawingCacheEnabled(false);
                if (tBitmap != null) {
                    mImageResult.setImageBitmap(tBitmap);
                    Toast.makeText(getApplicationContext(), "获取成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "获取失败", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mButtonChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mImageSource.setImageResource(R.mipmap.test2);
                mTextViewSource.setText("test2");
            }
        });

    }
}
