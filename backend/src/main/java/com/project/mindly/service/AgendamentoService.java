package com.project.mindly.service;

import com.project.mindly.dtos.agendamento.AgendamentoConfirmeDto;
import com.project.mindly.dtos.agendamento.AgendamentoDto;
import com.project.mindly.model.agendamento.Agendamento;
import com.project.mindly.model.paciente.Paciente;
import com.project.mindly.model.profissional.Profissional;
import com.project.mindly.repository.AgendamentoRepository;
import com.project.mindly.repository.PacienteRepository;
import com.project.mindly.repository.ProfissionalRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final PacienteRepository pacienteRepository;
    private final ProfissionalRepository profissionalRepository;

     AgendamentoService(AgendamentoRepository agendamentoRepository,
                              PacienteRepository pacienteRepository,
                              ProfissionalRepository profissionalRepository) {
        this.agendamentoRepository = agendamentoRepository;
        this.pacienteRepository = pacienteRepository;
        this.profissionalRepository = profissionalRepository;
    }

    public List<Agendamento> findAllAgendamento() {
        return agendamentoRepository.findAll();
    }

    public Optional<Agendamento> findAgendamentoById(int id) {
        return Optional.ofNullable(agendamentoRepository.findById(id))
                .orElseThrow(()-> new EntityNotFoundException("Agendamento não encontrado com o ID: " + id));
    }

    public Agendamento saveAgendamento(AgendamentoDto data) {

        Paciente cpfPaci = pacienteRepository.findById(data.cpf_paciente())
                .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado com CPF: " + data.cpf_paciente()));
        Profissional cpfProf = profissionalRepository.findById(data.cpf_prof())
                .orElseThrow(() -> new EntityNotFoundException("Profissional não encontrado com CPF: " + data.cpf_prof()));

        Agendamento agendamento = new Agendamento();
        agendamento.setDataAgendamento(data.data_agendamento());
        agendamento.setCpfPacienteAgendamento(cpfPaci);
        agendamento.setCpfProfAgendamento(cpfProf);
        agendamento.setDuracao(data.duracao());
        agendamento.setObservacoes(data.observacoes());
        agendamento.setLembreteEnviado(data.lembrete_enviado());
        agendamento.setHoraInicio(data.hora_agendamento());
        agendamento.setLinkVideo(data.link_video());
        agendamento.setStatus(data.status());
        return agendamentoRepository.save(agendamento);
    }

    public Agendamento updateAgendamento(int id,AgendamentoDto data){

        Paciente cpfPaci = pacienteRepository.findById(data.cpf_paciente())
                .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado com CPF: " + data.cpf_paciente()));
        Profissional cpfProf = profissionalRepository.findById(data.cpf_prof())
                .orElseThrow(() -> new EntityNotFoundException("Profissional não encontrado com CPF: " + data.cpf_prof()));

        return agendamentoRepository.findById(id)
                .map(result -> {
                    result.setCpfProfAgendamento(cpfProf);
                    result.setCpfPacienteAgendamento(cpfPaci);
                    result.setDataAgendamento(data.data_agendamento());
                    result.setHoraInicio(data.hora_agendamento());
                    result.setDuracao(data.duracao());
                    result.setLinkVideo(data.link_video());
                    result.setLembreteEnviado(data.lembrete_enviado());
                    result.setObservacoes(data.observacoes());
                    result.setStatus(data.status());
                    return agendamentoRepository.save(result);
                })
                .orElseThrow(()-> new EntityNotFoundException("Agendamento não encontrado com o ID: " + id));
    }

    public void deleteAgendamento(int id) {
        Agendamento agendamento = agendamentoRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Agendamento não encontrado com o ID: " + id));
        agendamentoRepository.delete(agendamento);
    }

    public Agendamento confirmeAgendamento (AgendamentoConfirmeDto data ) {
        Agendamento agendamento = agendamentoRepository.findById(data.idAgendamento())
                .orElseThrow(()-> new EntityNotFoundException("Agendamento não encontrado com o ID: " + data.idAgendamento()));
        agendamento.setStatus(data.status());
        return agendamentoRepository.save(agendamento);
    }
}
