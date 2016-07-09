package com.cky.learnandroiddetails;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import java.util.HashSet;
import java.util.Set;

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

        /*
        String s1 = "nihao";
        String s2 = "nihao";
        String s3 = new String("nihao");
        //==  比较两对象在jvm中的地址
        Log.d(TAG, String.valueOf(s1 == s2)); //true s1 s2 都是字符串字面值的引用
        Log.d(TAG, String.valueOf(s1 == s3)); //false
        */

        Employee e1 = new Employee();
        Employee e2 = new Employee();
        e1.setId(100);
        e2.setId(100);
        //Log.d(TAG, String.valueOf(e1.equals(e2))); //false
        //如果employee对象 equal返回 true set 应该只存储一个对象
        Set<Employee> employees = new HashSet<Employee>();
        employees.add(e1);
        employees.add(e2);
        System.out.println("employees-->" + employees);

    }
}
