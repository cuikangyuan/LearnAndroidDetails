package com.cky.learnandroiddetails.Camera;

import android.hardware.Camera;

import java.util.Comparator;

/**
 * Created by cuikangyuan on 2017/6/6.
 */

public class CameraSizeComparator implements Comparator<Camera.Size> {
    @Override
    public int compare(Camera.Size lhs, Camera.Size rhs) {
        if (lhs.width == rhs.width) {
            return 0;
        } else if (lhs.width > lhs.width){
            return 1;
        } else {
            return -1;
        }
    }
}
