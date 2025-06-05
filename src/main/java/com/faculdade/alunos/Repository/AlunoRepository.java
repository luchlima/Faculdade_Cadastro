package com.faculdade.alunos.Repository;

import com.faculdade.alunos.Entity.Alunos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AlunoRepository extends JpaRepository<Alunos, UUID> {
}
