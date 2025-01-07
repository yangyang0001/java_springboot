package com.deepblue.inaction_102_pattern.pattern_04_template.service.prop.dice;

import com.deepblue.inaction_102_pattern.pattern_04_template.dto.prop.common.*;
import com.deepblue.inaction_102_pattern.pattern_04_template.dto.prop.dice.*;
import com.deepblue.inaction_102_pattern.pattern_04_template.service.prop.common.PropCommonServiceImpl;

/**
 *
 */
public class DicePropServiceImpl extends PropCommonServiceImpl implements DicePropService{

    @Override
    public PropInitResult doInit(PropInitParam propInitParam) {
        DiceInitParam initParam = (DiceInitParam) propInitParam;

        return new DiceInitResult();
    }

    @Override
    public PropBeginResult doBegin(PropBeginParam propBeginParam) {
        DiceBeginParam beginParam = (DiceBeginParam) propBeginParam;
        return new DiceBeginResult();
    }

    @Override
    public PropEndResult doEnd(PropEndParam propEndParam) {
        DiceEndParam endParam = (DiceEndParam) propEndParam;
        return new DiceEndResult();
    }

}
