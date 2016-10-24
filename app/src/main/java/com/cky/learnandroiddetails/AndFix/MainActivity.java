package com.cky.learnandroiddetails.AndFix;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alipay.euler.andfix.patch.PatchManager;
import com.cky.learnandroiddetails.R;

import java.io.File;
import java.io.IOException;

/**
 * 作者：cky
 * 时间：2016/10/24 10:44
 * 描述：
 */

public class MainActivity extends AppCompatActivity{

    Button mButton;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.and_fix_main_act);

        initView();

    }

    public void initView() {
        mButton = (Button) findViewById(R.id.button);

        mButton.setText("按钮");
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "bug已修复", Toast.LENGTH_LONG).show();
            }
        });
/*
        mButton.setText("bug修复");
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "bug修复", Toast.LENGTH_LONG).show();
            }
        });
*/
    }


}
