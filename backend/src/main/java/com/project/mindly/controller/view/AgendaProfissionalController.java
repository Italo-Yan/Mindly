package com.project.mindly.controller.view;

import com.project.mindly.model.view.AgendaProfissionalView;
import com.project.mindly.service.view.AgendaProfissionalService;
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
@RequestMapping("/agenda/profissional")
public class AgendaProfissionalController {

    private static final Logger logger = LoggerFactory.getLogger(AgendaProfissionalController.class);
    private final AgendaProfissionalService agendaProfissionalService;


    @Autowired
    public AgendaProfissionalController(AgendaProfissionalService agendaProfissionalService) {
        this.agendaProfissionalService = agendaProfissionalService;
    }

    @GetMapping
    public List<AgendaProfissionalView> getProfissionalPublicoAll() {
        List<AgendaProfissionalView> agendaProfissionalView = agendaProfissionalService.findAgendaProfissionalAll();
        logger.info("Agendas: {}", agendaProfissionalView);
        return agendaProfissionalView;
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<AgendaProfissionalView> getProfissionalPublicoByCpf(@PathVariable @Valid String cpf) {
        return agendaProfissionalService.findAgendaProfissionalByCpf(cpf)
                .map(result -> ResponseEntity.status(HttpStatus.OK).body(result))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());

    }
}
