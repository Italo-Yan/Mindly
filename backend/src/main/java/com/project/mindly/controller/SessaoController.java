package com.project.mindly.controller;

import com.project.mindly.model.sessao.Sessao;
import com.project.mindly.model.sessao.SessaoDto;
import com.project.mindly.model.sessao.SessaoDtoResponse;
import com.project.mindly.service.PacienteService;
import com.project.mindly.service.SessaoService;
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
@RequestMapping("/sessao")
public class SessaoController {

    private final SessaoService sessaoService;
    private static final Logger logger = LoggerFactory.getLogger(SessaoController.class);


    public SessaoController(SessaoService sessaoService) {
        this.sessaoService = sessaoService;
    }

    @GetMapping
    public List<SessaoDtoResponse> getAllSessao(){
       List<SessaoDtoResponse> sessao = sessaoService.findAllSessao();
       logger.info("Total de sessoes retornadas: {}", sessao.size());
       return sessao;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sessao> getByIdSessao(@PathVariable @Valid int id) {
        return sessaoService.findSessaoById(id)
                .map(result -> ResponseEntity.status(HttpStatus.OK).body(result))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/create")
    public ResponseEntity<Sessao> createSessao(@RequestBody @Valid SessaoDto data) {
        try {
            Sessao sessao = sessaoService.saveSessao(data);
            return ResponseEntity.status(HttpStatus.CREATED).body(sessao);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Sessao> updateSessao(@RequestBody @Valid SessaoDto data,
                                               @PathVariable @Valid int id) {
        try {
            Sessao sessao = sessaoService.updateSessao(id,data);
            return ResponseEntity.status(HttpStatus.OK).body(sessao);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            logger.error("Ocorreu um erro inesperado", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Sessao> deleteSessao(@PathVariable @Valid int id) {
        try {
            sessaoService.deleteSessao(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
