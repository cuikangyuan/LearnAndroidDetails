package com.cky.learnandroiddetails.PathTest;

import android.content.Context;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.cky.learnandroiddetails.R;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Array;

public class LearnPathMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path_test_main);


        Log.e("cky", "外置SD卡路径 = " + getExtendedMemoryPath(this));
        Log.e("cky", "内置SD卡路径 = " + Environment.getExternalStorageDirectory().getAbsolutePath());
        Log.e("cky", "手机内存根目录路径  = " + Environment.getDataDirectory().getParentFile().getAbsolutePath());

    }

    private String getExtendedMemoryPath(Context context) {

        StorageManager storageManager = (StorageManager) context.getSystemService(Context.STORAGE_SERVICE);
        Class<?> storageVolumeClazz = null;

        try {
            storageVolumeClazz = Class.forName("android.os.storage.StorageVolume");
            Method getVolumeList = storageManager.getClass().getMethod("getVolumeList");
            Method getPath = storageManager.getClass().getMethod("getPath");
            Method isRemovable = storageManager.getClass().getMethod("isRemovable");

            Object result = getVolumeList.invoke(storageManager);
            int length = java.lang.reflect.Array.getLength(result);

            for (int i = 0; i < length; i++) {
                Object storageVolumeElement = java.lang.reflect.Array.get(result, i);
                String path = (String) getPath.invoke(storageVolumeElement);
                boolean removable = (Boolean) isRemovable.invoke(storageVolumeElement);
                if (removable) {
                    return path;
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


        return null;
    }
}
