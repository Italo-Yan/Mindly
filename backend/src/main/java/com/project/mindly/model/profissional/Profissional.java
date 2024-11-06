package com.project.mindly.model.profissional;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.project.mindly.model.agenda.Agenda;
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

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tb_profissional")
public class Profissional implements UserDetails {

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

    @Column(name = "abordagem_teorica")
    private String abordagemTeorica;

    @Column(name = "endereco_prof")
    private String enderecoProf;

    @Column(name = "telefone_prof", length = 20)
    private String telefoneProf;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRoles roles;

    @OneToMany(mappedBy = "cpfProfAgenda", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private Set<Agenda> agendas = new HashSet<>();

    @OneToMany(mappedBy = "cpfProfAgendamento", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private Set<Agendamento> agendamentos = new HashSet<>();

    @OneToMany(mappedBy = "cpfProfSessao", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private Set<Sessao> sessoes = new HashSet<>();


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

    public @Size(max = 255) String getAbordagemTeorica() {
        return abordagemTeorica;
    }

    public void setAbordagemTeorica(@Size(max = 255) String especialidade) {
        this.abordagemTeorica = especialidade;
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

    public Set<Sessao> getSessoes() {
        return sessoes;
    }

    public void setSessoes(Set<Sessao> sessoes) {
        this.sessoes = sessoes;
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
        return emailProf;
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
