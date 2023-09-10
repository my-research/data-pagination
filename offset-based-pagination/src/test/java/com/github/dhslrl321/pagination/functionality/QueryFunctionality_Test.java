package com.github.dhslrl321.pagination.functionality;

import com.github.dhslrl321.pagination.model.Todo;
import com.github.dhslrl321.pagination.model.TodoJdbcTestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class QueryFunctionality_Test {

    @Autowired
    JdbcTemplate jdbcTemplate;

    TodoJdbcTestHelper jdbc;

    @BeforeEach
    void setUp() {
        jdbc = new TodoJdbcTestHelper(jdbcTemplate);
    }

    @Test
    @DisplayName("TodoJdbcTestHelper 를 통해서 쿼리를 실행시키고 결과를 반환받을 수 있다")
    void name() {
        String query =
                "SELECT * FROM simple_todos " +
                        "ORDER BY createdAt desc " +
                        "limit 10";

        List<Todo> actual = jdbc.query(query);

        assertThat(actual).hasSize(10);
    }
}
