package com.github.dhslrl321.pagination.model;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@RequiredArgsConstructor
public class TodoJdbcTestHelper {

    private final JdbcTemplate jdbcTemplate;
    private final TodoRowMapper todoRowMapper = new TodoRowMapper();

    public Todo querySingle() {
        return null;
    }

    public List<Todo> query(String sql) {
        return jdbcTemplate.query(sql, todoRowMapper::mapRow);
    }
}
