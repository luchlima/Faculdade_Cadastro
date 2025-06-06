package com.faculdade.alunos.Service;

import com.faculdade.alunos.Entity.Curso;
import com.faculdade.alunos.Repository.AlunoRepository;
import com.faculdade.alunos.Repository.CursoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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

    //metodo get id
    public Curso listarPorId(Long id){
        return cursoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso não encontrado!"));
    }

    //metodo post
    public Curso criar(Curso curso){
        return cursoRepository.save(curso);
    }

    //metodo put
    public Curso editar(Long id, Curso cursoAtual){
        Curso cursoExistente = cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado!"));
        cursoExistente.setNome(cursoAtual.getNome());

        return cursoRepository.save(cursoExistente);
    }

    //metodo delete
    public void deletar(Long id){
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado!"));

        cursoRepository.delete(curso);
    }

}
