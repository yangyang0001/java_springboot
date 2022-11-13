package com.deepblue.inaction_102_pattern.pattern_05_chain;

import com.alibaba.fastjson.JSON;

/**
 *
 */
public class SecodHandler extends ExecuteHandler {

    @Override
    public void doExecute(MineParam mineParam) {
        mineParam.setCount(mineParam.getCount() + 1);
        System.out.println("SecodHandler execute invoke mineParam is :" + JSON.toJSONString(mineParam));
    }
}
