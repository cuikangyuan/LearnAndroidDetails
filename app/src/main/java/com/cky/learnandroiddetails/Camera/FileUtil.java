package com.cky.learnandroiddetails.Camera;

import android.graphics.Bitmap;
import android.os.Environment;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by cuikangyuan on 2017/6/8.
 */

public class FileUtil {

    private static final String TAG = FileUtil.class.getSimpleName();
    private static final File parentPath = Environment.getExternalStorageDirectory();
    private static String storagePath = "";
    private static final String DST_FOLDER_NAME = "ckyCamera";

    /**
     * 初始化 保存路径
     * @return
     */
    private static String initPath() {
        if (storagePath.equals("")) {
            storagePath = parentPath.getAbsolutePath() + "/" + DST_FOLDER_NAME;
            File file = new File(storagePath);
            if (!file.exists()) {
                file.mkdir();
            }
        }

        return storagePath;
    }

    /**
     * 保存bitmap 到本地
     * @param bitmap
     */
    public static void saveBitmap(Bitmap bitmap) {
        String path = initPath();
        long dateTake = System.currentTimeMillis();
        String jpegName = path + "/" + dateTake + ".jpeg";

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(jpegName);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bufferedOutputStream);
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
