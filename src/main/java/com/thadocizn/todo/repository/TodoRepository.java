package com.thadocizn.todo.repository;

import com.thadocizn.todo.models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
