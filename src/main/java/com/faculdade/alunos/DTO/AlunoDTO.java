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

    public AlunoDTO(Aluno aluno) {
        this.id = aluno.getId();
        this.nome = aluno.getNome();

        if (aluno.getCursos() != null) {
            this.cursos = aluno.getCursos()
                    .stream()
                    .map(CursoDTO::new)
                    .collect(Collectors.toSet());
        }
    }

    public static AlunoDTO fromEntity(Aluno aluno) {
        return new AlunoDTO(aluno);
    }


    public Aluno toEntity(){
        Aluno aluno = new Aluno();
        aluno.setId(this.id);
        aluno.setNome(this.nome);
        if (this.cursos != null){
            aluno.setCursos(this.cursos.stream()
                    .map(CursoDTO::toEntity)
                    .collect(Collectors.toSet()));
        }
        return aluno;
    }
}
