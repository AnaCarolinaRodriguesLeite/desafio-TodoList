package br.com.anacarolina.desafio_todolist.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Pageable;

import br.com.anacarolina.desafio_todolist.entity.Todo;
import br.com.anacarolina.desafio_todolist.service.TodoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private static final Logger logger = LoggerFactory.getLogger(TodoController.class);

    private final TodoService service;

    public TodoController(TodoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<List<Todo>> create(@RequestBody @Valid Todo todo, Pageable pageable) {
        logger.info("Recebida solicitação para criar uma tarefa: {}", todo);
        List<Todo> novoTodo = service.create(todo, pageable);
        logger.info("Tarefa criada com sucesso e lista retornada.");
        return ResponseEntity.status(HttpStatus.CREATED).body(novoTodo);
    }

    @GetMapping
    public ResponseEntity<Page<Todo>> list(Pageable pageable) {
        logger.info("Recebida solicitação para listar tarefas com paginação: {}", pageable);
        Page<Todo> todos = service.list(pageable);
        logger.info("Tarefas listadas com sucesso. Total retornado: {}", todos.getTotalElements());
        return ResponseEntity.ok(todos);
    }

    @PutMapping
    public ResponseEntity<List<Todo>> update(@RequestBody Todo todo, Pageable pageable) {
        logger.info("Recebida solicitação para atualizar uma tarefa: {}", todo);
        List<Todo> updatedTodos = service.update(todo, pageable);
        logger.info("Tarefa atualizada com sucesso.");
        return ResponseEntity.ok(updatedTodos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> detele(@PathVariable Long id) {
        logger.info("Recebida solicitação para excluir a tarefa com ID: {}", id);
        service.detele(id);
        logger.info("Tarefa excluída com sucesso.");
        return ResponseEntity.noContent().build();
    }    
}
