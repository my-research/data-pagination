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
public class CursorBasedPaginationTest {

    @Autowired
    TodoRepository sut;

    @BeforeEach
    void setUp() {
        sut.save(
                todoOf(100, year23Month(1)),
                todoOf(200, year23Month(2)),
                todoOf(300, year23Month(3)),
                todoOf(400, year23Month(4)),
                todoOf(500, year23Month(5)),
                todoOf(600, year23Month(6))
        );
    }

    @Test
    @DisplayName("cursor 기반 페이징은 클라이언트에게 마지막으로 제공된 데이터를 기준으로 커서를 만든다")
    void name() {
        List<Todo> firstPage = sut.query("SELECT * FROM todos ORDER BY createdAt, id LIMIT 3");

        assertThat(firstPage).hasSize(3);
        assertThat(firstPage.get(0).getId()).isEqualTo(100);
        assertThat(firstPage.get(1).getId()).isEqualTo(200);
        assertThat(firstPage.get(2).getId()).isEqualTo(300);

        Todo latestServed = firstPage.get(2);

        String sql = String.format("SELECT * FROM todos " +
                "WHERE id > %d AND createdAt > '%s' " +
                "ORDER BY createdAt LIMIT 3", latestServed.getId(), latestServed.getCreatedAt());

        List<Todo> secondPage = sut.query(sql);

        assertThat(secondPage).hasSize(3);
        assertThat(secondPage.get(0).getId()).isEqualTo(400);
        assertThat(secondPage.get(1).getId()).isEqualTo(500);
        assertThat(secondPage.get(2).getId()).isEqualTo(600);
    }
}
