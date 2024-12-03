package com.deepblue.inaction_00_spring.chapter_09_mine.example_003_transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class ExecuteServiceImpl implements ExecuteService {

    @Autowired
    private MineService mineService;

    @Autowired
    private HineService hineService;


    @Override
    public String add00() {
        long mineId = mineService.add0();
        long hineId = hineService.add0();

        return "mineId = " + mineId + ", hineId = " + hineId;
    }

    @Override
    public String add01() {
        long mineId = mineService.add0();
        long hineId = hineService.add1();

        return "mineId = " + mineId + ", hineId = " + hineId;
    }

    @Override
    public String add10() {
        long mineId = mineService.add1();
        long hineId = hineService.add0();

        return "mineId = " + mineId + ", hineId = " + hineId;
    }

    @Override
    public String add11() {
        long mineId = mineService.add1();
        long hineId = hineService.add1();

        return "mineId = " + mineId + ", hineId = " + hineId;
    }
}
