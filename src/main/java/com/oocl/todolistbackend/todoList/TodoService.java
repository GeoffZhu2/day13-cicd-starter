package com.oocl.todolistbackend.todoList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    public Todo addTodo(TodoDto todoDto) {
        Todo todo = new Todo();
        todo.setText(todoDto.getText());
        todo.setDone(false);
        return todoRepository.addTodo(todo);
    }

    public void clearCompanies() {
        todoRepository.clearAll();
    }
}
