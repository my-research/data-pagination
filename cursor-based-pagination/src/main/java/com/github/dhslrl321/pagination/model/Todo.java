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

    public static Todo anyTodoOf(long id, long ownerId) {
        return new Todo(id, "any title", "any interesting contents", TodoStatus.TODO, Priority.MEDIUM, LocalDateTime.of(2023, 1, 2, 3, 4, 5), LocalDateTime.of(2023, 1, 2, 3, 4, 5), null, ownerId);
    }

    public static Todo anyTodoOf(long id) {
        return Todo.anyTodoOf(id, 1004);
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
