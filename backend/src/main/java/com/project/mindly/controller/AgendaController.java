package com.project.mindly.controller;


import com.project.mindly.model.agenda.Agenda;
import com.project.mindly.model.agenda.AgendaDto;
import com.project.mindly.model.profissional.Profissional;
import com.project.mindly.repository.AgendaRepository;
import com.project.mindly.repository.ProfissionalRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/agenda")
public class AgendaController {

    @Autowired
    private AgendaRepository agendaRepository;

    @Autowired
    private ProfissionalRepository profissionalRepository;

    @GetMapping
    public List<Agenda> getAllAgenda() {
        return agendaRepository.findAll();
    }

    @GetMapping("/{idAgenda}")
    public ResponseEntity getAgendaById(@PathVariable @Valid int idAgenda) {
        return agendaRepository.findById(idAgenda)
                .map(result -> ResponseEntity.ok().body(result))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<Agenda> addAgenda(@RequestBody @Valid AgendaDto data) {
        try {
            Profissional cpfProf = profissionalRepository.findByCpfProf(data.cpf_prof());
            if (cpfProf == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            Agenda agenda = new Agenda();
            agenda.setCpfProfAgenda(cpfProf);
            agenda.setDiaDaSemana(data.diaDaSemana());
            agenda.setHoraInicio(data.horaInicio());
            agenda.setHoraFim(data.horaFim());
            agenda.setDuracao(data.duracao());
            agenda.setAtivo(data.ativo());
            agendaRepository.save(agenda);
            return ResponseEntity.status(HttpStatus.CREATED).body(agenda);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
