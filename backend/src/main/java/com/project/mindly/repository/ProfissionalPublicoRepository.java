package com.project.mindly.repository;

import com.project.mindly.model.view.ProfissionalPublicoView;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfissionalPublicoRepository extends JpaRepository<ProfissionalPublicoView,String> {
}
