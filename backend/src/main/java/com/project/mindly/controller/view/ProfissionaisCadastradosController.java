package com.project.mindly.controller.view;



import com.project.mindly.model.view.ProfissionaisCadastradosView;
import com.project.mindly.service.view.ProfissionaisCadastradosService;
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
@RequestMapping("/profissional-cadastrados")
public class ProfissionaisCadastradosController {

    private static final Logger logger = LoggerFactory.getLogger(ProfissionaisCadastradosController.class);
    private final ProfissionaisCadastradosService profissionaisCadastradosService;

    @Autowired
    public ProfissionaisCadastradosController(ProfissionaisCadastradosService profissionaisCadastradosService) {
        this.profissionaisCadastradosService = profissionaisCadastradosService;
    }

    @GetMapping
    public List<ProfissionaisCadastradosView> getProfissionaisCadastradosAll() {
        List<ProfissionaisCadastradosView> perfilPacienteProfissionalViews = profissionaisCadastradosService.findProfissionaisCadastradosAll();
        logger.info("Profissional: {}", perfilPacienteProfissionalViews.size());
        return perfilPacienteProfissionalViews;
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<ProfissionaisCadastradosView> getProfissionaisCadastradosByCpf(@PathVariable @Valid String cpf) {
        return profissionaisCadastradosService.findProfissionaisCadastradosByCpf(cpf)
                .map(result-> ResponseEntity.status(HttpStatus.OK).body(result))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
