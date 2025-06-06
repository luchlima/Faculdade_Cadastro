package com.faculdade.alunos.Controller;

import com.faculdade.alunos.DTO.AlunoDTO;
import com.faculdade.alunos.Entity.Aluno;
import com.faculdade.alunos.Service.AlunoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @PostMapping("/inserir/aluno")
    public ResponseEntity<AlunoDTO> newAluno(@RequestBody AlunoDTO alunoDTO){
        Aluno aluno = alunoDTO.toEntity();
        Aluno alunoInserido = alunoService.criar(aluno);
        URI location = URI.create("/alunos/" + alunoInserido.getId());

        return ResponseEntity.created(location).body(new AlunoDTO(alunoInserido));
    }

    @PostMapping("/inserir/{alunoId}/cursos/{cursoId}")
    public ResponseEntity<AlunoDTO> adicionarCursoAoAluno(@PathVariable Long alunoId, @PathVariable Long cursoId){
        alunoService.adicionarCursoAoAluno(alunoId, cursoId);
        Aluno alunoAtualizado = alunoService.listarPorId(alunoId);

        return ResponseEntity.ok(new AlunoDTO(alunoAtualizado));
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<AlunoDTO> listPorId(@PathVariable Long id){
        Aluno aluno = alunoService.listarPorId(id);

        return ResponseEntity.ok(new AlunoDTO(aluno));
    }

    @GetMapping("/listar/alunos")
    public ResponseEntity<List<AlunoDTO>> listAlunos(){
        List<Aluno> alunos = alunoService.listar();
        List<AlunoDTO> dtos = alunos.stream().map(AlunoDTO::fromEntity).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<AlunoDTO> editAluno(@PathVariable Long id, @RequestBody AlunoDTO alunoDTO){
        Aluno alunoEditado = alunoService.editar(id, new Aluno(alunoDTO));
        return ResponseEntity.ok(new AlunoDTO(alunoEditado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAluno(@PathVariable Long id){
       alunoService.deletar(id);
       return ResponseEntity.noContent().build();
    }
}
