package com.project.mindly.repository.view;

import com.project.mindly.model.view.ProfissionalPublicoView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProfissionalPublicoRepository extends JpaRepository<ProfissionalPublicoView,String> {

    @Query("SELECT p FROM ProfissionalPublicoView p WHERE LOWER(p.nomeProf) LIKE LOWER(CONCAT('%', :nome, '%'))")
    List<ProfissionalPublicoView> findByNomeProf(@Param("nome") String nome);
}
