package com.thadocizn.todo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "todo")
public class Todo {

    //todo comeback for date
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int todoid;

    @Column(nullable = false)
    private String description;

    @ManyToOne()
    @JoinColumn(name = "userid", nullable = false)
    @JsonIgnore
    private Users users;

    private boolean completed ;

    public Todo() {
    }

    public long getTodoid() {
        return todoid;
    }

    public void setTodoid(int todoid) {
        this.todoid = todoid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

}
