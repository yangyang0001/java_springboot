package com.deepblue.inaction_00_spring.chapter_09_mine.example_003_transaction;

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
public class MineServiceImpl implements MineService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public long add0() {

        String sql = "insert into t_mine(name, password, age, gender) values (?, ?, ?, ?)";

        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(sql, new String[] {"id"});

            statement.setString(1, "zhangsan_add0");
            statement.setString(2, "123456_add0");
            statement.setInt(3, 30);
            statement.setInt(4, 1);

            return statement;
        }, holder);

        return holder.getKey().longValue();
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public long add1() {

        String sql = "insert into t_mine(name, password, age, gender) values (?, ?, ?, ?)";

        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {

            PreparedStatement statement = connection.prepareStatement(sql, new String[] {"id"});

            statement.setString(1, "zhangsan_add1");
            statement.setString(2, "123456_add1");
            statement.setInt(3, 30);
            statement.setInt(4, 1);

            return statement;
        }, holder);


        return holder.getKey().longValue();
    }
}
