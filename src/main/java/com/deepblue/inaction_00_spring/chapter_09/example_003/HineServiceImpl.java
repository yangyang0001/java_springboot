package com.deepblue.inaction_00_spring.chapter_09.example_003;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;

/**
 *
 */
@Service
public class HineServiceImpl implements HineService {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    private NineService nineService;

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public Long saveMine0() {
        String sql = "insert into t_mine(name, password, age, gender) values(?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(sql, new String[]{"id"});
            statement.setString(1, "mine_zhangsan_0");
            statement.setString(2, "12345678");
            statement.setInt(3, 22);
            statement.setInt(4, 1);
            return statement;
        }, keyHolder);

        Long mineId0 = keyHolder.getKey().longValue();
        Long nineId0 = nineService.saveNine0();
//        Long mineId1 = saveMine1();

//        System.out.println("HineService saveMine0 method invoke, mineId0 = " + mineId0 + ", mineId1 = " + mineId1);
        System.out.println("HineService saveMine0 method invoke, mineId0 = " + mineId0 + ", nineId0 = " + nineId0);

        return mineId0;
    }

    @Override
//    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public Long saveMine1() {
        String sql = "insert into t_mine(name, password, age, gender) values(?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(sql, new String[]{"id"});
            statement.setString(1, "mine_zhangsan_1");
            statement.setString(2, "12345678");
            statement.setInt(3, 22);
            statement.setInt(4, 1);
            return statement;
        }, keyHolder);

        int a = 1 / 0;

        return keyHolder.getKey().longValue();
    }


}
