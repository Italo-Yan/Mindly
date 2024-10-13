package com.project.mindly.repository;

import com.project.mindly.model.agenda.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendaRepository extends JpaRepository<Agenda,Integer> {
}
