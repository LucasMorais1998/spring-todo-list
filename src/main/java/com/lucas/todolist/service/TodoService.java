package com.lucas.todolist.service;

import com.lucas.todolist.entity.Todo;
import com.lucas.todolist.repository.TodoRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    private TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> create(Todo todo) {
        todoRepository.save(todo);

        return list();
    }

    public List<Todo> list() {
        Sort sort = Sort.by("priority").descending().and(
                Sort.by("name").ascending());

        return todoRepository.findAll(sort);
    }
}
