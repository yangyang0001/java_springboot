package com.deepblue.inaction_00_spring.chapter_06.example_008_control_flow_pointcut_advisor;

import com.deepblue.inaction_00_spring.chapter_06.Waiter;

/**
 *
 */
public class WatierDelegate {

    private Waiter waiter;

    public void service(String name) {
        waiter.greetTo(name);
        waiter.serveTo(name);
    }


    public Waiter getWaiter() {
        return waiter;
    }

    public void setWaiter(Waiter waiter) {
        this.waiter = waiter;
    }
}
