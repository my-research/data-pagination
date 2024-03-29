package com.github.dhslrl321.pagination.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor(staticName = "loadFromDb")
public class Todo {

    public static Todo todoOf(long id, long ownerId, TodoStatus status, String title, String content, Priority priority) {
        return new Todo(id, title, content, status, priority, LocalDateTime.now(), LocalDateTime.now(), null, ownerId);
    }

    public static Todo todoOf(long id, long ownerId) {
        return new Todo(id, "any title", "any interesting contents", TodoStatus.TODO, Priority.MEDIUM, LocalDateTime.of(2023, 1, 2, 3, 4, 5), LocalDateTime.of(2023, 1, 2, 3, 4, 5), null, ownerId);
    }

    public static Todo todoOf(long id) {
        return Todo.todoOf(id, 1004);
    }

    public static Todo todoOf(long id, LocalDateTime createdAt) {
        return new Todo(id, "any title", "any interesting contents", TodoStatus.TODO, Priority.MEDIUM, createdAt, LocalDateTime.of(2023, 1, 2, 3, 4, 5), null, 1004L);
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
