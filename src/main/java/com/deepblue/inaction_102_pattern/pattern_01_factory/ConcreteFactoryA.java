package com.deepblue.inaction_102_pattern.pattern_01_factory;

/**
 *
 */
public class ConcreteFactoryA extends AbstractFactory{


    @Override
    public AbstractProduct create() {
        return new ConcreteProductA(1L, "zhangsan", "123456");
    }
}
