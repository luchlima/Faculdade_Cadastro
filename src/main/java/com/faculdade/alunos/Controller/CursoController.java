package com.faculdade.alunos.Controller;

import com.faculdade.alunos.DTO.CursoDTO;
import com.faculdade.alunos.Entity.Curso;
import com.faculdade.alunos.Service.CursoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    private CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @PostMapping("/inserir/curso")
    public ResponseEntity<CursoDTO> newAluno(@RequestBody CursoDTO cursoDTO){
        Curso curso = new Curso(cursoDTO);
        Curso cursoInserido = cursoService.criar(curso);
        URI location = URI.create("/cursos/" + cursoInserido.getId());

        return ResponseEntity.created(location).body(new CursoDTO(cursoInserido));
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<CursoDTO> listPorId(@PathVariable Long id){
        Curso curso = cursoService.listarPorId(id);

        return ResponseEntity.ok(new CursoDTO(curso));
    }

    @GetMapping("/listar/cursos")
    public ResponseEntity<List<CursoDTO>> listCursos(){
        List<Curso> cursos = cursoService.listar();
        List<CursoDTO> dtos = cursos.stream().map(CursoDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<CursoDTO> editCurso(@PathVariable Long id, @RequestBody CursoDTO cursoDTO){
        Curso cursoEditado = cursoService.editar(id, new Curso(cursoDTO));

        return ResponseEntity.ok(new CursoDTO(cursoEditado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCurso(@PathVariable Long id){
        cursoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
