package com.project.mindly.controller;

import com.project.mindly.model.view.ProfissionalPublicoView;
import com.project.mindly.service.ProfissionalPublicoService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/profissionais/publico")
public class ProfissionalPublicoController {

    private static final Logger logger = LoggerFactory.getLogger(ProfissionalPublicoController.class);
    private final ProfissionalPublicoService profissionalPublicoService;

    @Autowired
    public ProfissionalPublicoController(ProfissionalPublicoService profissionalPublicoService) {
        this.profissionalPublicoService = profissionalPublicoService;
    }

    @GetMapping
    public List<ProfissionalPublicoView> getProfissionalPublicoViewAll() {
        List<ProfissionalPublicoView> profissionalPublicoView = profissionalPublicoService.findProfissionalPublicoAll();
        logger.info("ProfissionalPublicoView: {}", profissionalPublicoView);
        return profissionalPublicoView;
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<ProfissionalPublicoView> getProfissionalPublicoViewById(@PathVariable @Valid String cpf) {
        return profissionalPublicoService.findrofissionalPublicoByCpf(cpf)
                .map(result -> ResponseEntity.status(HttpStatus.OK).body(result))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());

    }
}
