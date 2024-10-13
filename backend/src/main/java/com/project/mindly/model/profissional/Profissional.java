package com.project.mindly.model.profissional;


import com.project.mindly.model.agenda.Agenda;
import com.project.mindly.model.agendamento.Agendamento;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_profissional")
public class Profissional {

    @Id
    @Column(name = "cpf_prof", length = 20, nullable = false, unique = true)
    @NotNull
    @Size(min = 11, max = 20)
    private String cpfProf;

    @Column(name = "nome_prof", length = 155, nullable = false)
    @NotNull
    @Size(min = 3, max = 155)
    private String nomeProf;

    @Column(name = "crp", length = 10, nullable = false, unique = true)
    @NotNull
    private String crp;

    @Column(name = "email_prof", length = 155, nullable = false, unique = true)
    @NotNull
    @Email
    private String emailProf;

    @Column(name = "senha",nullable = false)
    @NotNull
    @Size(min = 6, max = 255)
    private String senha;

    @Column(name = "descricao_prof", length = 500)
    private String descProf;

    @Column(name = "especialidade")
    private String especialidade;

    @Column(name = "endereco_prof")
    private String enderecoProf;

    @Column(name = "telefone_prof", length = 20)
    private String telefoneProf;

    @OneToMany(mappedBy = "cpfProfAgenda", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Agenda> agendas = new HashSet<>();

    @OneToMany(mappedBy = "profissional", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Agendamento> agendamentos = new HashSet<>();

    //@OneToMany(mappedBy = "profissional", cascade = CascadeType.ALL, orphanRemoval = true)
    //private Set<Sessao> sessoes = new HashSet<>();


    public @NotNull @Size(min = 11, max = 20) String getCpfProf() {
        return cpfProf;
    }

    public void setCpfProf(@NotNull @Size(min = 11, max = 20) String cpfProf) {
        this.cpfProf = cpfProf;
    }

    public @NotNull @Size(min = 1, max = 155) String getNomeProf() {
        return nomeProf;
    }

    public void setNomeProf(@NotNull @Size(min = 1, max = 155) String nomeProf) {
        this.nomeProf = nomeProf;
    }

    public @NotNull @Size(min = 1, max = 10) String getCrp() {
        return crp;
    }

    public void setCrp(@NotNull @Size(min = 1, max = 10) String crp) {
        this.crp = crp;
    }

    public @NotNull @Email @Size(max = 155) String getEmailProf() {
        return emailProf;
    }

    public void setEmailProf(@NotNull @Email @Size(max = 155) String emailProf) {
        this.emailProf = emailProf;
    }

    public @NotNull @Size(min = 6, max = 255) String getSenha() {
        return senha;
    }

    public void setSenha(@NotNull @Size(min = 6, max = 255) String senha) {
        this.senha = senha;
    }

    public @Size(max = 500) String getDescProf() {
        return descProf;
    }

    public void setDescProf(@Size(max = 500) String descricaoProf) {
        this.descProf = descricaoProf;
    }

    public @Size(max = 255) String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(@Size(max = 255) String especialidade) {
        this.especialidade = especialidade;
    }

    public @Size(max = 255) String getEnderecoProf() {
        return enderecoProf;
    }

    public void setEnderecoProf(@Size(max = 255) String enderecoProf) {
        this.enderecoProf = enderecoProf;
    }

    public @Size(max = 20) String getTelefoneProf() {
        return telefoneProf;
    }

    public void setTelefoneProf(@Size(max = 20) String telefoneProf) {
        this.telefoneProf = telefoneProf;
    }

    public Set<Agenda> getAgendas() {
        return agendas;
    }

    public void setAgendas(Set<Agenda> agendas) {
        this.agendas = agendas;
    }

    public Set<Agendamento> getAgendamentos() {
        return agendamentos;
    }

    public void setAgendamentos(Set<Agendamento> agendamentos) {
        this.agendamentos = agendamentos;
    }
}
