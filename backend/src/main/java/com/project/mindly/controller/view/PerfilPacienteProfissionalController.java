package com.project.mindly.controller.view;

import com.project.mindly.model.view.PerfilPacienteProfissionalView;
import com.project.mindly.service.view.PerfilPacienteProfissionalService;
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
@RequestMapping("/paciente/profissional")
public class PerfilPacienteProfissionalController {

    private static final Logger logger = LoggerFactory.getLogger(PerfilPacienteProfissionalController.class);
    private final PerfilPacienteProfissionalService perfilPacienteProfissionalService;

    @Autowired
    public PerfilPacienteProfissionalController(PerfilPacienteProfissionalService perfilPacienteProfissionalService) {
        this.perfilPacienteProfissionalService = perfilPacienteProfissionalService;
    }

    @GetMapping
    public List<PerfilPacienteProfissionalView> getPerfilPacienteProfissionalAll() {
        List<PerfilPacienteProfissionalView> perfilPacienteProfissionalViews = perfilPacienteProfissionalService.findPerfilPacienteProfissionalAll();
        logger.info("Pacientes", perfilPacienteProfissionalViews);
        return perfilPacienteProfissionalViews;
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<PerfilPacienteProfissionalView> getPerfilPacienteProfissionalByCpf(@PathVariable @Valid String cpf) {
        return perfilPacienteProfissionalService.findPerfilPacienteProfissionalById(cpf)
                .map(result-> ResponseEntity.status(HttpStatus.OK).body(result))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
