package com.faculdade.alunos.Service;

import com.faculdade.alunos.Entity.Aluno;
import com.faculdade.alunos.Repository.AlunoRepository;
import com.faculdade.alunos.Repository.CursoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final CursoRepository cursoRepository;

    public AlunoService(AlunoRepository alunoRepository, CursoRepository cursoRepository) {
        this.alunoRepository = alunoRepository;
        this.cursoRepository = cursoRepository;
    }

    //metodo get
    public List<Aluno> listar(){
        return alunoRepository.findAll();
    }

    //metodo get id
    public Aluno listarPorId(Long id){
        return alunoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado!"));
    }

    //metodo post
    public Aluno criar(Aluno aluno){
        return alunoRepository.save(aluno);
    }

    //metodo put editar nome
    public Aluno editar(Long id, Aluno alunoAtualizado){
        Aluno alunoExistente = alunoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado com id: " + id));
        alunoExistente.setNome(alunoAtualizado.getNome());
        alunoExistente.setCursos(alunoAtualizado.getCursos());

        return alunoRepository.save(alunoExistente);
    }

    //metodo put adicionar curso ao aluno
    public void adicionarCursoAoAluno(Long alunoId, Long cursoId){
        Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado!"));
        var curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new RuntimeException("Curos não encontrado!"));

        if (!aluno.getCursos().contains(curso)) {
            aluno.getCursos().add(curso);
            alunoRepository.save(aluno);
        }
    }


    //metodo delete
    public void deletar(Long id){
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado!"));

        alunoRepository.delete(aluno);

    }
}
