package com.oocl.todolistbackend.domain.todoList;

import org.springframework.data.jpa.repository.JpaRepository;


public interface TodoJpaRepository extends JpaRepository<Todo, Long> {
}
