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

    /*
    *
    *4.3.6
    *更改器方法可以执行错误检查 然而对域直接进行赋值将不会进行这些处理
    *如果需要返回一个可变对象的引用 应该对它进行克隆 clone
    *
    * hireDay.clone();
    *
    * 4.4.3
    * 可以认为静态方法是没有this参数(隐式参数)的方法
    *
    * 静态方法不能操作对象 所以不能在静态方法中访问实例域 但是静态方法可以访问自身类的静态域
    *
    * 异常 编译时异常 CheckedException 运行时异常RuntimeException
    *
    * 如果程序没有处理Checked异常 程序在编译时即发生错误无法编译 即 没有完善错误处理的代码根本没有机会被执行
    *
    * 处理方法:
    * 1 当前方法知道如何处理异常 使用try catch  处理异常
    * 2 当前方法不知道如何处理异常 在定义方法时声明抛出该异常
    *
    * 运行时异常由系统自动检测并交给缺省的异常处理程序 当然也可以显式的捕获它们
    *
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

        /*
        http://my.oschina.net/hanzhankang/blog/195529

        http://blog.163.com/chen_24_fei@yeah/blog/static/170115961201231443015473/

        Runtime Exception：
        在定义方法时不需要声明会抛出runtime exception； 在调用这个方法时不需要捕获这个runtime exception； runtime exception是从java.lang.RuntimeException或java.lang.Error类衍生出来的。 例如：nullpointexception，IndexOutOfBoundsException就属于runtime exception

        Exception:
        定义方法时必须声明所有可能会抛出的exception； 在调用这个方法时，必须捕获它的checked exception，不然就得把它的exception传递下去；exception是从java.lang.Exception类衍生出来的。例如：IOException，SQLException就属于Exception
        * */

    }
}
