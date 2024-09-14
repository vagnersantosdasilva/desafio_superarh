package com.superarh.tarefas_backend.model;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "tarefas")
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "list_task_id", nullable = false)
    private ListTask listTask;

    // Estado da tarefa: pendente, concluída, etc.
    @Column(name = "status", length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusTask status = StatusTask.PENDENTE;

    // Sugestão de campos para auditoria
    @Column(name="date_start")
    private LocalDateTime dateStart = LocalDateTime.now();

    @Column(name="date_finish")
    private LocalDateTime dateFinish;

}
