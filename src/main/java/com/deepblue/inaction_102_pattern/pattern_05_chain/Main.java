package com.deepblue.inaction_102_pattern.pattern_05_chain;

import com.alibaba.fastjson.JSON;

/**
 *
 */
public class Main {

    public static void main(String[] args) {

        MineParam mineParam = new MineParam(1L, "Yang", 0);

        ExecuteHandler handler0 = new FirstHandler();
        ExecuteHandler handler1 = new SecodHandler();
        handler0.setHandler(handler1);

        System.out.println("mineParam is :" + JSON.toJSONString(mineParam));
        handler0.execute(mineParam);
        System.out.println("mineParam is :" + JSON.toJSONString(mineParam));
    }
}
