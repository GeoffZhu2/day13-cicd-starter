package com.oocl.todolistbackend.todoList;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oocl.todolistbackend.domain.todoList.TodoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TodoControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TodoService todoService;

    @BeforeEach
    void setUp() {
        todoService.clearCompanies();
    }

    @Test
    void should_return_empty_array_when_find_empty_database() throws Exception {
        mockMvc.perform(get("/todos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    void should_create_a_todo_item_post_given_a_valid_body() throws Exception {
        String requestBody = """
                {
                    "text": "Java"
                }
                """;
        mockMvc.perform(post("/todos").contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.text").value("Java"))
                .andExpect(jsonPath("$.done").value(false));
    }

    @Test
    void should_not_create_a_todo_item_post_given_a_invalid_body() throws Exception {
        String requestBody1 = """
                {
                    "text": ""
                }
                """;
        mockMvc.perform(post("/todos").contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody1))
                .andExpect(status().isUnprocessableEntity());
        String requestBody2 = """
                {
                    "done": false
                }
                """;
        mockMvc.perform(post("/todos").contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody2))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void should_return_todo_when_find_by_the_exist_id() throws Exception {
        String requestBody = """
                {
                    "text": "Java"
                }
                """;
        long id = createTodo(requestBody);

        mockMvc.perform(get("/todos/{id}", id))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void should_throw_exception_when_find_by_the_not_exist_id() throws Exception {
        String requestBody = """
                {
                    "text": "Java"
                }
                """;
        createTodo(requestBody);

        mockMvc.perform(get("/todos/{id}", 99999))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    void should_update_todo_when_update_by_the_exist_id() throws Exception {
        String requestBody = """
                {
                    "text": "Java"
                }
                """;
        long id = createTodo(requestBody);

        String updateDTO = """
                {
                    "id": 1,
                    "text": "C++",
                    "done": true
                }
                """;
        mockMvc.perform(put("/todos/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(updateDTO))
                .andExpect(status().isOk());
    }

    private long createTodo(String requestBody) throws Exception {
        ResultActions resultActions = mockMvc.perform(post("/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));
        MvcResult mvcResult = resultActions.andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        return new ObjectMapper().readTree(contentAsString).get("id").asLong();
    }

}
