package com.lcwd.todo.todo.controllers;

import com.lcwd.todo.todo.models.Todo;
import com.lcwd.todo.todo.services.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Random;


@RestController
@RequestMapping("/todos")
public class TodoController {

    Logger logger = LoggerFactory.getLogger(TodoController.class);

    @Autowired
    private TodoService todoService;

    Random random = new Random();
    @PostMapping
    public ResponseEntity<Todo> createTodoHandler(@RequestBody Todo todo){
        logger.info("Create Todo");

        int id = random.nextInt(99999);

        todo.setId(id);

        Date currentDate= new Date();

        todo.setAddedDate(currentDate);

        Todo todo1 = todoService.createTodo(todo);
        return new ResponseEntity<>(todo1, HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodoHandler(){
        List<Todo> allTodos =  todoService.getAllTodos();

        return new ResponseEntity<>(allTodos,HttpStatus.OK);

    }

    @GetMapping("/{todoId}")
    public ResponseEntity<Todo> getSingleTodoHandler(@PathVariable int todoId){
        Todo todo = todoService.getTodo(todoId);

        return ResponseEntity.ok(todo);
    }

    @PutMapping("/{todoId}")
    public ResponseEntity<Todo> updateTodoHandler(
            @RequestBody Todo todowithNewDetails,
            @PathVariable int todoId){

        Todo todo = todoService.updateTodo(todoId, todowithNewDetails);
        return  ResponseEntity.ok(todo);
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<String> deleteTodo(@PathVariable int todoId){


        todoService.deleteTodo(todoId);

        return ResponseEntity.ok("todo Successfully deleted");
    }
}
