package br.com.anacarolina.desafio_todolist.service;

import br.com.anacarolina.desafio_todolist.enums.StatusTodo;
import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.anacarolina.desafio_todolist.entity.Todo;
import br.com.anacarolina.desafio_todolist.repository.TodoRepository;

@Service
public class TodoService {

  private static final Logger logger = LoggerFactory.getLogger(TodoService.class);

    @Autowired
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @SuppressWarnings("unchecked")
    public List<Todo> create(Todo tarefa, Pageable pageable) {
      if (tarefa.getStatus() == null) {
        tarefa.setStatus(StatusTodo.PENDENTE);
      }
      todoRepository.save(tarefa);
      return (List<Todo>) list(pageable);
    }

    public Page<Todo> list(Pageable pageable) { 
      Sort sort = Sort.by("dataCriacao").descending().and(Sort.by("titulo").ascending());
      Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
      return todoRepository.findAll(sortedPageable);
    }

    @SuppressWarnings("unchecked")
    public List<Todo> update(Todo todo, Pageable pageable) {
        Todo tarefa = todoRepository.findById(todo.getId()).orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
        tarefa.setStatus(todo.getStatus());
        if (todo.getStatus() == StatusTodo.CONCLUIDA) {
            tarefa.setDataConclusao(LocalDateTime.now());
        }
        todoRepository.save(tarefa);
        return (List<Todo>) list(pageable);
    }

    @SuppressWarnings("unchecked")
    public List<Todo> detele(Long id, Pageable pageable) {
      todoRepository.deleteById(id);
      return (List<Todo>) list(pageable);
    }
}
