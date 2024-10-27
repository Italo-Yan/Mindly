package com.project.mindly.controller;


import com.project.mindly.model.agendamento.Agendamento;
import com.project.mindly.model.agendamento.AgendamentoDto;
import com.project.mindly.model.paciente.Paciente;
import com.project.mindly.model.profissional.Profissional;
import com.project.mindly.repository.AgendamentoRepository;
import com.project.mindly.repository.PacienteRepository;
import com.project.mindly.repository.ProfissionalRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {

    private final AgendamentoRepository agendamentoRepository;

    private final ProfissionalRepository profissionalRepository;

    private final PacienteRepository pacienteRepository;

    public AgendamentoController(AgendamentoRepository agendamentoRepository,
                                 ProfissionalRepository profissionalRepository,
                                 PacienteRepository pacienteRepository) {
        this.agendamentoRepository = agendamentoRepository;
        this.profissionalRepository = profissionalRepository;
        this.pacienteRepository = pacienteRepository;
    }


    @GetMapping
    public List<Agendamento> getAllAgendamento() {
        return agendamentoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agendamento> getByIdAgendamento(@PathVariable @Valid int id) {
        return agendamentoRepository.findById(id)
                .map(result -> ResponseEntity.status(HttpStatus.OK).body(result))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/create")
    public ResponseEntity<Agendamento> createAgendamento(@RequestBody @Valid AgendamentoDto data) {
        try {
            Profissional cpfProf = profissionalRepository.findByCpfProf(data.cpf_prof());
            Paciente cpfPaci = pacienteRepository.findByCpfPaciente(data.cpf_paciente());
            if(cpfProf != null && cpfPaci != null) {
                Agendamento agend = new Agendamento();
                agend.setCpfProfAgendamento(cpfProf);
                agend.setCpfPacienteAgendamento(cpfPaci);
                agend.setDataAgendamento(data.data_agendamento());
                agend.setHoraInicio(data.hora_agendamento());
                agend.setDuracao(data.duracao());
                agend.setLinkVideo(data.link_video());
                agend.setLembreteEnviado(data.lembrete_enviado());
                agend.setObservacoes(data.observacoes());
                agend.setStatus(data.status());
                agendamentoRepository.save(agend);
                return ResponseEntity.status(HttpStatus.CREATED).body(agend);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Agendamento> updateAgendamento(@RequestBody @Valid AgendamentoDto data,
                                                         @PathVariable int id) {
        try {
            Profissional cpfProf = profissionalRepository.findByCpfProf(data.cpf_prof());
            Paciente cpfPaci = pacienteRepository.findByCpfPaciente(data.cpf_paciente());
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
                        agendamentoRepository.save(result);
                        return ResponseEntity.status(HttpStatus.OK).body(result);
                    })
                    .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAgendamento(@PathVariable @Valid int id) {
        return agendamentoRepository.findById(id)
                .map(result -> {
                    agendamentoRepository.delete(result);
                    return ResponseEntity.status(HttpStatus.NO_CONTENT).<Void>build();
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

}
