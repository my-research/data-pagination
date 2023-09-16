package com.github.dhslrl321.pagination.deferred_join;

import com.github.dhslrl321.pagination.TodoDbTest;
import com.github.dhslrl321.pagination.model.Todo;
import com.github.dhslrl321.pagination.persistence.TodoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.github.dhslrl321.pagination.model.Todo.anyTodoOf;
import static org.assertj.core.api.Assertions.assertThat;

@TodoDbTest
public class DeferredJoinOffsetPaginationTest {

    @Autowired
    TodoRepository sut;

    private final int pageNumber = 1;
    private final int PAGE_SIZE = 3;
    private final int OFFSET = (pageNumber - 1) * PAGE_SIZE;

    @BeforeEach
    void setUp() {
        sut.save(
                anyTodoOf(100), anyTodoOf(200), anyTodoOf(300),
                anyTodoOf(400), anyTodoOf(500), anyTodoOf(600)
        );
    }

    @Test
    @DisplayName("일반적인 offset based pagination")
    void simple_offset_pagination() {

        String pageQuery = String.format("SELECT * FROM todos LIMIT %d OFFSET %d", PAGE_SIZE, OFFSET);

        List<Todo> actual = sut.query(pageQuery);

        assertThat(actual.get(0).getId()).isEqualTo(100);
        assertThat(actual.get(1).getId()).isEqualTo(200);
        assertThat(actual.get(2).getId()).isEqualTo(300);
    }

    @Test
    @DisplayName("deferred join 을 이용한 offset based pagination 최적화")
    void performance_optimized_by_deferredJoin_offset_pagination() {

        String pageQuery = String.format("SELECT * FROM todos " +
                "INNER JOIN (" +
                    "SELECT id FROM todos ORDER BY id LIMIT %d OFFSET %d" + // deferred join 을 이용함
                ") AS sub_todos USING(id) " +
                "ORDER BY id", PAGE_SIZE, OFFSET);

        List<Todo> actual = sut.query(pageQuery);

        assertThat(actual.get(0).getId()).isEqualTo(100);
        assertThat(actual.get(1).getId()).isEqualTo(200);
        assertThat(actual.get(2).getId()).isEqualTo(300);
    }
}
