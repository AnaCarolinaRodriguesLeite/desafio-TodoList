package br.com.anacarolina.desafio_todolist;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import br.com.anacarolina.desafio_todolist.entity.Todo;
import br.com.anacarolina.desafio_todolist.enums.StatusTodo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DesafioTodolistApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void testeCreatedTodoSucess() {
		var dataCriacao = LocalDateTime.now();
		var dataConclusao = LocalDateTime.now();
		var todo = new Todo("Estudar Spring Boot", "Estudar Spring Boot com a Alura", StatusTodo.PENDENTE, dataCriacao, dataConclusao);

		webTestClient.post().uri("/todos")
				.bodyValue(todo)
				.exchange()
				.expectStatus().isCreated()
				.expectBody()
				.jsonPath("$").isArray()
				.jsonPath("$.length()")
				.isEqualTo(1)
				.jsonPath("$[0].titulo").isEqualTo(todo.getTitulo())
				.jsonPath("$[0].descricao").isEqualTo(todo.getDescricao())
				.jsonPath("$[0].status").isEqualTo(todo.getStatus())
				.jsonPath("$[0].dataCriacao").exists()
        .jsonPath("$[0].dataConclusao").exists();
	}

	@Test
	void testeCreatedTodoFailure() {
		webTestClient.post().uri("/todos")
				.bodyValue(new Todo ("", "", null, null, null))
				.exchange()
				.expectStatus().isBadRequest();
	}
}
