package com.github.dhslrl321.pagination;

import com.github.dhslrl321.pagination.model.Todo;
import com.github.dhslrl321.pagination.model.TodoRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SqlQueryExecutor {
    private final JdbcTemplate jdbcTemplate;
    private final TodoRowMapper todoRowMapper = new TodoRowMapper();

    public List<Todo> query(String sql) {
        return jdbcTemplate.query(sql, todoRowMapper);
    }
}
