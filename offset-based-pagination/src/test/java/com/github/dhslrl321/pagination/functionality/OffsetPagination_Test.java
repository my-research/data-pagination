package com.github.dhslrl321.pagination.functionality;

import com.github.dhslrl321.pagination.DataSourceSupports;
import com.github.dhslrl321.pagination.model.Todo;
import com.github.dhslrl321.pagination.model.TodoJdbcTestHelper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = DataSourceSupports.class)
@Sql("classpath:init.sql")
public class OffsetPagination_Test {

    @Autowired
    TodoJdbcTestHelper sut;

    @BeforeEach
    void setUp() {
        sut.save(Todo.todoOf())
    }

    @Test
    void name() {
        List<Todo> query = sut.query("SELECT * FROM simple_todos limit 5");

        assertThat(query).hasSize(0);
    }
}
