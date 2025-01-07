package com.deepblue.inaction_01_springboot2.chapter_05.entity;

import lombok.*;

/**
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Department {

    private Long id;

    private String name;
}
