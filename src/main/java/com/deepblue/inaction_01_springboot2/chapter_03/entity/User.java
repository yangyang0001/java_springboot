package com.deepblue.inaction_01_springboot2.chapter_03.entity;

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

    private long userId;

    private String username;

    private String password;
}
