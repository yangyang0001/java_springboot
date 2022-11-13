package com.deepblue.inaction_102_pattern.pattern_01_factory;

import lombok.*;

/**
 *
 */
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractProduct {

    protected Long id;

    protected String name;
}
