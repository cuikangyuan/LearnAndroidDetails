package com.cky.learnandroiddetails.SysInfoTest;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.cky.learnandroiddetails.R;

public class SysInfoTestMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sys_info_test_main);



        TextView textView = (TextView) findViewById(R.id.text1);


        StringBuilder stringBuilder = new StringBuilder();

        String board = Build.BOARD;
        String brand = Build.BRAND;

        String os_version = System.getProperty("os.version");
        String os_name = System.getProperty("os.name");

        stringBuilder.append("board : ");
        stringBuilder.append(board + "\n");
        stringBuilder.append("brand : ");
        stringBuilder.append(brand + "\n");
        stringBuilder.append("os_version : ");
        stringBuilder.append(os_version + "\n");
        stringBuilder.append("os_name : ");
        stringBuilder.append(os_name + "\n");

        textView.setText(stringBuilder);
    }
}
