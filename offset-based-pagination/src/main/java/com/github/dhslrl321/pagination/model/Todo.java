package com.github.dhslrl321.pagination.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.cglib.core.Local;

import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@ToString
@AllArgsConstructor(staticName = "loadFromDb")
public class Todo {

    public static Todo todoOf(long id, long ownerId, TodoStatus status, String title, String content, String category) {
        return new Todo(id, title, content, status, category, Local.now(), Instant.now(), null, ownerId);
    }

    private Long id;
    private String title;
    private String content;
    private String status;
    private String category;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    private Long ownerId;
}
