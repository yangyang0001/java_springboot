package com.deepblue.inaction_102_pattern.pattern_01_factory;

/**
 *
 */
public class ConcreteFactoryA implements AbstractFactory{

    @Override
    public ConcreteProductA create() {
        ConcreteProductA productA = new ConcreteProductA();
        productA.setId(1L);
        productA.setName("ConcreteProductA");
        return productA;
    }
}
