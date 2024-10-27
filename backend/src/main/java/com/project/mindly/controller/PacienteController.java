package com.project.mindly.controller;


import com.project.mindly.model.paciente.Paciente;
import com.project.mindly.model.paciente.PacienteDto;
import com.project.mindly.model.paciente.PacienteDtoPatch;
import com.project.mindly.service.PacienteService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paciente")
public class PacienteController {


    private final PacienteService pacienteService;
    private static final Logger logger = LoggerFactory.getLogger(PacienteController.class);


    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping
    public List<Paciente> findAllPaciente() {
        List<Paciente> pacientes = pacienteService.findAllPaciente();
        logger.info("Total de pacientes retornados: {}", pacientes.size());
        return pacientes;
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Paciente> findByIdPaciente(@PathVariable @Valid String cpf) {
        return pacienteService.findPacienteById(cpf)
                .map( result -> ResponseEntity.status(HttpStatus.OK).body(result))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/create") // método base para replicar nos outros
    public ResponseEntity<Paciente> createPaciente(@RequestBody @Valid PacienteDto data) {
        try {
            Paciente paciente = pacienteService.savePaciente(data);
            return ResponseEntity.status(HttpStatus.CREATED).body(paciente);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            logger.error("Ocorreu um erro inesperado", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



    @PatchMapping("/{cpf}")
    public ResponseEntity<Paciente> updatePaciente(@RequestBody @Valid PacienteDtoPatch data,
                                                   @PathVariable @Valid String cpf) {
        try {
            Paciente updatedPaciente = pacienteService.updatePaciente(cpf, data);
            return ResponseEntity.status(HttpStatus.OK).body(updatedPaciente);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            logger.error("Ocorreu um erro inesperado", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @DeleteMapping("/{cpf}")
    public ResponseEntity<String> deletePaciente(@PathVariable @Valid String cpf) {
        try {
            pacienteService.deletePaciente(cpf);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
