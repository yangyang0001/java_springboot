package com.deepblue.inaction_102_pattern.pattern_06_decorator;

import com.alibaba.fastjson.JSON;
import lombok.*;

/**
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class MineComponent implements Component {

    // --------------------------------------- FirstDecorator Execute Begin ---------------------------------------
    private Long id;

    private String username;

    private String password;
    // --------------------------------------- FirstDecorator Execute End   ---------------------------------------

    // --------------------------------------- SecndDecorator Execute Begin ---------------------------------------
    private String card;

    private String mobile;
    // --------------------------------------- SecndDecorator Execute END   ---------------------------------------

    @Override
    public void execute() {
        System.out.println("MineComponent execute method invoke, the component json is :" + JSON.toJSONString(this));
    }
}
