package com.cky.learnandroiddetails.DesignPattern.SimpleFactory;

import android.util.Log;

import static com.cky.learnandroiddetails.DesignPattern.SimpleFactory.Constants.FRUIT_INTERFACE;

/**
 * Created by cuikangyuan on 2017/5/24.
 */

public class Apple implements Fruit {

    public int getTreeAge() {
        return mTreeAge;
    }

    public void setTreeAge(int treeAge) {
        mTreeAge = treeAge;
    }

    private int mTreeAge;

    @Override
    public void plant() {
        Log.d(FRUIT_INTERFACE, "apple plant");
    }

    @Override
    public void grow() {
        Log.d(FRUIT_INTERFACE, "apple grow");
    }

    @Override
    public void harvest() {
        Log.d(FRUIT_INTERFACE, "apple harvest");
    }
}
