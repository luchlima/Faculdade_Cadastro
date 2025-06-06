package com.faculdade.alunos.DTO;

import com.faculdade.alunos.Entity.Curso;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CursoDTO {

    private Long id;
    private String nome;

    public CursoDTO(Curso curso){
        this.id = curso.getId();
        this.nome = curso.getNome();
    }
}
