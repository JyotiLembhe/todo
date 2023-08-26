package com.todo.controller;

import com.todo.dto.Todo;
import com.todo.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping(path = "/todo")
@Validated
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping()
    public ResponseEntity<List<Todo>> listAll() {
        return ResponseEntity.ok(todoService.findAll());
    }

    @PostMapping()
    public ResponseEntity<Todo> saveTodo(@Valid @RequestBody Todo todo) throws URISyntaxException {
        int i = todoService.saveTodo(todo);
        return ResponseEntity.created(new URI("http://localhost:8080/todo/" + i)).build();
    }

    @GetMapping(path = "/{todoId}")
    public ResponseEntity<Todo> getTodo(@PathVariable Integer todoId) {
        return ResponseEntity.ok(todoService.getTodo(todoId));
    }

    @DeleteMapping(path = "/{todoId}")
    public ResponseEntity removeTodo(@PathVariable Integer todoId) {
        this.todoService.delete(todoId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/{todoId}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Integer todoId,@RequestBody Todo newTodo) {
        if(newTodo == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else{
            return ResponseEntity.ok(todoService.updateTodo(todoId,newTodo));
        }


    }

}
