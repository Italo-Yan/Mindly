package com.project.mindly.repository;

import com.project.mindly.model.agendamento.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer> {
}
