package com.oocl.todolistbackend.todoList;

import java.util.List;

public interface TodoRepository {
    List<Todo> findAll();
}
