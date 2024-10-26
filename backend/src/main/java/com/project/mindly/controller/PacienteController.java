package com.project.mindly.controller;


import com.project.mindly.model.paciente.Paciente;
import com.project.mindly.model.paciente.PacienteDto;
import com.project.mindly.model.paciente.PacienteDtoPatch;
import com.project.mindly.repository.PacienteRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/paciente")
public class PacienteController {

    private final PacienteRepository pacienteRepository;

    public PacienteController(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @GetMapping
    public List<Paciente> findAllPaciente() {
        return pacienteRepository.findAll();
    }

    @GetMapping("/{cpfPaciente}")
    public ResponseEntity<Paciente> findByIdPaciente(@PathVariable @Valid String cpfPaciente) {
        return pacienteRepository.findById(cpfPaciente)
                .map( result -> ResponseEntity.status(HttpStatus.OK).body(result))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/create")
    public ResponseEntity<Paciente> createPaciente(@RequestBody @Valid PacienteDto data) {
        try {
            Paciente paciente = new Paciente();
            paciente.setCpfPaciente(data.cpf_paciente());
            paciente.setNomePaciente(data.nome_paciente());
            paciente.setEmailPaciente(data.email_paciente());
            paciente.setSenha(data.senha());
            paciente.setNascimento(data.nascimento());
            paciente.setMedicacao(data.medicacao());
            paciente.setEndPaciente(data.endereco_paciente());
            paciente.setTelPaciente(data.telefone_paciente());
            pacienteRepository.save(paciente);
            return ResponseEntity.status(HttpStatus.CREATED).body(paciente);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PatchMapping("/{cpfPaciente}")
    public ResponseEntity<Paciente> updatePaciente(@RequestBody @Valid PacienteDtoPatch data, @PathVariable @Valid String cpfPaciente) {

        return pacienteRepository.findById(cpfPaciente)
                .map(result -> {
                    result.setNomePaciente(data.nome_paciente());
                    result.setEmailPaciente(data.email_paciente());
                    result.setSenha(data.senha());
                    result.setNascimento(data.nascimento());
                    result.setMedicacao(data.medicacao());
                    result.setEndPaciente(data.endereco_paciente());
                    result.setTelPaciente(data.telefone_paciente());
                    pacienteRepository.save(result);
                    return ResponseEntity.status(HttpStatus.OK).body(result);
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{cpfPaciente}")
    public ResponseEntity<Void> deletePaciente(@PathVariable @Valid String cpfPaciente) {
        return pacienteRepository.findById(cpfPaciente)
                .map(result -> {
                    pacienteRepository.delete(result);
                    return ResponseEntity.status(HttpStatus.NO_CONTENT).<Void>build();
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
