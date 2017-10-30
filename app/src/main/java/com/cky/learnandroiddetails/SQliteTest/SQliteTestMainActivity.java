package com.cky.learnandroiddetails.SQliteTest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.cky.learnandroiddetails.R;

public class SQliteTestMainActivity extends AppCompatActivity {

    private MySQliteOpenHelper mMySQliteOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_test_main);

        //数据库版本1
        //mMySQliteOpenHelper = new MySQliteOpenHelper(this, "BookStore.db", null, 1);

        //数据库版本2
        mMySQliteOpenHelper = new MySQliteOpenHelper(this, "BookStore.db", null, 2);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMySQliteOpenHelper.getWritableDatabase();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
