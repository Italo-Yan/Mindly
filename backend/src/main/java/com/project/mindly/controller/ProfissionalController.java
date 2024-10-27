package com.project.mindly.controller;


import com.project.mindly.model.profissional.Profissional;
import com.project.mindly.model.profissional.ProfissionalDto;
import com.project.mindly.model.profissional.ProfissionalDtoPatch;
import com.project.mindly.repository.ProfissionalRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profissional")
public class ProfissionalController {


    private final ProfissionalRepository profissionalRepository;

    public ProfissionalController(ProfissionalRepository profissionalRepository) {
        this.profissionalRepository = profissionalRepository;
    }

    @GetMapping
    public List<Profissional> getProfissionalAll() {
        return profissionalRepository.findAll();
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Profissional> getByCpfProfissional(@PathVariable @Valid String cpf) {
        return profissionalRepository.findById(cpf)
                .map(result -> ResponseEntity.status(HttpStatus.OK).body(result))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
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
            prof.setEspecialidade(prfDto.especialidade());
            prof.setEnderecoProf(prfDto.endereco());
            prof.setTelefoneProf(prfDto.tel());
            profissionalRepository.save(prof);
            return ResponseEntity.status(HttpStatus.CREATED).body(prof);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PatchMapping("/{cpf}")
    public ResponseEntity<Profissional> updateProfissional(@RequestBody @Valid ProfissionalDtoPatch data,
                                                           @PathVariable @Valid String cpf) {
        return profissionalRepository.findById(cpf)
                .map(result -> {
                    result.setTelefoneProf(data.tel());
                    result.setDescProf(data.descricao());
                    result.setEmailProf(data.email());
                    result.setSenha(data.senha());
                    result.setNomeProf(data.nome());
                    result.setCrp(data.crp());
                    result.setEnderecoProf(data.endereco());
                    result.setEspecialidade(data.especialidade());
                    profissionalRepository.save(result);
                    return ResponseEntity.status(HttpStatus.OK).body(result);
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> deleteProfissional(@PathVariable @Valid String cpf) {
        return profissionalRepository.findById(cpf)
                .map(result -> {
                    profissionalRepository.delete(result);
                    return ResponseEntity.status(HttpStatus.NO_CONTENT).<Void>build();
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

}
