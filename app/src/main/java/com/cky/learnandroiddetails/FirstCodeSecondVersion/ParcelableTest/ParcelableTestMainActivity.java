package com.cky.learnandroiddetails.FirstCodeSecondVersion.ParcelableTest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.cky.learnandroiddetails.R;

public class ParcelableTestMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcelable_test_main);


        findViewById(R.id.button).setOnClickListener(v -> {

                Intent intent = new Intent(ParcelableTestMainActivity.this, ParcelableTestSecondActivity.class);
                Person person = new Person();
                person.setName("myname");
                person.setAge(321);

                intent.putExtra("person", person);

                startActivity(intent);

        });

    }
}
