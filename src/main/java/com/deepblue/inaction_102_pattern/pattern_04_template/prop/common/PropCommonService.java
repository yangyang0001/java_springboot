package com.deepblue.inaction_102_pattern.pattern_04_template.prop.common;

import com.deepblue.inaction_102_pattern.pattern_04_template.common.PropGameService;
import com.deepblue.inaction_102_pattern.pattern_04_template.prop.dto.common.*;

public interface PropCommonService extends PropGameService {

    public PropInitResult initProp(PropInitParam propInitParam);

    public PropBeginResult beginProp(PropBeginParam propBeginParam);

    public PropEndResult endProp(PropEndParam propEndParam);

}
