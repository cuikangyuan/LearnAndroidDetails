package com.cky.learnandroiddetails.actsince20161017;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.cky.learnandroiddetails.R;

/**
 * 作者：cky
 * 时间：2016/10/17 16:39
 * 描述：
 */

public class ExampleAct extends AppCompatActivity {

    private Button mButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.example_act);

        initView();
    }

    private void initView() {
/*
        mButton = (Button) findViewById(R.id.show_dialog_btn);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
*/
    }
}
