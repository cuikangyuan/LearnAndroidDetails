package com.cky.learnandroiddetails.learnGcsSloopView;

/**
 * Created by cuikangyuan on 2017/2/4.
 */

public class PieData {

    private String mName;
    private float mValue;
    private float mPercentage;

    private int mColor = 0;
    private float mAngle = 0;

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public float getmValue() {
        return mValue;
    }

    public void setmValue(float mValue) {
        this.mValue = mValue;
    }

    public float getmPercentage() {
        return mPercentage;
    }

    public void setmPercentage(float mPercentage) {
        this.mPercentage = mPercentage;
    }

    public int getmColor() {
        return mColor;
    }

    public void setmColor(int mColor) {
        this.mColor = mColor;
    }

    public float getmAngle() {
        return mAngle;
    }

    public void setmAngle(float mAngle) {
        this.mAngle = mAngle;
    }

    public PieData(String name, float value) {
        mName = name;
        mValue = value;
    }

    public PieData() {

    }
}
