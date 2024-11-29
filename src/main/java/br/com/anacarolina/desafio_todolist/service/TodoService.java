package br.com.anacarolina.desafio_todolist.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.anacarolina.desafio_todolist.entity.Todo;
import br.com.anacarolina.desafio_todolist.repository.TodoRepository;

@Service
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> create(Todo tarefa) {
        todoRepository.save(tarefa);
        return list();
    }

    public List<Todo> list() {
      Sort sort = Sort.by("dataCriacao").descending().and(Sort.by("titulo").ascending());
      return todoRepository.findAll(sort);
    }  

    public List<Todo> update(Todo todo) {
        // Todo tarefa = todoRepository.findById(id).orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
        // tarefa.setTitulo(tarefaAtualizada.getTitulo());
        // tarefa.setDescricao(tarefaAtualizada.getDescricao());
        // tarefa.setStatus(tarefaAtualizada.getStatus());
        // tarefa.setDataConclusao(tarefaAtualizada.getDataConclusao());
        todoRepository.save(todo);
        return list();
    }

    public List<Todo> detele(Long id) {
      todoRepository.deleteById(id);
      return list();
    }
}
