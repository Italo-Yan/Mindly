package com.project.mindly.controller.view;

import com.project.mindly.model.view.ProfissionalPublicoView;
import com.project.mindly.service.view.ProfissionalPublicoService;
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
    public List<ProfissionalPublicoView> getProfissionalPublicoAll() {
        List<ProfissionalPublicoView> profissionalPublicoView = profissionalPublicoService.findProfissionalPublicoAll();
        logger.info("Profissionais: {}", profissionalPublicoView.size());
        return profissionalPublicoView;
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<ProfissionalPublicoView>> getByNameProfisisonal(@PathVariable @Valid String nome) {
        return profissionalPublicoService.findProfissionalPublicoByName(nome)
                .map(result-> ResponseEntity.status(HttpStatus.OK).body(result))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/crp/{crp}")
    public ResponseEntity<ProfissionalPublicoView> getProfissionalPublicoByCrp(@PathVariable @Valid String crp) {
        return profissionalPublicoService.findProfissionalPublicoByCrp(crp)
                .map(result -> ResponseEntity.status(HttpStatus.OK).body(result))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());

    }
}
