package com.deepblue.inaction_01_springboot2.chapter_05.dao;

import com.deepblue.inaction_01_springboot2.chapter_05.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Long getUserCount() {
        String sql = "select count(0) from user";
        Long count = jdbcTemplate.queryForObject(sql, Long.class);
        return count;
    }

    public User getUser(Long userId) {
        String sql = "select * from user where id = ?";
        User user = jdbcTemplate.queryForObject(sql, new UserRowMapper(), userId);
        return user;
    }

    public List<User> getUserListByDeptId(Long departmentId) {
        String sql = "select * from user where department_id = ?";
         List<User> userList = jdbcTemplate.query(sql, new UserRowMapper(), departmentId);
        return userList;
    }


    public static class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setName(rs.getString("name"));
            user.setDepartmentId(rs.getLong("department_id"));
            user.setCreateTime(rs.getDate("create_time"));
            return user;
        }
    }
}
