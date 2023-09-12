package com.github.dhslrl321.pagination.model;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TodoJdbcTestHelper {

    private final JdbcTemplate jdbcTemplate;
    private final TodoRowMapper todoRowMapper = new TodoRowMapper();

    public Optional<Todo> querySingle(String sql) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, todoRowMapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        } catch (IncorrectResultSizeDataAccessException e) {
            throw new IllegalArgumentException("쿼리 결과가 여러개입니다. " + e.getMessage());
        }
    }

    public List<Todo> query(String sql) {
        return jdbcTemplate.query(sql, todoRowMapper);
    }

    public void save(Todo todo) {
        String sql = "INSERT INTO your_table_name (title, content, status, category, createdAt, updatedAt, deletedAt, ownerId) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                todo.getTitle(),
                todo.getContent(),
                todo.getStatus(),
                todo.getCategory(),
                todo.getCreatedAt(),
                todo.getUpdatedAt(),
                todo.getDeletedAt(),
                todo.getOwnerId()
        );
    }
}
