package com.deepblue.inaction_01_springboot2.chapter_11.entity;

import lombok.*;

/**
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Book {

    private Long id;

    private String name;

    private String author;
}
