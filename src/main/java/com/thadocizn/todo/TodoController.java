package com.thadocizn.todo;

import com.thadocizn.todo.models.Todo;
import com.thadocizn.todo.models.Users;
import com.thadocizn.todo.repository.TodoRepository;
import com.thadocizn.todo.repository.UserRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "Todo Application", description = "Classic Todo Application")
@RestController
@RequestMapping(params = {}, produces = MediaType.APPLICATION_JSON_VALUE)
public class TodoController {

    @Autowired
    TodoRepository todoRepo;

    @Autowired
    UserRepository userRepo;

    // User
    @GetMapping("/users")
    public List<Users> allUsers(){
        return userRepo.findAll();
    }

    // to dos
    @GetMapping("/todos")
    public List<Todo> allTodos(){
        return todoRepo.findAll();
    }
}
