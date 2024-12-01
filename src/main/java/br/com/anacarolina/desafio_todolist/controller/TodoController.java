package br.com.anacarolina.desafio_todolist.controller;

import java.util.List;

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
   private final TodoService service;

    public TodoController(TodoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<List<Todo>> create(@RequestBody @Valid Todo todo, Pageable pageable) {
        List<Todo> novoTodo = service.create(todo, pageable);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoTodo);
    }

    @GetMapping
    public ResponseEntity<Page<Todo>> list(Pageable pageable) {
        return ResponseEntity.ok(service.list(pageable));
    }

    @PutMapping
    public ResponseEntity<List<Todo>> update(@RequestBody Todo todo, Pageable pageable) {
        return ResponseEntity.ok(service.update(todo, pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Todo> detele(@PathVariable Long id, Pageable pageable) {
        service.detele(id, pageable);
        return ResponseEntity.noContent().build();
    }
}
