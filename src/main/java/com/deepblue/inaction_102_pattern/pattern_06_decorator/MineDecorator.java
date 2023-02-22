package com.deepblue.inaction_102_pattern.pattern_06_decorator;

import lombok.*;

/**
 *
 */
@Data
public abstract class MineDecorator implements ComponentHandler {

    protected ComponentHandler component;

    public abstract void execute();

}
