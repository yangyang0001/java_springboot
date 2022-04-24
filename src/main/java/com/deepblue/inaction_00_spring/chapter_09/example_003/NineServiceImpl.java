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
public class NineServiceImpl implements NineService{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Long saveNine() {
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

        return keyHolder.getKey().longValue();
    }

}
