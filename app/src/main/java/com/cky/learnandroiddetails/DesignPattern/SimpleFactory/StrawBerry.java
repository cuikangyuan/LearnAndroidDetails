package com.cky.learnandroiddetails.DesignPattern.SimpleFactory;

import android.util.Log;

import static com.cky.learnandroiddetails.DesignPattern.SimpleFactory.Constants.FRUIT_INTERFACE;

/**
 * Created by cuikangyuan on 2017/5/24.
 */

public class StrawBerry implements Fruit {

    @Override
    public void plant() {
        Log.d(FRUIT_INTERFACE, "StrawBerry plant");
    }

    @Override
    public void grow() {
        Log.d(FRUIT_INTERFACE, "StrawBerry grow");

    }

    @Override
    public void harvest() {
        Log.d(FRUIT_INTERFACE, "StrawBerry harvest");

    }
}
