package com.github.dhslrl321.pagination.functionality;

import com.github.dhslrl321.pagination.model.Todo;
import com.github.dhslrl321.pagination.persistence.TodoRepository;
import com.github.dhslrl321.pagination.TodoDbTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.github.dhslrl321.pagination.fixture.Fixtures.generateTodos;
import static org.assertj.core.api.Assertions.assertThat;

@TodoDbTest
public class Functionality_TestSetUp_Test {

    @Autowired
    TodoRepository sut;

    @Test
    @DisplayName("setUp 에서 100개의 todo 를 생성했을 때, 총 100 개의 row 가 존재한다")
    void name() {
        sut.save(generateTodos(100));
        List<Todo> query = sut.query("SELECT * FROM todos");

        assertThat(query).hasSize(100);
    }

    @Test
    @DisplayName("setUp 에서 100개의 todo 를 생성했을 때, 총 1 개의 row 가 존재한다")
    void name2() {
        sut.save(generateTodos(1));
        List<Todo> query = sut.query("SELECT * FROM todos");

        assertThat(query).hasSize(1);
    }
}
