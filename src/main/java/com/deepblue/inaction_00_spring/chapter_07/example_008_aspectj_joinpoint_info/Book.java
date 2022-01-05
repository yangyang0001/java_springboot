package com.deepblue.inaction_00_spring.chapter_07.example_008_aspectj_joinpoint_info;

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

    /**
     * 书本Id
     */
    private long bookId;

    /**
     * 书名
     */
    private String bookName;

    /**
     * 价格 单位分
     */
    private long bookPrice;
}
