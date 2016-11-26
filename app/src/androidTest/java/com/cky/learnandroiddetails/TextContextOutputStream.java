package com.cky.learnandroiddetails;

import android.content.Context;

import com.cky.learnandroiddetails.UnitTestExample.Util;

import org.junit.Before;
import org.junit.Test;

import java.io.FileOutputStream;

import static android.support.test.espresso.core.deps.guava.collect.Range.atLeast;
import static org.hamcrest.CoreMatchers.any;

/**
 * 作者：cky
 * 时间：2016/11/26 16:58
 * 描述：
 */

public class TextContextOutputStream {
    Context context;

    FileOutputStream fileOutputStream;

    @Before
    public void init(){

    }

    @Test
    public void writeShouldWriteTwiceToFileSystem() {

    }
}
