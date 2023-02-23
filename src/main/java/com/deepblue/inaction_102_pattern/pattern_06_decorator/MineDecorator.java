package com.deepblue.inaction_102_pattern.pattern_06_decorator;

import lombok.*;

/**
 *
 */
@Data
public abstract class MineDecorator implements Component {

    protected Component component;

    public abstract void execute();

}
