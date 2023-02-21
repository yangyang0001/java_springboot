package com.deepblue.inaction_102_pattern.pattern_01_factory;

import lombok.*;

/**
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ConcreteProductB extends AbstractProduct {

    private Long id;

    private String username;

    private Integer gender;
    
}
