package com.github.dhslrl321.pagination.offset_paging;

import com.github.dhslrl321.pagination.TodoDbTest;
import com.github.dhslrl321.pagination.fixture.Fixtures;
import com.github.dhslrl321.pagination.model.Todo;
import com.github.dhslrl321.pagination.persistence.JpaTodoRepository;
import com.github.dhslrl321.pagination.persistence.TodoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static com.github.dhslrl321.pagination.fixture.Fixtures.generateTodos;
import static com.github.dhslrl321.pagination.model.Todo.anyTodoOf;
import static org.assertj.core.api.Assertions.assertThat;

@TodoDbTest
public class JpaPaginationTest {

    @Autowired
    JpaTodoRepository sut;

    @BeforeEach
    void setUp() {
        sut.saveAll(generateTodos(100));
    }

    @Test
    void name() {
        Page<Todo> all = sut.findAll(PageRequest.of(1, 3));

        System.out.println("all = " + all);
    }

}
