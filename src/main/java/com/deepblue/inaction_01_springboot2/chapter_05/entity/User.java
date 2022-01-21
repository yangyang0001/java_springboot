package com.deepblue.inaction_01_springboot2.chapter_05.entity;

import lombok.*;

import java.util.Date;

/**
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class User {

    private Long id;

    private String name;

    private Long departmentId;

    private Date createTime;

}
