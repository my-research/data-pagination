package com.github.dhslrl321.pagination.offset_paging;

import com.github.dhslrl321.pagination.TodoDbTest;
import com.github.dhslrl321.pagination.model.Todo;
import com.github.dhslrl321.pagination.persistence.PageRequest;
import com.github.dhslrl321.pagination.persistence.TodoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.github.dhslrl321.pagination.model.Todo.anyTodoOf;
import static org.assertj.core.api.Assertions.assertThat;

@TodoDbTest
public class OffsetBasedPaginationTest {

    @Autowired
    TodoRepository sut;

    @BeforeEach
    void setUp() {
        sut.save(
                anyTodoOf(1), anyTodoOf(2),
                anyTodoOf(3), anyTodoOf(4),
                anyTodoOf(5), anyTodoOf(6)
        );
    }

    @Test
    @DisplayName("page offset 공식은 " +
            "[offset = (pageNumber - 1) * pageSize)]" +
            "이다")
    void name() {
        // offset(0) = pageNumber(0) * pageSize(2)
        List<Todo> firstPage = sut.query("SELECT * FROM todos LIMIT 2 OFFSET 0");
        assertThat(firstPage).contains(anyTodoOf(1), anyTodoOf(2));

        // offset(2) = pageNumber(1) * pageSize(2)
        List<Todo> nextPage = sut.query("SELECT * FROM todos LIMIT 2 OFFSET 2");
        assertThat(nextPage).contains(anyTodoOf(3), anyTodoOf(4));

        // offset(4) = pageNumber(2) * pageSize(2)
        List<Todo> pageAfterNext = sut.query("SELECT * FROM todos LIMIT 2 OFFSET 4");
        assertThat(pageAfterNext).contains(anyTodoOf(5), anyTodoOf(6));
    }

    @Test
    @DisplayName("PageRequest 객체를 통해서 page 를 가져올 수도 있다")
    void name2() {
        List<Todo> firstPage = sut.query(PageRequest.of(1, 2));
        assertThat(firstPage).contains(anyTodoOf(1), anyTodoOf(2));

        List<Todo> nextPage = sut.query(PageRequest.of(2, 2));
        assertThat(nextPage).contains(anyTodoOf(3), anyTodoOf(4));

        List<Todo> pageAfterNext = sut.query(PageRequest.of(3, 2));
        assertThat(pageAfterNext).contains(anyTodoOf(5), anyTodoOf(6));
    }
}
