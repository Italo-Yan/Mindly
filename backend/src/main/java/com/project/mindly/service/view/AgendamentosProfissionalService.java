package com.project.mindly.service.view;

import com.project.mindly.model.view.AgendamentosProfissionalView;
import com.project.mindly.repository.view.AgendamentosProfissionalRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgendamentosProfissionalService {

    private final AgendamentosProfissionalRepository agendamentosProfissionalRepository;


    public AgendamentosProfissionalService(AgendamentosProfissionalRepository agendamentosProfissionalRepository) {
        this.agendamentosProfissionalRepository = agendamentosProfissionalRepository;
    }

    public List<AgendamentosProfissionalView> findAgendamentoProfissionalAll() {
        return agendamentosProfissionalRepository.findAll();
    }

    public Optional<AgendamentosProfissionalView> findAgendamentoProfissionalByCpf(String cpf) {
        return Optional.ofNullable(agendamentosProfissionalRepository.findById(cpf)
                .orElseThrow(()-> new EntityNotFoundException("Paciente não encontrado com  o CPF: "+ cpf)));
    }
}
