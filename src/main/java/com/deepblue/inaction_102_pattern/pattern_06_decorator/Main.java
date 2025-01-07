package com.deepblue.inaction_102_pattern.pattern_06_decorator;

/**
 *
 */
public class Main {

    public static void main(String[] args) {

        MineComponent component = new MineComponent();

        FirstDecorator decorator1 = new FirstDecorator();
        SecodDecorator decorator2 = new SecodDecorator();

        decorator1.setComponent(component);
        decorator2.setComponent(component);
        decorator1.execute();
        decorator2.execute();
    }

}
