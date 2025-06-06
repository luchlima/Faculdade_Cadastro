package com.faculdade.alunos.Entity;

import com.faculdade.alunos.DTO.CursoDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_cursos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ManyToMany(mappedBy = "cursos")
    @JsonBackReference
    private Set<Aluno> alunos = new HashSet<>();

    public Curso(CursoDTO dto) {
        this.id = dto.getId();
        this.nome = dto.getNome();
    }
}
