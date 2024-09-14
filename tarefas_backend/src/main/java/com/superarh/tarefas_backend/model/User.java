package com.superarh.tarefas_backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "usuarios")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String name;


    @Column(nullable = false)
    private String password;


    private boolean actived = true;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL) // Relacionamento bidirecional com ListTask
    private List<ListTask> tasks;
}
