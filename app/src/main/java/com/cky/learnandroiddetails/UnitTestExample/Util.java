package com.cky.learnandroiddetails.UnitTestExample;

import android.content.Context;

import java.io.FileOutputStream;

/**
 * 作者：cky
 * 时间：2016/11/26 16:56
 * 描述：
 */

public class Util {
    public static void writeConfiguration(Context ctx) {
        try (FileOutputStream openFileOutput =
                     ctx.openFileOutput("config.txt", Context.MODE_PRIVATE);) {

            openFileOutput.write("This is a test1.".getBytes());
            openFileOutput.write("This is a test2.".getBytes());
        } catch (Exception e) {
            // not handled
        }
    }
}
