package com.project.mindly.controller;


import com.project.mindly.dtos.userAuth.UserAuth;
import com.project.mindly.model.paciente.Paciente;
import com.project.mindly.dtos.paciente.PacienteDto;
import com.project.mindly.dtos.paciente.PacienteDtoPatch;
import com.project.mindly.security.TokenService;
import com.project.mindly.service.AuthService;
import com.project.mindly.service.PacienteService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paciente")
public class PacienteController {


    private static final Logger logger = LoggerFactory.getLogger(PacienteController.class);
    private final PacienteService pacienteService;
    private final AuthService authService;


    @Autowired
    public PacienteController(PacienteService pacienteService, AuthService authService){
        this.pacienteService = pacienteService;
        this.authService = authService;

    }

    @GetMapping
    public List<Paciente> getAllPaciente() {
        List<Paciente> paciente = pacienteService.findAllPaciente();
        logger.info("Total de pacientes retornados: {}", paciente.size());
        return paciente;
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Paciente> getByIdPaciente(@PathVariable @Valid String cpf) {
        return pacienteService.findPacienteById(cpf)
                .map( result -> ResponseEntity.status(HttpStatus.OK).body(result))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/create")
    public ResponseEntity<?> createPaciente(@RequestBody @Valid PacienteDto data) {
        try {
            Paciente paciente = pacienteService.savePaciente(data);
            return ResponseEntity.status(HttpStatus.CREATED).body(paciente);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
        } catch (Exception e) {
            logger.error("Ocorreu um erro inesperado", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping("/{cpf}")
    public ResponseEntity<Paciente> updatePaciente(@RequestBody @Valid PacienteDtoPatch data,
                                                   @PathVariable @Valid String cpf) {
        try {
            Paciente paciente = pacienteService.updatePaciente(cpf, data);
            return ResponseEntity.status(HttpStatus.OK).body(paciente);
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
