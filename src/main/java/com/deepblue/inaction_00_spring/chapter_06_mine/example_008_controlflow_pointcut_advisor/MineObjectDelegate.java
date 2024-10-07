package com.deepblue.inaction_00_spring.chapter_06_mine.example_008_controlflow_pointcut_advisor;

/**
 *
 */
public class MineObjectDelegate {

    private MineInterface mineInterface;

    public MineInterface getMineInterface() {
        return mineInterface;
    }

    public void setMineInterface(MineInterface mineInterface) {
        this.mineInterface = mineInterface;
    }

    public void service(String name) {
        mineInterface.greet2(name);
        mineInterface.serve2(name);
    }
}
