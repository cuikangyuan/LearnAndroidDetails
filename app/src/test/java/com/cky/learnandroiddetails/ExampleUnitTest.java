package com.cky.learnandroiddetails;

import com.cky.learnandroiddetails.UnitTestExample.Calculator;

import junit.framework.Assert;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 * http://blog.csdn.net/pdskyzcc1/article/details/50953083
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testAdd() {
        double a = 1;
        double b = 2;

        Calculator mCalculator = new Calculator();
        double result = mCalculator.sum(a, b);
        Assert.assertEquals(result, 3.0);

    }

    @Test
    public void testAdd2() {
        /*
        * 验证函数是否被调用 包括参数是否一致
        * */
        Calculator mCalculator = mock(Calculator.class);
        mCalculator.sum(1, 2);

        verify(mCalculator).sum(1, 3);
    }


}