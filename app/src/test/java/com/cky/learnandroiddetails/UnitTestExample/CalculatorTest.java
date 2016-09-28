package com.cky.learnandroiddetails.UnitTestExample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 作者：cky
 * 时间：2016/9/28 11:18
 * 描述：
 */
public class CalculatorTest {

    private Calculator mCalculator;

    @Before
    public void setUp() throws Exception {
        mCalculator = new Calculator();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void sum() throws Exception {
        assertEquals(3d, mCalculator.sum(1d, 2d), 0);
    }

    @Test
    public void substract() throws Exception {
        assertEquals(4d, mCalculator.substract(5d, 1d), 0);
    }

    @Test
    public void divide() throws Exception {
        assertEquals(5d, mCalculator.divide(5d, 1d), 0);
    }

    @Test
    public void multiply() throws Exception {
        assertEquals(6d, mCalculator.multiply(2d, 3d), 0);
    }

}