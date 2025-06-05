package com.faculdade.alunos.Service;

import com.faculdade.alunos.Entity.Alunos;
import com.faculdade.alunos.Repository.AlunoRepository;
import com.faculdade.alunos.Repository.CursoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AlunosService {

    private final AlunoRepository alunoRepository;
    private final CursoRepository cursoRepository;

    public AlunosService(AlunoRepository alunoRepository, CursoRepository cursoRepository) {
        this.alunoRepository = alunoRepository;
        this.cursoRepository = cursoRepository;
    }

    //metodo get
    public List<Alunos> listar(){
        return alunoRepository.findAll();
    }

    //metodo post
    public Alunos criar(Alunos alunos){
        return alunoRepository.save(alunos);
    }

    //metodo put editar nome
    public Alunos editar(UUID id, Alunos alunosAtual){
        Alunos alunosAtualizado = alunoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado!"));
        alunosAtualizado.setNome(alunosAtual.getNome());

        return alunoRepository.save(alunosAtualizado);
    }

    //metodo put adicionar curso ao aluno
    public void adicionarCursoAoAluno(UUID alunoId, UUID cursoId){
        Alunos aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado!"));
        var curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new RuntimeException("Curos não encontrado!"));

        if (!aluno.getCursos().contains(curso)) {
            aluno.getCursos().add(curso);
            alunoRepository.save(aluno);
        }
    }


    //metodo delete
    public void deletar(UUID id){
        Alunos aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado!"));

        alunoRepository.delete(aluno);
    }
}
