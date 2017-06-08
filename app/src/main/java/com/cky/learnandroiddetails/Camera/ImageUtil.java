package com.cky.learnandroiddetails.Camera;

import android.graphics.Bitmap;
import android.graphics.Matrix;

/**
 * Created by cuikangyuan on 2017/6/8.
 */

public class ImageUtil {

    public static Bitmap getRotatedBitmap (Bitmap bitmap, float degree) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);

        Bitmap rotateBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, false);

        return rotateBitmap;
    }
}
