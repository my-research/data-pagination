package com.github.dhslrl321.pagination.query_paging;

import com.github.dhslrl321.pagination.TodoDbTest;
import com.github.dhslrl321.pagination.fixture.Fixtures;
import com.github.dhslrl321.pagination.persistence.PageRequest;
import com.github.dhslrl321.pagination.persistence.TodoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.time.Instant;

@TodoDbTest
public class Issue_PerformanceDegradeTest {

    @Autowired
    TodoRepository sut;

    @Test
    void name() {
        sut.save(Fixtures.generateTodos(10_000));

        Instant beforeTime = Instant.now();
        sut.query(PageRequest.of(1, 2));
        Instant afterTime = Instant.now();
        System.out.println("실행 시간(ms): " + Duration.between(beforeTime, afterTime).toMillis());

        Instant beforeTime2 = Instant.now();

        sut.query(PageRequest.of(4999, 2));

        Instant afterTime2 = Instant.now();
        System.out.println("실행 시간(ms): " + Duration.between(beforeTime2, afterTime2).toMillis());
    }
}
