package com.cky.learnandroiddetails.UnitTestExample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cky.learnandroiddetails.R;
/*
* 参考http://stackoverflow.com/questions/2047261/using-android-test-framework
* */
public class TestUnitTestAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_unit_test);
    }
}
