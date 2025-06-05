package com.faculdade.alunos.Service;

import com.faculdade.alunos.Entity.Curso;
import com.faculdade.alunos.Repository.CursoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    //metodo get
    public List<Curso> listar(){
        return cursoRepository.findAll();
    }

    //metodo post
    public Curso criar(Curso curso){
        return cursoRepository.save(curso);
    }

    //metodo put
    public Curso editar(UUID id, Curso cursoAtual){
        Curso cursoExistente = cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado!"));
        cursoExistente.setNome(cursoAtual.getNome());

        return cursoRepository.save(cursoExistente);
    }

    //metodo delete
    public void deletar(UUID id){
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado!"));

        cursoRepository.delete(curso);
    }

}
