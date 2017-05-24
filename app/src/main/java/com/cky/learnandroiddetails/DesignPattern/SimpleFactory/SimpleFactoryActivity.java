package com.cky.learnandroiddetails.DesignPattern.SimpleFactory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cky.learnandroiddetails.R;

public class SimpleFactoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_factory);

        init();
    }

    private void init() {
        try {

            Fruit fruit1 = FruitGardener.factory("apple");
            fruit1.grow();
            Fruit fruit2 = FruitGardener.factory("grape");
            fruit2.grow();
            Fruit fruit3 = FruitGardener.factory("strawberry");
            fruit3.grow();
            Fruit fruit4 = FruitGardener.factory("pineapple");
            fruit4.grow();
        } catch (BadFruitException e) {
            e.printStackTrace();
        }

    }
}
