package com.deepblue.inaction_102_pattern.pattern_06_decorator;

import lombok.*;

/**
 *
 */
@Data
public class SecodDecorator extends MineDecorator{

    @Override
    public void execute() {
        Component component = this.getComponent();
        this.secndDecorate(component);
        component.execute();
    }

    public void secndDecorate(Component component) {
        MineComponent mine = (MineComponent) component;
        mine.setCard("371523201001066253");
        mine.setMobile("18765829090");
    }

}
