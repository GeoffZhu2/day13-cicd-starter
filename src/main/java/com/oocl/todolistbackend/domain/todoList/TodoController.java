package com.oocl.todolistbackend.domain.todoList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodos() {
        return ResponseEntity.ok(todoService.findAll());
    }

    @PostMapping
    public ResponseEntity<Todo> addTodo(@RequestBody TodoDto todoDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(todoService.addTodo(todoDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> findById(@PathVariable long id) {
        return ResponseEntity.ok(todoService.findById(id));
    }

}
