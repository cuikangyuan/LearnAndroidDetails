package com.cky.learnandroiddetails.FirstCodeSecondVersion.ParcelableTest;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import com.cky.learnandroiddetails.R;

public class ParcelableTestSecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcelable_test_main);


        Person person = getIntent().getParcelableExtra("person");
        Snackbar.make(findViewById(R.id.button), person.getName() + "-" + person.getAge(), Snackbar.LENGTH_SHORT).show();
    }
}
