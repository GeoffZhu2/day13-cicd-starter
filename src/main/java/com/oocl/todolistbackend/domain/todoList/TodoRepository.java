package com.oocl.todolistbackend.domain.todoList;

import java.util.List;

public interface TodoRepository {
    List<Todo> findAll();

    Todo addTodo(Todo todo);

    void clearAll();
}
