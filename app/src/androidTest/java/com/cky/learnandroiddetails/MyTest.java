package com.cky.learnandroiddetails;

import android.app.Activity;
import android.os.SystemClock;
import android.test.ActivityInstrumentationTestCase2;

import com.cky.learnandroiddetails.Camera.CameraTestActivity;
import com.cky.learnandroiddetails.CopyOrWriteListLearn.CopyOrWriteListMainActivity;
import com.cky.learnandroiddetails.DesignPattern.SimpleFactory.SimpleFactoryActivity;
import com.cky.learnandroiddetails.Matrix.MatrixTestActivity;
import com.cky.learnandroiddetails.MultiFingerTouch.MultiFingerTouchActivity;
import com.cky.learnandroiddetails.RegionTest.RegionTestActivity;
import com.cky.learnandroiddetails.TestBitmapShader.BitmapShaderMainActivity;
import com.cky.learnandroiddetails.TestPair.TestPairMainActivity;
import com.cky.learnandroiddetails.popupWindow.PopWindowMainActivity;
import com.cky.learnandroiddetails.webViewTest.*;

/**
 * 作者：cky
 * 时间：2016/9/28 16:43
 * 描述：
 * https://segmentfault.com/u/chriszou/articles
 */

public class MyTest extends ActivityInstrumentationTestCase2<CameraTestActivity> {

    private Activity mActivity;

    public MyTest() {
        super(CameraTestActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mActivity = getActivity();

    }

    //运行的函数即可测试
    public void testTextView() {
        /*
        try {
            runTestOnUiThread(new Runnable() {
                @Override
                public void run() {

                    TextView textView = (TextView) mActivity.findViewById(R.id.text_view);
                    textView.setText("Hello World");

                }
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        */
        SystemClock.sleep(400000000);
    }
}
