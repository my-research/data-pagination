package com.github.dhslrl321.pagination.functionality;

import com.github.dhslrl321.pagination.model.Todo;
import com.github.dhslrl321.pagination.model.TodoJdbcTestHelper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class OffsetPagination_Test {

    TodoJdbcTestHelper jdbc = new TodoJdbcTestHelper();

    @Test
    void name() {
        int size = 3;
        int page = 1;

        List<Todo> actual = jdbc.query(String.format("SELECT * " +
                "FROM simple_todos " +
                "ORDER BY createdAt DESC " +
                "LIMIT %d " +
                "OFFSET %d", size, page));

        assertThat(actual).hasSize(3);
    }
}
