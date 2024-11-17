package com.project.mindly.controller;


import com.project.mindly.model.profissional.Profissional;
import com.project.mindly.dtos.profissional.ProfissionalDto;
import com.project.mindly.dtos.profissional.ProfissionalDtoPatch;
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
@RequestMapping("/profissional")
public class ProfissionalController {


    private static final Logger logger = LoggerFactory.getLogger(ProfissionalController.class);
    private final ProfissionalService profissionalService;


    @Autowired
    public ProfissionalController(ProfissionalService profissionalService) {
        this.profissionalService = profissionalService;

    }

    @GetMapping
    public List<Profissional> getProfissionalAll() {
        List<Profissional> profissional = profissionalService.findAllProfissional();
        logger.info("Total Profissionals: {}", profissional.size());
        return profissional;
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Profissional> getByCpfProfissional(@PathVariable @Valid String cpf) {
        return profissionalService.findProfissionalById(cpf)
                .map(result -> ResponseEntity.status(HttpStatus.OK).body(result))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Profissional>> getByNameProfisisonal(@PathVariable @Valid String nome) {
        return profissionalService.findProfissionalByName(nome)
                .map(result-> ResponseEntity.status(HttpStatus.OK).body(result))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> getByEmailProfissional(@PathVariable @Valid String email) {
        return profissionalService.findProfissionalByEmail(email)
                .map(result-> ResponseEntity.status(HttpStatus.OK).body(result))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/create")
    public ResponseEntity<?> createProfissional (@RequestBody @Valid ProfissionalDto data) {
        try {
            Profissional profissional = profissionalService.saveProfissional(data);
            return ResponseEntity.status(HttpStatus.CREATED).body(profissional);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
        } catch (Exception e) {
            logger.error("Ocorreu um erro inesperado", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @PatchMapping("/{cpf}")
    public ResponseEntity<Profissional> updateProfissional(@RequestBody @Valid ProfissionalDtoPatch data,
                                                           @PathVariable @Valid String cpf) {

        try{
            Profissional profissional = profissionalService.updateProfissional(cpf,data);
            return ResponseEntity.status(HttpStatus.OK).body(profissional);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            logger.error("Ocorreu um error inesperado", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<String> deleteProfissional(@PathVariable @Valid String cpf) {
        try {
            profissionalService.deleteProfissional(cpf);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
