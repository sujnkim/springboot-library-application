package com.group.libraryapp.repository.user;

import org.springframework.jdbc.core.JdbcTemplate;

public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public boolean isUserNotExist(long id) {
        String readSql = "SELECT * FROM user WHERE id = ?";
        return jdbcTemplate.query(readSql, (rs, rowNum) -> 0, id).isEmpty();
    }

    public void updateUserName(String name, long id) {
        String sql = "UPDATE user SET name = ? WHERE id = ?";
        jdbcTemplate.update(sql, name, id);
    }


    public boolean isUserNotExist(String name) {
        String readSql = "SELECT * FROM user WHERE name = ?";
        return jdbcTemplate.query(readSql, (rs, rowNum) -> 0, name).isEmpty();
    }

    public void deleteUser(String name) {
        String sql = "DELETE FROM user WHERE name = ?";
        jdbcTemplate.update(sql, name);
    }

}

