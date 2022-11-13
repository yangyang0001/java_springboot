package com.deepblue.inaction_102_pattern.pattern_04_template.prop.common;

import com.deepblue.inaction_102_pattern.pattern_04_template.common.PropGameServiceImpl;
import com.deepblue.inaction_102_pattern.pattern_04_template.prop.dto.common.*;

/**
 *
 */
public abstract class PropCommonServiceImpl extends PropGameServiceImpl implements PropCommonService {

    @Override
    public PropInitResult initProp(PropInitParam propInitParam) {
        return doInit(propInitParam);
    }

    @Override
    public PropBeginResult beginProp(PropBeginParam propBeginParam) {
        return doBegin(propBeginParam);
    }

    @Override
    public PropEndResult endProp(PropEndParam propEndParam) {
        return doEnd(propEndParam);
    }

    public abstract PropInitResult doInit(PropInitParam propInitParam);
    public abstract PropBeginResult doBegin(PropBeginParam propBeginParam);
    public abstract PropEndResult doEnd(PropEndParam propEndParam);
}
