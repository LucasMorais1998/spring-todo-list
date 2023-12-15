package com.lucas.todolist.controller;

import com.lucas.todolist.entity.Todo;
import com.lucas.todolist.service.TodoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {
    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    public List<Todo> create(Todo todo) {
        return todoService.create(todo);
    }

    public List<Todo> list() {
        return todoService.list();
    }

    public List<Todo> update(Todo todo) {
        return todoService.update(todo);
    }

    public List<Todo> delete(Long id) {
        return todoService.delete(id);
    }
}
