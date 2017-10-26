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
                replaceFragment(new Fragment3(), true);
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
