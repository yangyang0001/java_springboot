package com.deepblue.inaction_102_pattern.pattern_05_chain;

import lombok.*;

/**
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class MineParam {

    private Long id;

    private String username;

    private long count;
}
