package com.lucas.todolist;

import com.lucas.todolist.entity.Todo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ToDoListApplicationTests {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreateTodoSuccess() {
        var todo = new Todo("todo 1", "description todo 1", false, 1);

        webTestClient
                .post()
                .uri("/todos")
                .bodyValue(todo)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").isArray()
                .jsonPath("$.length()").isEqualTo(1)
                .jsonPath("$[0].name").isEqualTo(todo.getName())
                .jsonPath("$[0].description").isEqualTo(todo.getDescription())
                .jsonPath("$[0].complete").isEqualTo(todo.isComplete())
                .jsonPath("$[0].priority").isEqualTo(todo.getPriority());
    }

    @Test
    void testCreateTodoFailure() {
        webTestClient
                .post()
                .uri("/todos")
                .bodyValue(
                        new Todo("", "", false, 0)
                )
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testGetAllTodosSuccess() {
        webTestClient
                .get()
                .uri("/todos")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(APPLICATION_JSON)
                .expectBodyList(Todo.class);
    }
}
