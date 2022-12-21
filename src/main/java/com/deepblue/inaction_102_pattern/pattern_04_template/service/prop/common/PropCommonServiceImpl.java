package com.deepblue.inaction_102_pattern.pattern_04_template.service.prop.common;

import com.deepblue.inaction_102_pattern.pattern_04_template.dto.prop.common.*;
import com.deepblue.inaction_102_pattern.pattern_04_template.service.common.PropGameServiceImpl;

/**
 *
 */
public abstract class PropCommonServiceImpl extends PropGameServiceImpl implements PropCommonService {


    @Override
    public PropInitResult propInit(PropInitParam propInitParam) {
        return doInit(propInitParam);
    }

    @Override
    public PropBeginResult propBegin(PropBeginParam propBeginParam) {
        return doBegin(propBeginParam);
    }

    @Override
    public PropEndResult propEnd(PropEndParam propEndParam) {
        return doEnd(propEndParam);
    }

    public abstract PropInitResult doInit(PropInitParam propInitParam);

    public abstract PropBeginResult doBegin(PropBeginParam propBeginParam);

    public abstract PropEndResult doEnd(PropEndParam propEndParam);
}
