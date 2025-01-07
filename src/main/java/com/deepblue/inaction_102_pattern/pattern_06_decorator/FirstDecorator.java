package com.deepblue.inaction_102_pattern.pattern_06_decorator;

import lombok.*;

/**
 *
 */
@Data
public class FirstDecorator extends MineDecorator {

    @Override
    public void execute() {
        Component component = this.getComponent();
        this.firstDecorate(component);
        component.execute();
    }

    public void firstDecorate(Component component) {
        MineComponent mine = (MineComponent) component;
        mine.setId(100L);
        mine.setUsername("zhangsan");
        mine.setPassword("123456");
    }
}
