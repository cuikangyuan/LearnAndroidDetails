package com.cky.learnandroiddetails.HotFix;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cky.learnandroiddetails.IHelloHotFix;
import com.cky.learnandroiddetails.R;

import java.io.File;

import dalvik.system.DexClassLoader;

public class HotFixClassLoaderAct extends AppCompatActivity {

    private static final String TAG = HotFixClassLoaderAct.class.getSimpleName();
    private TextView mTextView;
    private Button mButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_fix_class_loader);
        mTextView = (TextView) findViewById(R.id.dex_file_text);
        mButton = (Button) findViewById(R.id.load_dex_file_btn);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadHotFixPack();
            }
        });
        //LogOutClassLoaderName();
    }

    private void LogOutClassLoaderName() {
        ClassLoader classLoader = HotFixClassLoaderAct.class.getClassLoader();

        while (classLoader != null) {

            Log.d(TAG, classLoader.toString());

            classLoader = classLoader.getParent();
        }
    }

    private void loadHotFixPack() {
        // 获取到包含 class.dex 的 jar 包文件
        final File jarFile =
                new File(Environment.getExternalStorageDirectory().getPath() + File.separator + "myfirstdex.jar");

        // 如果没有读权限,确定你在 AndroidManifest 中是否声明了读写权限
        Log.d(TAG, jarFile.canRead() + "");

        if (!jarFile.exists())
        {
            Log.e(TAG, "myfirstdex.jar not exists");
            return;
        }

        // getCodeCacheDir() 方法在 API 21 才能使用,实际测试替换成 getExternalCacheDir() 等也是可以的
        // 只要有读写权限的路径均可
        DexClassLoader dexClassLoader =
                new DexClassLoader(jarFile.getAbsolutePath(), getExternalCacheDir().getAbsolutePath(), null, getClassLoader());

        // 加载 HelloAndroid 类
        Class clazz = null;
        try {
            clazz = dexClassLoader.loadClass("com.cky.learnandroiddetails.HelloHotFix");
            //强转成 ISayHello, 注意 ISayHello 的包名需要和 jar 包中的
            IHelloHotFix iHelloHotFix = null;
            iHelloHotFix = (IHelloHotFix) clazz.newInstance();
            mTextView.setText(iHelloHotFix.say());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
