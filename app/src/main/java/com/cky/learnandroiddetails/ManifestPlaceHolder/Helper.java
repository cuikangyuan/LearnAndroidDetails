package com.cky.learnandroiddetails.ManifestPlaceHolder;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.cky.learnandroiddetails.MyApplication;

/**
 * Created by cuikangyuan on 2017/5/26.
 */

public class Helper {

    public static final String A_HOST = "a_host";
    public static final String B_HOST = "b_host";

    public static String getManifestPlaceHolder(String key) {

        PackageManager packageManager = MyApplication.getContext().getPackageManager();

        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(
                    MyApplication.getContext().getPackageName(),
                    PackageManager.GET_META_DATA
            );

            String s = applicationInfo.metaData.getString(key);
            return s;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
