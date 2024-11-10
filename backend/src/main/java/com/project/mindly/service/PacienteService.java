package com.project.mindly.service;

import com.project.mindly.enums.UserRoles;
import com.project.mindly.model.paciente.Paciente;
import com.project.mindly.dtos.paciente.PacienteDto;
import com.project.mindly.dtos.paciente.PacienteDtoPatch;
import com.project.mindly.repository.PacienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {


    private final PacienteRepository pacienteRepository;
    private final PasswordEncoder passwordEncoder;

    public PacienteService(PacienteRepository pacienteRepository, PasswordEncoder passwordEncoder) {
        this.pacienteRepository = pacienteRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Paciente> findAllPaciente() {
        return pacienteRepository.findAll();
    }

    public Optional<Paciente> findPacienteById(String id) {
        return Optional.ofNullable(pacienteRepository.findById(id) //  retorna um Optional ou um Optional.empty()
                .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado com o CPF: " + id)));
    }

    public Paciente savePaciente(PacienteDto data) {

        if (pacienteRepository.existsById(data.cpf_paciente())) {
            throw new DataIntegrityViolationException("Paciente já está cadastrado.");
        }
        Paciente paciente = new Paciente();
        paciente.setCpfPaciente(data.cpf_paciente());
        paciente.setNomePaciente(data.nome_paciente());
        paciente.setEmailPaciente(data.email_paciente());
        String result = passwordEncoder.encode(data.senha_paciente());
        paciente.setSenha(result);
        paciente.setNascimento(data.nascimento_paciente());
        paciente.setEndPaciente(data.endereco_paciente());
        paciente.setTelPaciente(data.telefone_paciente());
        paciente.setRoles(UserRoles.PACIENTE);
        return pacienteRepository.save(paciente);

    }

    public Paciente updatePaciente(String cpf, PacienteDtoPatch data) {
        Paciente paciente = pacienteRepository.findById(cpf)
                .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado com CPF: " + cpf));
        paciente.setNomePaciente(data.nome_paciente());
        paciente.setEmailPaciente(data.email_paciente());
        paciente.setSenha(data.senha_paciente());
        paciente.setNascimento(data.nascimento_paciente());
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
