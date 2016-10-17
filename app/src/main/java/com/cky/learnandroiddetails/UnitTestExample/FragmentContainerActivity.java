package com.cky.learnandroiddetails.UnitTestExample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;

/**
 * 作者：cky
 * 时间：2016/10/9 09:51
 * 描述：
 */

public class FragmentContainerActivity extends FragmentActivity {

    private static final int CONTAINER_ID = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void addFragment(Fragment fragment, String tag) {
        getSupportFragmentManager().beginTransaction()
                //.add(CONTAINER_ID, fragment, tag)
                .commit();
    }
}
