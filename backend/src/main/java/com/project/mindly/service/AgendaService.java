package com.project.mindly.service;

import com.project.mindly.dtos.agenda.AgendaDto;
import com.project.mindly.dtos.agendamento.AgendamentoDto;
import com.project.mindly.model.agenda.Agenda;
import com.project.mindly.model.profissional.Profissional;
import com.project.mindly.repository.AgendaRepository;
import com.project.mindly.repository.ProfissionalRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgendaService {

    private final AgendaRepository agendaRepository;
    private final ProfissionalRepository profissionalRepository;

    public AgendaService(AgendaRepository agendaRepository, ProfissionalRepository profissionalRepository) {
        this.agendaRepository = agendaRepository;
        this.profissionalRepository = profissionalRepository;
    }

    public List<Agenda> findAgendaAll() {
        return agendaRepository.findAll();
    }

    public Optional<Agenda> findAgendaById(int id) {
        return Optional.ofNullable(agendaRepository.findById(id))
                .orElseThrow(()-> new EntityNotFoundException("Agenda não encontrada com o ID: " + id));
    }

    public Agenda saveAgenda(AgendaDto data) {
        Profissional cpfProf = profissionalRepository.findById(data.cpf_prof())
                .orElseThrow(()-> new EntityNotFoundException("Profissional não encontrado com o CPF: " + data.cpf_prof()));
        Agenda agenda = new Agenda();
        agenda.setAtivo(data.ativo());
        agenda.setCpfProfAgenda(cpfProf);
        agenda.setDuracao(data.duracao());
        agenda.setHoraInicio(data.horaInicio());
        agenda.setHoraFim(data.horaFim());
        agenda.setDiaDaSemana(data.diaDaSemana());
        return agendaRepository.save(agenda);
    }

    public Agenda updateAgenda(int id,AgendaDto data) {
        Profissional cpfProf = profissionalRepository.findById(data.cpf_prof())
                .orElseThrow(()-> new EntityNotFoundException("Profissional não encontrado com o CPF: " + data.cpf_prof()));
        return agendaRepository.findById(id)
                .map(result -> {
                    result.setCpfProfAgenda(cpfProf);
                    result.setAtivo(data.ativo());
                    result.setDuracao(data.duracao());
                    result.setHoraInicio(data.horaInicio());
                    result.setHoraFim(data.horaFim());
                    result.setDiaDaSemana(data.diaDaSemana());
                    return agendaRepository.save(result);
                })
                .orElseThrow(() -> new EntityNotFoundException("Agenda não encontrado com o ID: " + id));
    }

    public void deleteAgenda(int id) {
        Agenda agenda = agendaRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Agenda não encontrada com o ID: " + id));
        agendaRepository.delete(agenda);
    }
}
