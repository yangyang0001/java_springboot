package com.deepblue.inaction_01_springboot2.chapter_12.entity;

import lombok.*;

/**
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class User {

    private Long userId;

    private String username;

    private String password;
}
