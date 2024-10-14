package com.project.mindly.controller;


import com.project.mindly.model.profissional.Profissional;
import com.project.mindly.model.profissional.ProfissionalDto;
import com.project.mindly.repository.ProfissionalRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/profissional")
public class ProfissionalController {

    @Autowired
    private ProfissionalRepository profissionalRepository;

    @GetMapping
    public List<Profissional> getAll() {
        return profissionalRepository.findAll();
    }

    @GetMapping("/{cpf_prof}")
    public ResponseEntity<Profissional> getByCpf(@PathVariable @Valid String cpf_prof) {
        return profissionalRepository.findById(cpf_prof)
                .map(result -> ResponseEntity.ok().body(result))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
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
