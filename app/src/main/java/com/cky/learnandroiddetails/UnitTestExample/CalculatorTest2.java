package com.cky.learnandroiddetails.UnitTestExample;

import junit.framework.Assert;

/**
 * 作者：cky
 * 时间：2016/11/26 10:44
 * 描述：测试用例
 */

public class CalculatorTest2 {
    Calculator mCalculator = new Calculator();


    public void testAdd() {
        double a = 1;
        double b = 2;

        double result = mCalculator.sum(a, b);
        Assert.assertEquals(result, 3);

    }
}
