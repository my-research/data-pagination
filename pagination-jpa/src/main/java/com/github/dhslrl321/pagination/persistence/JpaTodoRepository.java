package com.github.dhslrl321.pagination.persistence;

import com.github.dhslrl321.pagination.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTodoRepository extends JpaRepository<Todo, Long> {
}
