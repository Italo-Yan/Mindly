package com.project.mindly.controller;


import com.project.mindly.model.agenda.Agenda;
import com.project.mindly.dtos.agenda.AgendaDto;
import com.project.mindly.model.profissional.Profissional;
import com.project.mindly.service.AgendaService;
import com.project.mindly.service.ProfissionalService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agenda")
public class AgendaController {


    private static final Logger logger = LoggerFactory.getLogger(AgendaController.class);
    private final AgendaService agendaService;

    @Autowired
    public AgendaController(AgendaService agendaService) {
        this.agendaService = agendaService;
    }

    @GetMapping
    public List<Agenda> getAllAgenda() {
        List<Agenda> agenda = agendaService.findAgendaAll();
        logger.info("Total de agendas retornadas: {}",agenda.size());
        return agenda;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agenda> getAgendaById(@PathVariable @Valid int id) {
        return agendaService.findAgendaById(id)
                .map(result -> ResponseEntity.status(HttpStatus.OK).body(result))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/create")
    public ResponseEntity<Agenda> addAgenda(@RequestBody @Valid AgendaDto data) {
        try {
            Agenda agenda = agendaService.saveAgenda(data);
            return ResponseEntity.status(HttpStatus.CREATED).body(agenda);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            logger.error("Ocorreu um erro inesperado", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Agenda> updateAgenda(@RequestBody @Valid AgendaDto data, @PathVariable int id) {
        try {
            Agenda agenda = agendaService.updateAgenda(id,data);
            return ResponseEntity.status(HttpStatus.CREATED).body(agenda);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            logger.error("Ocorreu um erro inesperado", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAgenda(@PathVariable @Valid int id) {
        try {
            agendaService.deleteAgenda(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
