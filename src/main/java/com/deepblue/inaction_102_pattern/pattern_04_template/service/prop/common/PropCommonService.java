package com.deepblue.inaction_102_pattern.pattern_04_template.service.prop.common;

import com.deepblue.inaction_102_pattern.pattern_04_template.dto.prop.common.*;
import com.deepblue.inaction_102_pattern.pattern_04_template.service.common.PropGameService;

public interface PropCommonService extends PropGameService {

    public PropInitResult propInit(PropInitParam propInitParam);
    public PropBeginResult propBegin(PropBeginParam propBeginParam);
    public PropEndResult propEnd(PropEndParam propEndParam);

}
