package com.project.mindly.controller;


import com.project.mindly.model.agenda.Agenda;
import com.project.mindly.model.agenda.AgendaDto;
import com.project.mindly.model.profissional.Profissional;
import com.project.mindly.repository.AgendaRepository;
import com.project.mindly.repository.ProfissionalRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agenda")
public class AgendaController {


    private final AgendaRepository agendaRepository;


    private final ProfissionalRepository profissionalRepository;

    public AgendaController(AgendaRepository agendaRepository, ProfissionalRepository profissionalRepository) {
        this.agendaRepository = agendaRepository;
        this.profissionalRepository = profissionalRepository;
    }

    @GetMapping
    public List<Agenda> getAllAgenda() {
        return agendaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agenda> getAgendaById(@PathVariable @Valid int id) {
        return agendaRepository.findById(id)
                .map(result -> ResponseEntity.status(HttpStatus.OK).body(result))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/create")
    public ResponseEntity<Agenda> addAgenda(@RequestBody @Valid AgendaDto data) {
        try {
            Profissional cpfProf = profissionalRepository.findByCpfProf(data.cpf_prof());
            if (cpfProf != null) {
                Agenda agenda = new Agenda();
                agenda.setCpfProfAgenda(cpfProf);
                agenda.setDiaDaSemana(data.diaDaSemana());
                agenda.setHoraInicio(data.horaInicio());
                agenda.setHoraFim(data.horaFim());
                agenda.setDuracao(data.duracao());
                agenda.setAtivo(data.ativo());
                agendaRepository.save(agenda);
                return ResponseEntity.status(HttpStatus.CREATED).body(agenda);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Agenda> updateAgenda(@RequestBody @Valid AgendaDto data, @PathVariable int id) {
        Profissional cpfProf = profissionalRepository.findByCpfProf(data.cpf_prof());
        if (cpfProf != null) {
            return agendaRepository.findById(id)
                    .map(result -> {
                        result.setCpfProfAgenda(cpfProf);
                        result.setAtivo(data.ativo());
                        result.setDuracao(data.duracao());
                        result.setHoraInicio(data.horaInicio());
                        result.setHoraFim(data.horaFim());
                        result.setDiaDaSemana(data.diaDaSemana());
                        Agenda updatedAgenda = agendaRepository.save(result);
                        return ResponseEntity.status(HttpStatus.OK).body(updatedAgenda);
                    })
                    .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAgenda(@PathVariable @Valid int id) {
        return agendaRepository.findById(id)
                .map(result -> {
                    agendaRepository.delete(result);
                    return ResponseEntity.status(HttpStatus.NO_CONTENT).<Void>build();
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
