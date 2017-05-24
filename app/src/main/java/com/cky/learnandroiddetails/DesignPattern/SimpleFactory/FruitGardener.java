package com.cky.learnandroiddetails.DesignPattern.SimpleFactory;

/**
 * Created by cuikangyuan on 2017/5/24.
 *
 * 简单工厂 模式
 */

public class FruitGardener {

    public static Fruit factory(String which) throws BadFruitException {
        if (which.equalsIgnoreCase("apple")) {
            return new Apple();
        }
        if (which.equalsIgnoreCase("grape")) {
            return new Grape();
        }
        if (which.equalsIgnoreCase("strawberry")) {
            return new StrawBerry();
        } else {
            throw new BadFruitException("bad request");
        }
    }


}
