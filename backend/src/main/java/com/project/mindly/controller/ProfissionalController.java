package com.project.mindly.controller;


import com.project.mindly.model.profissional.Profissional;
import com.project.mindly.model.profissional.ProfissionalDto;
import com.project.mindly.repository.ProfissionalRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/profissional")
public class ProfissionalController {


    private final ProfissionalRepository profissionalRepository;

    public ProfissionalController(ProfissionalRepository profissionalRepository) {
        this.profissionalRepository = profissionalRepository;
    }

    @GetMapping
    public List<Profissional> getAll() {
        return profissionalRepository.findAll();
    }

    @PostMapping("create")
    public ResponseEntity<Profissional> createProfissional (@RequestBody @Valid ProfissionalDto prfDto) {
        try {
            Profissional prof = new Profissional();
            prof.setCpfProf(prfDto.cpf());
            prof.setNomeProf(prfDto.nome());
            prof.setCrp(prfDto.crp());
            prof.setEmailProf(prfDto.email());
            prof.setSenha(prfDto.senha());
            prof.setDescProf(prfDto.descricao());
            prof.setEspecialidade(prfDto.esp());
            prof.setEnderecoProf(prfDto.end());
            prof.setTelefoneProf(prfDto.tel());
            profissionalRepository.save(prof);
            return ResponseEntity.status(HttpStatus.CREATED).body(prof);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
