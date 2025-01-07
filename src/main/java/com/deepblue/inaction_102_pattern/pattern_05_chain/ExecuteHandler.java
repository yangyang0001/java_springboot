package com.deepblue.inaction_102_pattern.pattern_05_chain;

import lombok.Data;

@Data
public abstract class ExecuteHandler implements ExecuteInterface{

    protected ExecuteHandler handler;

    @Override
    public final void execute(MineParam mineParam) {
        doExecute(mineParam);
        if(this.getHandler() != null) {
            this.getHandler().execute(mineParam);
        }
    }

    public abstract void doExecute(MineParam mineParam);

}
