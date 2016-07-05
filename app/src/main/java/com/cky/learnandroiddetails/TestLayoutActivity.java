package com.cky.learnandroiddetails;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

public class TestLayoutActivity extends AppCompatActivity {

    /*
    * 如果我们想手动强制让视图进行重绘 可以调用invalidate方法
    *
    *
    * setVisibility()、setEnabled()、setSelected()等方法内部其实也是
    * 通过调用invalidate方法来重绘的
    *
    * 调用invalidate之后 会走 performTraversals(视图绘制的入口)
    *
    * 然后重新执行 绘制流程
    *
    *
    * selectDrawable 控制背景图的改变
    *   -> invalidateSelf
    *       ->invalidateDrawable(进入重绘)
    *
    *   注
    *   invalidate 方法最终会调到performTraversals方法，但measure
    *   layout流程是不会重新执行的，因为视图没有强制重新测量的标志位
    *   而且大小也没有发生变化 所以这时只有draw流程会走
    *
    *   同时也可调用requestLayout使视图的绘制流程全部执行一遍
    * */
    private LinearLayout mainLayout;

    private static final String TAG = TestLayoutActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        /*
        mainLayout = (LinearLayout) findViewById(R.id.main_layout);
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View buttonLayout = layoutInflater.inflate(R.layout.button_layout, null);
        mainLayout.addView(buttonLayout);

        ViewParent viewParent = mainLayout.getParent();
        Log.d(TAG, "The parent of mainLayout is " + viewParent);
        */
    }
}
