package com.cky.learnandroiddetails.IntentTest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.cky.learnandroiddetails.R;

public class IntentTestAct1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_test_act1);


        findViewById(R.id.button).setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setAction("com.cky.learnandroiddetails.IntentTest.myaction1");

            intent.addCategory("com.cky.learnandroiddetails.IntentTest.mycategory1");

            Intent haha = Intent.createChooser(intent, "haha");
            startActivity(haha);
        });
    }
}
