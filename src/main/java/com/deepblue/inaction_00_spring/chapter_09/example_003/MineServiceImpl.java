package com.deepblue.inaction_00_spring.chapter_09.example_003;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;

/**
 *
 */
@Service
public class MineServiceImpl implements MineService{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
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

        return keyHolder.getKey().longValue();
    }

    @Override
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

        return keyHolder.getKey().longValue();
    }

    @Override
    public String test_same_00() {
        Long mineId0 = saveMine0();
        Long mineId1 = saveMine1();
        System.out.println("mineId0 = " + mineId0 + ", mineId1 = " + mineId1);

        Integer value = 1 / 0;
        return "success";
    }

}
