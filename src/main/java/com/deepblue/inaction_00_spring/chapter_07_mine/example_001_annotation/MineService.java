package com.deepblue.inaction_00_spring.chapter_07_mine.example_001_annotation;

import lombok.*;

/**
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MineService {

    @MineFieldAnno(value = "1000")
    private Long id;

    @MineFieldAnno(value = "zhangsan")
    private String name;

    @MineFieldAnno(value = "1")
    private Integer gender;

    @MineMethodAnno(value = true)
    public Long selectId(Long id) {
        return this.id;
    }

    @MineMethodAnno(value = false)
    public Long deleteId(Long id) {
        return this.id;
    }
}
