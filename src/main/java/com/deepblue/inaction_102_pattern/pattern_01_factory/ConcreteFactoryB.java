package com.deepblue.inaction_102_pattern.pattern_01_factory;

/**
 *
 */
public class ConcreteFactoryB extends AbstractFactory{


    @Override
    public AbstractProduct create() {
        return new ConcreteProductB(1L, "maosan", 1);
    }
}
