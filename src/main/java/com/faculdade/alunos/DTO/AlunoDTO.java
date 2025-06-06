package com.faculdade.alunos.DTO;

import com.faculdade.alunos.Entity.Aluno;
import com.faculdade.alunos.Entity.Curso;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlunoDTO {

    private Long id;
    private String nome;
    private Set<CursoDTO> cursos;

    public static AlunoDTO fromEntity(Aluno aluno) {
        AlunoDTO dto = new AlunoDTO();
        dto.setId(aluno.getId());
        dto.setNome(aluno.getNome());

        if (aluno.getCursos() != null){
            dto.setCursos(aluno.getCursos().stream().map(CursoDTO::new).collect(Collectors.toSet()));
        }
        return dto;
    }
}
