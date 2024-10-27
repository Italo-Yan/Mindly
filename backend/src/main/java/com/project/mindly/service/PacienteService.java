package com.project.mindly.service;

import com.project.mindly.model.paciente.Paciente;
import com.project.mindly.model.paciente.PacienteDto;
import com.project.mindly.model.paciente.PacienteDtoPatch;
import com.project.mindly.repository.PacienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {


    private final PacienteRepository pacienteRepository;

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public List<Paciente> findAllPaciente() {
        return pacienteRepository.findAll();
    }

    public Optional<Paciente> findPacienteById(String id) {
        return Optional.ofNullable(pacienteRepository.findById(id) //  retorna um Optional ou um Optional.empty()
                .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado com o CPF: " + id)));
    }

    public Paciente savePaciente(PacienteDto data) {
        Paciente paciente = new Paciente();
        paciente.setCpfPaciente(data.cpf_paciente());
        paciente.setNomePaciente(data.nome_paciente());
        paciente.setEmailPaciente(data.email_paciente());
        paciente.setSenha(data.senha());
        paciente.setNascimento(data.nascimento());
        paciente.setMedicacao(data.medicacao());
        paciente.setEndPaciente(data.endereco_paciente());
        paciente.setTelPaciente(data.telefone_paciente());
        return pacienteRepository.save(paciente);
    }

    public Paciente updatePaciente(String cpf, PacienteDtoPatch data) {
        Paciente paciente = pacienteRepository.findById(cpf)
                .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado com CPF: " + cpf));
        paciente.setNomePaciente(data.nome_paciente());
        paciente.setEmailPaciente(data.email_paciente());
        paciente.setSenha(data.senha());
        paciente.setNascimento(data.nascimento());
        paciente.setMedicacao(data.medicacao());
        paciente.setEndPaciente(data.endereco_paciente());
        paciente.setTelPaciente(data.telefone_paciente());
        return pacienteRepository.save(paciente);
    }

    public void deletePaciente(String cpf) {
        Paciente paciente = pacienteRepository.findById(cpf)
                .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado com CPF: " + cpf));
        pacienteRepository.delete(paciente);
    }

}
