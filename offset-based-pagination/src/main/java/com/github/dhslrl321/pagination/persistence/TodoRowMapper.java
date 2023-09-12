package com.github.dhslrl321.pagination.persistence;

import com.github.dhslrl321.pagination.model.Priority;
import com.github.dhslrl321.pagination.model.Todo;
import com.github.dhslrl321.pagination.model.TodoStatus;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TodoRowMapper implements RowMapper<Todo> {

    @Override
    public Todo mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Todo.loadFromDb(
                rs.getLong("id"),
                rs.getString("title"),
                rs.getString("content"),
                TodoStatus.valueOf(rs.getString("status")),
                Priority.valueOf(rs.getString("category")),
                rs.getTimestamp("createdAt") == null ? null : rs.getTimestamp("createdAt").toLocalDateTime(),
                rs.getTimestamp("updatedAt") == null ? null : rs.getTimestamp("updatedAt").toLocalDateTime(),
                rs.getTimestamp("deletedAt") == null ? null : rs.getTimestamp("deletedAt").toLocalDateTime(),
                rs.getLong("ownerId")
        );
    }
}
