package com.oocl.todolistbackend.domain.todoList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TodoRepositoryImpl implements TodoRepository {

    @Autowired
    private TodoJpaRepository todoJpaRepository;

    @Override
    public List<Todo> findAll() {
        return todoJpaRepository.findAll();
    }

    @Override
    public Todo addTodo(Todo todo) {
        return todoJpaRepository.save(todo);
    }

    @Override
    public void clearAll() {
        todoJpaRepository.deleteAll();
    }

    @Override
    public Todo findById(long id) {
        return todoJpaRepository.findById(id).orElse(null);
    }

    @Override
    public Todo save(Todo todo) {
        return todoJpaRepository.save(todo);
    }

    @Override
    public void deleteById(long id) {
        todoJpaRepository.deleteById(id);
    }
}
