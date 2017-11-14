package com.cky.learnandroiddetails;

import android.app.Activity;
import android.os.SystemClock;
import android.test.ActivityInstrumentationTestCase2;

import com.cky.learnandroiddetails.FirstCodeSecondVersion.NotificationTest.NotificationTestMainActivity;
import com.cky.learnandroiddetails.FirstCodeSecondVersion.ParcelableTest.ParcelableTestMainActivity;
import com.cky.learnandroiddetails.FirstCodeSecondVersion.ServiceBestPractice.Main5Activity;
import com.cky.learnandroiddetails.FirstCodeSecondVersion.TakePhotoTest.TakePhotoMainActivity;
import com.cky.learnandroiddetails.RecyclerVIewItemViewTest.RecyclerVIewItemViewTestActivity;
import com.cky.learnandroiddetails.Scroller.ScrollerMainActivity;
import com.cky.learnandroiddetails.ViewDragHelperTest.ViewDragHelperTestMainActivity;
import com.cky.learnandroiddetails.ViewGroupAnimationTest.ViewGroupAnimationTestMainActivity;
import com.cky.learnandroiddetails.learnGcsSloopView.LearnGcsSloopViewMainActivity;

/**
 * 作者：cky
 * 时间：2016/9/28 16:43
 * 描述：
 * https://segmentfault.com/u/chriszou/articles
 */

public class MyTest extends ActivityInstrumentationTestCase2<LearnGcsSloopViewMainActivity> {

    private Activity mActivity;

    public MyTest() {
        super(LearnGcsSloopViewMainActivity.class);
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
