package com.thadocizn.todo.repository;

import com.thadocizn.todo.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Integer> {

    public Users findByName(String name);
}
