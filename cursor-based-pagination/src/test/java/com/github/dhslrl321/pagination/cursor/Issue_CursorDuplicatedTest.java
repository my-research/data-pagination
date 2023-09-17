package com.github.dhslrl321.pagination.cursor;

import com.github.dhslrl321.pagination.TodoDbTest;
import com.github.dhslrl321.pagination.model.Todo;
import com.github.dhslrl321.pagination.persistence.TodoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.github.dhslrl321.pagination.fixture.Fixtures.year23Month;
import static com.github.dhslrl321.pagination.model.Todo.todoOf;
import static org.assertj.core.api.Assertions.assertThat;

@TodoDbTest
public class Issue_CursorDuplicatedTest {

    @Autowired
    TodoRepository sut;

    @BeforeEach
    void setUp() {
        sut.save(
                todoOf(100, year23Month(1)),
                todoOf(200, year23Month(1)),
                todoOf(300, year23Month(1)),
                todoOf(400, year23Month(1)),
                todoOf(500, year23Month(1)),
                todoOf(600, year23Month(1))
        );
    }

    @Test
    @DisplayName("커서를 만들 때 커서의 중복성을 고려하지 않은 경우")
    void name() {
        List<Todo> firstPage = sut.query("SELECT * FROM todos ORDER BY createdAt, id LIMIT 3");
        Todo latestServed = firstPage.get(2);

        // 일반적인 커서 쿼리
        String sql = String.format("SELECT * FROM todos " +
                "WHERE id > %d AND createdAt > '%s' " +
                "ORDER BY createdAt LIMIT 3", latestServed.getId(), latestServed.getCreatedAt());

        List<Todo> secondPage = sut.query(sql);

        assertThat(secondPage).hasSize(0);
    }

    @Test
    @DisplayName("중복성이 고려된 커서")
    void name2() {
        List<Todo> firstPage = sut.query("SELECT * FROM todos ORDER BY createdAt, id LIMIT 3");
        Todo latestServed = firstPage.get(2);

        // 일반적인 커서 쿼리
        String sql = String.format("SELECT * FROM todos " +
                "WHERE (id > %d AND createdAt >= '%s') " +
                "ORDER BY createdAt LIMIT 3", latestServed.getId(), latestServed.getCreatedAt());

        List<Todo> secondPage = sut.query(sql);

        assertThat(secondPage).hasSize(0);
    }
}
