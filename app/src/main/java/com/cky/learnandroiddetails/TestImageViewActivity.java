package com.cky.learnandroiddetails;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class TestImageViewActivity extends AppCompatActivity {

    ImageView ivTestScaleType;
    TextView tvHelloEventBus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_image_view);

        ivTestScaleType = (ImageView) findViewById(R.id.ivTestScaleType);
        tvHelloEventBus = (TextView) findViewById(R.id.tvHelloEventBus);
        //ivTestScaleType.setImageResource(R.mipmap.chinese_400x300);

        /*
        *
        * 1.ImageView.ScaleType.CENTER
        * 按图片原来size居中显示，图片超过view 的尺寸，会截取图的居中部分显示
        *
        * 2.ImageView.ScaleType.CENTER_CROP
        * 按比例扩大图片size居中显示  使图片的长宽 大于等于view的尺寸
        *
        * 3.ImageView.ScaleType.CENTER_INSIDE
        * 将图片内容完整居中显示 按比例缩小图片size使 图片的长宽小于等于view的尺寸
        *
        * 4.ImageView.ScaleType.FIT_CENTER(居中显示) FIT_START(靠近开始部位显示) FIT_END(靠近结束部分显示)
        * 将图片按比例 扩大或缩小至view的尺寸
        *
        * 5.ImageView.ScaleType.FIT_XY
        * 不按比例缩放图片 使图片充满整个view
        *
        * */

        EventBus.getDefault().register(this);

        int resId = R.mipmap.chinese_400x300;
        String msg = "Hello EventBus";
        EventBus.getDefault().post(resId);
        EventBus.getDefault().post(msg);
        //ivTestScaleType.setScaleType(ImageView.ScaleType.FIT_XY);
        //EventBus.getDefault().post(R.mipmap.chinese_400x300);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void helloEventBus(int resId) {
        ivTestScaleType.setImageResource(resId);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void helloEventBus2(String msg) {
        tvHelloEventBus.setText(msg);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
