package com.project.mindly.model.view;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;

import java.time.LocalDate;


@Entity
@Table(name = "vw_perfil_paciente_profissional")
public class PerfilPacienteProfissionalView {

    @Id
    @Column(name = "cpf_paciente",length = 11)
    private String cpfPaciente;

    @Column(name = "name_paciente",length = 155)
    private String namePaciente;

    @Column(name = "email_paciente",length = 155)
    @Email
    private String emailPaciente;

    @Column(name = "nascimento")
    private LocalDate nascimento;

    @Column(name = "medicacao")
    private String medicacao;

    @Column(name = "telefone_paciente",length = 20)
    private String telefonePaciente;

    public String getCpfPaciente() {
        return cpfPaciente;
    }

    public void setCpfPaciente(String cpfPaciente) {
        this.cpfPaciente = cpfPaciente;
    }

    public String getNamePaciente() {
        return namePaciente;
    }

    public void setNamePaciente(String namePaciente) {
        this.namePaciente = namePaciente;
    }

    public @Email String getEmailPaciente() {
        return emailPaciente;
    }

    public void setEmailPaciente(@Email String emailPaciente) {
        this.emailPaciente = emailPaciente;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public String getMedicacao() {
        return medicacao;
    }

    public void setMedicacao(String medicacao) {
        this.medicacao = medicacao;
    }

    public String getTelefonePaciente() {
        return telefonePaciente;
    }

    public void setTelefonePaciente(String telefonePaciente) {
        this.telefonePaciente = telefonePaciente;
    }
}
