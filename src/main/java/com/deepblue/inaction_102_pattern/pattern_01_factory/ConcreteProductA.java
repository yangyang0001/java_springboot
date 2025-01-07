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
public class ConcreteProductA extends AbstractProduct {

    private Long id;

    private String username;

    private String password;

}
