package com.github.dhslrl321.pagination.functionality;

import com.github.dhslrl321.pagination.model.Todo;
import com.github.dhslrl321.pagination.model.TodoJdbcTestHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class QueryFunctionality_Test {

    TodoJdbcTestHelper jdbc = new TodoJdbcTestHelper();

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

    @Test
    @DisplayName("단일 조회 쿼리도 가능")
    void name2() {
        String query =
                "SELECT * FROM simple_todos " +
                        "ORDER BY createdAt desc " +
                        "limit 1";

        Optional<Todo> actual = jdbc.querySingle(query);

        assertThat(actual.isPresent()).isTrue();
    }
}
