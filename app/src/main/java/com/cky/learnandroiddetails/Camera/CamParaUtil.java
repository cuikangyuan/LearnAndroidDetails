package com.cky.learnandroiddetails.Camera;

import android.hardware.Camera;

import java.util.List;

/**
 * Created by cuikangyuan on 2017/6/6.
 */

public class CamParaUtil {

    private static CameraSizeComparator sizeComparator = new CameraSizeComparator();

    public static boolean isRateEqual(Camera.Size size, float rate) {
        float r = (float) size.width / (float) size.height;

        if (Math.abs(r - rate) <= 0.03) {
            return true;
        } else {
            return false;
        }
    }
/*
    public static Camera.Size getPropPreviewOrPictureSize(
            List<Camera.Size> list,
            float rate,
            int minWidth) {

        int i = 0;
        for (Camera.Size s : list) {
            if (s.width >= minWidth && isRateEqual(s, rate)) {
                break;
            }
            i++;
        }

        if (i == list.size()) {
            i = 0;//如果没找到  就选最小Size
        }

        return list.get(i);
    }
*/
    //以height做判断
    public static Camera.Size getPropPreviewOrPictureSize(
            List<Camera.Size> list,
            float rate,
            int minHeight) {

        int i = 0;
        for (Camera.Size s : list) {
            if (s.height >= minHeight && isRateEqual(s, rate)) {
                break;
            }
            i++;
        }

        if (i == list.size()) {
            i = 0;//如果没找到  就选最小Size
        }

        return list.get(i);
    }
}
