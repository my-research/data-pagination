package com.github.dhslrl321.pagination.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@AllArgsConstructor(staticName = "loadFromDb")
public class Todo {

    public static Todo todoOf(long id, long ownerId, TodoStatus status, String title, String content, Priority priority) {
        return new Todo(id, title, content, status, priority, LocalDateTime.now(), LocalDateTime.now(), null, ownerId);
    }

    private Long id;
    private String title;
    private String content;
    private TodoStatus status;
    private Priority priority;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    private Long ownerId;
}
