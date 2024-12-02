package br.com.anacarolina.desafio_todolist.service;

import br.com.anacarolina.desafio_todolist.enums.StatusTodo;
import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> create(Todo tarefa, Pageable pageable) {
        logger.info("Criando uma nova tarefa: {}", tarefa);
        if (tarefa.getStatus() == null) {
            tarefa.setStatus(StatusTodo.PENDENTE);
        }
        Todo savedTask = todoRepository.save(tarefa);
        logger.info("Tarefa criada com sucesso: {}", savedTask);
        return list(pageable).getContent();
    }

    public Page<Todo> list(Pageable pageable) {
        logger.info("Listando tarefas com paginação: {}", pageable);
        Sort sort = Sort.by("dataCriacao").descending().and(Sort.by("titulo").ascending());
        Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        Page<Todo> todos = todoRepository.findAll(sortedPageable);
        logger.info("Tarefas listadas com sucesso. Total: {}", todos.getTotalElements());
        return todos;
    }

    public List<Todo> update(Todo todo, Pageable pageable) {
        logger.info("Atualizando tarefa com ID: {}", todo.getId());
        Todo tarefa = todoRepository.findById(todo.getId())
                .orElseThrow(() -> {
                    logger.error("Tarefa não encontrada para o ID: {}", todo.getId());
                    return new RuntimeException("Tarefa não encontrada");
                });
        tarefa.setStatus(todo.getStatus());
        if (todo.getStatus() == StatusTodo.CONCLUIDA) {
            tarefa.setDataConclusao(LocalDateTime.now());
            logger.info("Tarefa marcada como concluída com data de conclusão: {}", tarefa.getDataConclusao());
        }
        Todo updatedTask = todoRepository.save(tarefa);
        logger.info("Tarefa atualizada com sucesso: {}", updatedTask);
        return list(pageable).getContent();
    }

    public List<Todo> detele(Long id) {
      logger.info("Excluindo tarefa com ID: {}", id);
      todoRepository.deleteById(id);
      logger.info("Tarefa excluída com sucesso.");
      return todoRepository.findAll();
  }  
}
