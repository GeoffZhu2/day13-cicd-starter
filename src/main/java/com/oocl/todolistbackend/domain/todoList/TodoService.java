package com.oocl.todolistbackend.domain.todoList;

import com.oocl.todolistbackend.exception.InvalidTodoException;
import com.oocl.todolistbackend.exception.MissingUpdateParameterException;
import com.oocl.todolistbackend.exception.NotFoundException;
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
        if(todoDto.getText() == null || todoDto.getText().trim().isEmpty()) {
            throw new InvalidTodoException();
        }
        Todo todo = new Todo();
        todo.setText(todoDto.getText());
        todo.setDone(false);
        return todoRepository.addTodo(todo);
    }

    public void clearCompanies() {
        todoRepository.clearAll();
    }

    public Todo findById(long id) {
        Todo todo = todoRepository.findById(id);
        if (todo == null) {
            throw new NotFoundException();
        }
        return todo;
    }

    public Todo updateById(long id, TodoDto todoDto) {
        Todo todo = todoRepository.findById(id);
        if (todo == null) {
            throw new NotFoundException();
        }
        if(!isValid(todoDto)) {
            throw new MissingUpdateParameterException();
        }
        todo.setText(todoDto.getText());
        todo.setDone(todoDto.getDone());
        return todoRepository.save(todo);
    }

    private boolean isValid(TodoDto todoDto) {
        if (todoDto == null) {
            return false;
        }
        if (todoDto.getText() == null || todoDto.getText().trim().isEmpty()) {
            return false;
        }
        return todoDto.getDone() != null;
    }

    public Long deleteById(long id) {
        Todo todo = todoRepository.findById(id);
        if (todo == null) {
            throw new NotFoundException();
        }
        todoRepository.deleteById(id);
        return id;
    }
}
