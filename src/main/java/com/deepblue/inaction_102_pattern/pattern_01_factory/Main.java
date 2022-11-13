package com.deepblue.inaction_102_pattern.pattern_01_factory;

import com.alibaba.fastjson.JSON;

/**
 *
 */
public class Main {

    public static void main(String[] args) {

        AbstractFactory factoryA = new ConcreteFactoryA();
        AbstractProduct productA = factoryA.create();

        AbstractFactory factoryB = new ConcreteFactoryB();
        AbstractProduct productB = factoryB.create();

        System.out.println(JSON.toJSONString(productA));
        System.out.println(JSON.toJSONString(productB));

    }
}
