package com.faculdade.alunos.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tb_cursos")
@Data
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String nome;

    @ManyToMany(mappedBy = "cursos")
    private Set<Alunos> alunos = new HashSet<>();
}
