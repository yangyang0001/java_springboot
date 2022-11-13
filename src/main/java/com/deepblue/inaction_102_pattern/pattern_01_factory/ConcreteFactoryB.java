package com.deepblue.inaction_102_pattern.pattern_01_factory;

/**
 *
 */
public class ConcreteFactoryB implements AbstractFactory{

    @Override
    public ConcreteProductB create() {
        ConcreteProductB productB = new ConcreteProductB();
        productB.setId(2L);
        productB.setName("ConcreteProductB");
        return productB;
    }
}
