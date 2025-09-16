package com.oocl.todolistbackend.domain.todoList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoDto {
    private long id;
    private String text;
    private boolean done;
}
