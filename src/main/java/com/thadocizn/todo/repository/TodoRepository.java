package com.thadocizn.todo.repository;

import com.thadocizn.todo.models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Integer> {

    @Query(value = "SELECT u.username, t.description, t.completed FROM todo t, users u WHERE t.userid = u.userid", nativeQuery = true)
    List<Object[]> getTodosWithUsers();

    @Query(value = "SELECT u.username, t.description, t.completed FROM todo t, users u WHERE t.completed = 0", nativeQuery = true)
    List<Object[]> getTodosActive();

    @Query(value = "SELECT * FROM todo t WHERE t.userid = :userid", nativeQuery = true)
    List<Todo> getUserTodos(@Param("userid") int userid);
}
