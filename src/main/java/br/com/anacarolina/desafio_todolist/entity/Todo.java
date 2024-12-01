package br.com.anacarolina.desafio_todolist.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import br.com.anacarolina.desafio_todolist.enums.StatusTodo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "todo")
public class Todo {
   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @NotBlank
    private String titulo;
    private String descricao;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusTodo status;
    @Column(nullable = false, updatable = false)
    private LocalDateTime dataCriacao;
    private LocalDateTime dataConclusao;

    public Todo() {}

    public Todo(String titulo, String descricao, @NotNull StatusTodo status, LocalDateTime dataCriacao,
        LocalDateTime dataConclusao) {
      this.titulo = titulo;
      this.descricao = descricao;
      this.status = status;
      this.dataCriacao = dataCriacao;
      this.dataConclusao = dataConclusao;
    }

    @PrePersist
    public void prePersist() {
        this.dataCriacao = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        if (StatusTodo.CONCLUIDA.equals(this.status) && this.dataConclusao == null) {
            this.dataConclusao = LocalDateTime.now();
        }
    }

    public Long getId() {
      return id;
    }

    public void setId(Long id) {
      this.id = id;
    }

    public String getTitulo() {
      return titulo;
    }

    public void setTitulo(String titulo) {
      this.titulo = titulo;
    }

    public String getDescricao() {
      return descricao;
    }

    public void setDescricao(String descricao) {
      this.descricao = descricao;
    }

    public StatusTodo getStatus() {
      return status;
    }

    public void setStatus(StatusTodo status) {
      this.status = status;
    }

    public LocalDateTime getDataCriacao() {
      return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
      this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataConclusao() {
      return dataConclusao;
    }

    public void setDataConclusao(LocalDateTime dataConclusao) {
      this.dataConclusao = dataConclusao;
    }
}
