package com.github.dhslrl321.pagination.persistence;

import com.github.dhslrl321.pagination.model.Todo;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TodoRepository {

    private final JdbcTemplate jdbcTemplate;
    private final TodoRowMapper todoRowMapper = new TodoRowMapper();

    public List<Todo> query(String sql) {
        return jdbcTemplate.query(sql, todoRowMapper);
    }

    public List<Todo> query(PageRequest pageRequest) {
        String pagingQuery = String.format("SELECT * FROM todos LIMIT %d OFFSET %d",
                pageRequest.getLimit(),
                pageRequest.getOffset());

        return jdbcTemplate.query(pagingQuery, todoRowMapper);
    }

    public void save(Todo ... todos){
        for (Todo todo : todos) {
            save(todo);
        }
    }

    public void save(List<Todo> todos){
        for (Todo todo : todos) {
            save(todo);
        }
    }

    public void save(Todo todo) {
        String sql = "INSERT INTO todos (id, title, content, status, category, createdAt, updatedAt, deletedAt, ownerId) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                todo.getId(),
                todo.getTitle(),
                todo.getContent(),
                todo.getStatus().name(),
                todo.getPriority().name(),
                todo.getCreatedAt(),
                todo.getUpdatedAt(),
                todo.getDeletedAt(),
                todo.getOwnerId()
        );
    }
}
