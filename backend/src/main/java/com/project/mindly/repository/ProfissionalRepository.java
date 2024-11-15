package com.project.mindly.repository;

import com.project.mindly.model.profissional.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProfissionalRepository extends JpaRepository<Profissional, String> {

    Profissional findByEmailProf(String nome);
    @Query("SELECT p FROM Profissional p WHERE LOWER(p.nomeProf) LIKE LOWER(CONCAT('%', :nome, '%'))")
    List<Profissional> findByNomeProf(@Param("nome") String nome);
}
