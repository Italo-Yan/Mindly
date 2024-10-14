package com.project.mindly.repository;

import com.project.mindly.model.profissional.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfissionalRepository extends JpaRepository<Profissional, String> {

    Profissional findByCpfProf(String cpfProf);
}
