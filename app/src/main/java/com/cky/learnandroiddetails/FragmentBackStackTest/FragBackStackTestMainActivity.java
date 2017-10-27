package com.cky.learnandroiddetails.FragmentBackStackTest;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.cky.learnandroiddetails.R;

public class FragBackStackTestMainActivity extends AppCompatActivity {

    FrameLayout mFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frag_back_stack_test_main);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                此时替换的时候如果没有调用addToBackStack，
                那么fragment2就会进入销毁状态 onDestroy onDestroyView onDetach就会被调用
                */
                replaceFragment(new Fragment3(), false);
            }
        });

        mFrameLayout = (FrameLayout) findViewById(R.id.frag_container);

        replaceFragment(new Fragment2(), false);
    }

    private void replaceFragment(Fragment fragment, boolean addToBackStack) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.frag_container, fragment);
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(null);
        }

        //supportFragmentManager.popBackStack();
        fragmentTransaction.commit();
    }

}
