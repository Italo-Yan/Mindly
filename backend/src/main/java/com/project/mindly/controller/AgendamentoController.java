package com.project.mindly.controller;


import com.project.mindly.dtos.agendamento.AgendamentoConfirmeDto;
import com.project.mindly.model.agendamento.Agendamento;
import com.project.mindly.dtos.agendamento.AgendamentoDto;
import com.project.mindly.model.paciente.Paciente;
import com.project.mindly.model.profissional.Profissional;
import com.project.mindly.repository.AgendamentoRepository;
import com.project.mindly.repository.PacienteRepository;
import com.project.mindly.repository.ProfissionalRepository;
import com.project.mindly.service.AgendamentoService;
import com.project.mindly.service.PacienteService;
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

import javax.xml.crypto.Data;
import java.util.List;

@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {


    private static final Logger logger = LoggerFactory.getLogger(AgendamentoController.class);
    private final AgendamentoService agendamentoService;

    @Autowired
    public AgendamentoController(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;

    }

    @GetMapping
    public List<Agendamento> getAllAgendamento() {
        List<Agendamento> agendamento = agendamentoService.findAllAgendamento();
        logger.info("Total de agendamentos retornados: {}",agendamento.size());
        return agendamento;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agendamento> getByIdAgendamento(@PathVariable @Valid int id) {
        return agendamentoService.findAgendamentoById(id)
                .map(result -> ResponseEntity.status(HttpStatus.OK).body(result))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/create")
    public ResponseEntity<Agendamento> createAgendamento(@RequestBody @Valid AgendamentoDto data) {
        try {
            Agendamento agendamento = agendamentoService.saveAgendamento(data);
            return ResponseEntity.status(HttpStatus.CREATED).body(agendamento);
        }  catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            logger.error("Ocorreu um erro inesperado", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Agendamento> updateAgendamento(@RequestBody @Valid AgendamentoDto data,
                                                         @PathVariable int id) {
        try {
            Agendamento agendamento = agendamentoService.updateAgendamento(id,data);
            return ResponseEntity.status(HttpStatus.OK).body(agendamento);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e ) {
            logger.error("Ocorreu um erro inesperado", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAgendamento(@PathVariable @Valid int id) {
        try{
            agendamentoService.deleteAgendamento(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("confirmacao")
    public ResponseEntity<Agendamento> confirmeAgendamento(@RequestBody @Valid AgendamentoConfirmeDto data) {
        try {
            Agendamento agendamento = agendamentoService.confirmeAgendamento(data);
            return ResponseEntity.status(HttpStatus.OK).body(agendamento);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            logger.error("Ocorreu um erro inesperado", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
