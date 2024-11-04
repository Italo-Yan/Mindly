package com.project.mindly.repository;

import com.project.mindly.model.paciente.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, String> {

    Paciente findByEmailPaciente(String email);

}
