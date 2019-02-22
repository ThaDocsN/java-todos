package com.thadocizn.todo;

import com.thadocizn.todo.models.Todo;
import com.thadocizn.todo.models.Users;
import com.thadocizn.todo.repository.TodoRepository;
import com.thadocizn.todo.repository.UserRepository;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

            @ApiParam(value = "This is the user you seek", required = true) @PathVariable long id) {
        var foundUser = userRepo.findById(id);
        if (foundUser.isPresent()) {
            return foundUser.get();
        } else {
            return null;
        }
    }

    @GetMapping("/users/username/{name}")
    public Users findUser(@PathVariable String name){
        var foundUser = userRepo.findByName(name);
        if (foundUser != null){
            return foundUser;
        }else {
            return null;
        }
    }
    // to dos
    @GetMapping("/todos")
    public List<Todo> allTodos() {
        return todoRepo.findAll();
    }
}
