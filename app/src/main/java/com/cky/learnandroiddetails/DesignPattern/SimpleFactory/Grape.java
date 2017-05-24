package com.cky.learnandroiddetails.DesignPattern.SimpleFactory;

import android.util.Log;

import static com.cky.learnandroiddetails.DesignPattern.SimpleFactory.Constants.FRUIT_INTERFACE;

/**
 * Created by cuikangyuan on 2017/5/24.
 */

public class Grape implements Fruit {

    public boolean isSeedless() {
        return seedless;
    }

    public void setSeedless(boolean seedless) {
        this.seedless = seedless;
    }

    private boolean seedless;

    @Override
    public void plant() {
        Log.d(FRUIT_INTERFACE, "Grape plant");

    }

    @Override
    public void grow() {
        Log.d(FRUIT_INTERFACE, "Grape grow");

    }

    @Override
    public void harvest() {
        Log.d(FRUIT_INTERFACE, "Grape harvest");

    }
}
