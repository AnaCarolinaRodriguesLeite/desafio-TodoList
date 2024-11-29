package br.com.anacarolina.desafio_todolist.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.anacarolina.desafio_todolist.entity.Todo;
import br.com.anacarolina.desafio_todolist.service.TodoService;

@RestController
@RequestMapping("/todos")
public class TodoController {
   private final TodoService service;

    public TodoController(TodoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<List<Todo>> create(@RequestBody Todo todo) {
        return ResponseEntity.ok(service.create(todo));
    }

    @GetMapping
    public ResponseEntity<List<Todo>> listar() {
        return ResponseEntity.ok(service.list());
    }

    @PutMapping
    public ResponseEntity<List<Todo>> atualizar(@RequestBody Todo todo) {
        return ResponseEntity.ok(service.update(todo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Todo> detele(@PathVariable Long id) {
        service.detele(id);
        return ResponseEntity.noContent().build();
    }
}
