package com.thadocizn.todo;

import com.thadocizn.todo.models.Todo;
import com.thadocizn.todo.models.Users;
import com.thadocizn.todo.repository.TodoRepository;
import com.thadocizn.todo.repository.UserRepository;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Api(value = "Todo Application", description = "Classic Todo Application")
@RestController
@RequestMapping(params = {}, produces = MediaType.APPLICATION_JSON_VALUE)
public class TodoController {

    @Autowired
    TodoRepository todoRepo;

    @Autowired
    UserRepository userRepo;

    @ApiOperation(value = "list All user", response = List.class)
    @ApiResponses(value =
            {
                    @ApiResponse(code = 200, message = "Successfully recetrieve list"),
                    @ApiResponse(code = 401, message = "You are not authorized to the view the resource"),
                    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
                    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
            })

    // User
    @GetMapping("/users")
    public List<Users> allUsers() {
        return userRepo.findAll();
    }

    @ApiOperation(value = "User based off of user id", response = Users.class)
    @GetMapping("/users/id/{id}")
    public Users findUserId(

            @ApiParam(value = "This is the user you seek", required = true) @PathVariable int id) {
        var foundUser = userRepo.findById(id);
        if (foundUser.isPresent()) {
            return foundUser.get();
        } else {
            return null;
        }
    }

    @GetMapping("/users/username/{name}")
    public Users findUser(@PathVariable String name){
        var foundUser = userRepo.findByUsername(name);
        if (foundUser != null){
            return foundUser;
        }else {
            return null;
        }
    }

    @PostMapping("/users")
    public Users newUser(@RequestBody Users user) throws URISyntaxException
    {
        return userRepo.save(user);
    }

    @PutMapping("/users/userid/{userid}")
    public Users updateUser(@RequestBody Users newUser, @PathVariable int userid) throws URISyntaxException{
        Optional<Users> userToUpdate = userRepo.findById(userid);
        if(userToUpdate.isPresent()){
            newUser.setUserid(userid);
            userRepo.save(newUser);
            return newUser;
        }
        return null;
    }

    @DeleteMapping("/users/id/{id}")
    public Users deleteUser(@PathVariable int id)
    {
        var foundUser = userRepo.findById(id);
        if (foundUser.isPresent()) {
            userRepo.deleteById(id);
            return foundUser.get();
        } else {
            return null;
        }

    }

    // to dos
    @GetMapping("/todos")
    public List<Todo> allTodos() {
        return todoRepo.findAll();
    }

    @ApiOperation(value = "Todo based off of todo id", response = Todo.class)
    @GetMapping("/todos/todoid/{todoid}")
    public Todo findTodoId(@ApiParam(value = "This is the todo you seek", required = true) @PathVariable int id) {
        var foundTodo = todoRepo.findById(id);
        if (foundTodo.isPresent()) {
            return foundTodo.get();
        } else {
            return null;
        }
    }

    @ApiOperation(value = "List all todos and the user name", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully received list"),
            @ApiResponse(code = 401, message = "You are not authorized here"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("/todos/users")
    public List<Object[]> getTodosWithUsers(){
        List<Object[]> todosList = todoRepo.getTodosWithUsers();
        if(todosList != null){
            return todosList;
        }
        return null;
    }

    @ApiOperation(value = "List all todos that are not done", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully received list"),
            @ApiResponse(code = 401, message = "You are not authorized here"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("/todos/active")
    public List<Object[]> getActiveTodos(){
        List<Object[]> todosList = todoRepo.getTodosActive();
        if(todosList != null){
            return todosList;
        }
        return null;
    }
    @PostMapping("/todos")
    public Todo addTodo(@RequestBody Todo todo) throws URISyntaxException {
        return todoRepo.save(todo);
    }

    @ApiOperation(value = "Updates todo by id", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated todo"),
            @ApiResponse(code = 401, message = "You are not authorized here"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @PutMapping("/todos/todoid/{todoid}")
    public Todo updateTodo(@RequestBody Todo newTodo, @PathVariable int todoid) throws URISyntaxException{
        Optional<Todo> todoToUpdate = todoRepo.findById(todoid);
        if(todoToUpdate.isPresent()){
            newTodo.setTodoid(todoid);
            todoRepo.save(newTodo);
            return newTodo;
        }
        return null;
    }

    @ApiOperation(value = "Deletes a todo based off of the given id", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted todo"),
            @ApiResponse(code = 401, message = "You are not authorized here"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @DeleteMapping("/todos/todoid/{todoid}")
    public Todo deleteTodo(@PathVariable int todoid){
        var foundTodo = todoRepo.findById(todoid);
        if(foundTodo.isPresent()){
            todoRepo.deleteById(todoid);
            return foundTodo.get();
        }
        return null;
    }

}
