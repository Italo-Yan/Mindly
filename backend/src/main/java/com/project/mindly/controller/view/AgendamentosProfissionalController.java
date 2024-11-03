package com.project.mindly.controller.view;


import com.project.mindly.model.view.AgendamentosProfissionalView;
import com.project.mindly.service.view.AgendamentosProfissionalService;
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
@RequestMapping("/agendamento/profissional")
public class AgendamentosProfissionalController {

    private static final Logger logger = LoggerFactory.getLogger(AgendamentosProfissionalController.class);
    private final AgendamentosProfissionalService agendamentosProfissionalService;

    @Autowired
    public AgendamentosProfissionalController(AgendamentosProfissionalService agendamentosProfissionalService) {
        this.agendamentosProfissionalService = agendamentosProfissionalService;
    }

    @GetMapping
    public List<AgendamentosProfissionalView> getAgendamentosProfissionalViewAll() {
        List<AgendamentosProfissionalView> agendamentosProfissionalView= agendamentosProfissionalService.findAgendamentoProfissionalAll();
        logger.info("Agendamentos: {}", agendamentosProfissionalView);
        return agendamentosProfissionalView;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendamentosProfissionalView> getAgendamentosProfissionalById(@PathVariable @Valid int id) {
        return agendamentosProfissionalService.findAgendamentoProfissionalById(id)
                .map(result -> ResponseEntity.status(HttpStatus.OK).body(result))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());

    }
}
