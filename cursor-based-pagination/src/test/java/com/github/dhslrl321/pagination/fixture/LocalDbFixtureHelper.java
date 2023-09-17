package com.github.dhslrl321.pagination.fixture;

import com.github.dhslrl321.pagination.model.Todo;
import com.github.dhslrl321.pagination.persistence.TodoRepository;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class LocalDbFixtureHelper {

    JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        HikariDataSource datasource = new HikariDataSource();
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/my-test-db"); // MySQL 호스트 및 포트
        config.setUsername("my-test-db"); // MySQL 사용자 이름
        config.setPassword("my-test-db"); // MySQL 비밀번호
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        HikariDataSource dataSource = new HikariDataSource(config);

        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Test
    @DisplayName("1000만건의 데이터를 한번에 로컬 db 에 insert 합니다")
    @Disabled
    void name() {
        int threadCount = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);

        for (int i = 0; i < threadCount; i++) {
            executorService.execute(() -> {
                for (int j = 0; j < threadCount; j++) {
                    this.save(Fixtures.generateTodos(20_000));
                }
            });
        }

        executorService.shutdown();

        try {
            // 모든 스레드가 실행을 마칠 때까지 대기합니다.
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void save(List<Todo> todo) {
        for (Todo t : todo) {
            save(t);
        }
    }

    private void save(Todo todo) {

        String sql = "INSERT INTO simple_todos (title, content, status, category, createdAt, updatedAt, deletedAt, ownerId) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                todo.getTitle(),
                todo.getContent(),
                todo.getStatus().name(),
                "sample",
                todo.getCreatedAt(),
                todo.getUpdatedAt(),
                todo.getDeletedAt(),
                todo.getOwnerId()
        );
    }
}
