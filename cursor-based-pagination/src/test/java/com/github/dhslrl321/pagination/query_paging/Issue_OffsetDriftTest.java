package com.github.dhslrl321.pagination.query_paging;

import com.github.dhslrl321.pagination.TodoDbTest;
import com.github.dhslrl321.pagination.model.Todo;
import com.github.dhslrl321.pagination.persistence.TodoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.github.dhslrl321.pagination.model.Todo.anyTodoOf;
import static org.assertj.core.api.Assertions.assertThat;

@TodoDbTest
public class Issue_OffsetDriftTest {

    @Autowired
    TodoRepository sut;

    @Test
    @DisplayName("paging 중간에 insert 가 되면 drift 현상으로 인해 중복으로 값을 읽어올 수 있다")
    void name() {
        sut.save(anyTodoOf(1), anyTodoOf(2), anyTodoOf(3), anyTodoOf(4), anyTodoOf(5));

        List<Todo> firstPage = sut.query("SELECT * FROM todos ORDER BY id DESC LIMIT 2 OFFSET 0");
        assertThat(firstPage).contains(anyTodoOf(5), anyTodoOf(4));

        sut.save(anyTodoOf(6)); // inserted !

        List<Todo> secondPage = sut.query("SELECT * FROM todos ORDER BY id DESC LIMIT 2 OFFSET 2");
        assertThat(secondPage).contains(anyTodoOf(4), anyTodoOf(3)); // 4 가 중복됨
    }
}
