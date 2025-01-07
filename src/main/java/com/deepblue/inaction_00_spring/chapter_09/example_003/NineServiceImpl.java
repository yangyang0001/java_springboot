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
public class NineServiceImpl implements NineService{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
//    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public Long saveNine0() {
        String sql = "insert into t_nine(name, password, age, gender) values(?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(sql, new String[]{"id"});
            statement.setString(1, "lisi");
            statement.setString(2, "66666666");
            statement.setInt(3, 10);
            statement.setInt(4, 1);
            return statement;
        }, keyHolder);

        int i= 1 / 0;

        return keyHolder.getKey().longValue();
    }

}
