package com.project.mindly.model.paciente;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.project.mindly.model.agendamento.Agendamento;
import com.project.mindly.model.sessao.Sessao;
import com.project.mindly.enums.UserRoles;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tb_paciente")
public class Paciente implements UserDetails {

    @Id
    @Column(name = "cpf_paciente",length = 20, nullable = false, unique = true)
    @NotNull
    @Size(min = 11, max = 20)
    private String cpfPaciente;

    @Column(name = "nome_paciente", length = 155, nullable = false)
    @NotNull
    private String nomePaciente;

    @Column(name = "email_paciente", length = 155, nullable = false)
    @NotNull
    @Email
    @Size(min = 11, max = 155)
    private String emailPaciente;

    @Column(name = "senha",nullable = false)
    @NotNull
    @Size(min = 6, max = 255)
    private String senha;

    @Column(name = "nascimento",nullable = false)
    @NotNull
    private LocalDate nascimento;

    @Column(name = "medicacao")
    private String medicacao;

    @Column(name = "endereco_paciente")
    private String endPaciente;

    @Column(name = "telefone_paciente")
    private String telPaciente;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRoles roles;

    @OneToMany(mappedBy = "cpfPacienteAgendamento", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private Set<Agendamento> agendamentoPaciente = new HashSet<>();

    @OneToMany(mappedBy = "cpfPacienteSessao", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private Set<Sessao> sessaoPaciente = new HashSet<>();

    public @NotNull @Size(min = 11, max = 20) String getCpfPaciente() {
        return cpfPaciente;
    }

    public void setCpfPaciente(@NotNull @Size(min = 11, max = 20) String cpfPaciente) {
        this.cpfPaciente = cpfPaciente;
    }

    public @NotNull String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(@NotNull String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    public @NotNull @Size(min = 11, max = 155) String getEmailPaciente() {
        return emailPaciente;
    }

    public void setEmailPaciente(@NotNull @Size(min = 11, max = 155) String emailPaciente) {
        this.emailPaciente = emailPaciente;
    }

    public @NotNull @Size(min = 6, max = 255) String getSenha() {
        return senha;
    }

    public void setSenha(@NotNull @Size(min = 6, max = 255) String senha) {
        this.senha = senha;
    }

    public @NotNull LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(@NotNull LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public String getMedicacao() {
        return medicacao;
    }

    public void setMedicacao(String medicacao) {
        this.medicacao = medicacao;
    }

    public String getEndPaciente() {
        return endPaciente;
    }

    public void setEndPaciente(String endPaciente) {
        this.endPaciente = endPaciente;
    }

    public String getTelPaciente() {
        return telPaciente;
    }

    public void setTelPaciente(String telPaciente) {
        this.telPaciente = telPaciente;
    }

    public Set<Agendamento> getAgendamentoPaciente() {
        return agendamentoPaciente;
    }

    public void setAgendamentoPaciente(Set<Agendamento> agendamentoPaciente) {
        this.agendamentoPaciente = agendamentoPaciente;
    }

    public Set<Sessao> getSessaoPaciente() {
        return sessaoPaciente;
    }

    public void setSessaoPaciente(Set<Sessao> sessaoPaciente) {
        this.sessaoPaciente = sessaoPaciente;
    }

    public UserRoles getRoles() {
        return roles;
    }

    public void setRoles(UserRoles roles) {
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.roles == UserRoles.PROFISSIONAL) return List.of(new SimpleGrantedAuthority("ROLE_PROFISSIONAL"));
        else return List.of(new SimpleGrantedAuthority("ROLE_PACIENTE"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return emailPaciente;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
