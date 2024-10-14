package com.project.mindly.model.agenda;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.project.mindly.model.profissional.Profissional;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;


import java.time.LocalTime;


@Entity
@Table(name = "tb_agenda")
public class Agenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    @NotNull
    private int id;

    @Column(name = "duracao", nullable = false)
    @NotNull
    private int duracao;

    @Column(name = "dia_da_semana", nullable = false, length = 15)
    @NotNull
    private String diaDaSemana;

    @Column(name = "hora_inicio", nullable = false)
    @NotNull
    private LocalTime horaInicio;

    @Column(name = "hora_fim", nullable = false)
    @NotNull
    private LocalTime horaFim;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "ativo", nullable = false)
    private AgendaAtivo ativo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cpf_prof", nullable = false)
    @JsonManagedReference
    @NotNull
    private Profissional cpfProfAgenda;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public String getDiaDaSemana() {
        return diaDaSemana;
    }

    public void setDiaDaSemana(String diaDaSemana) {
        this.diaDaSemana = diaDaSemana;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(LocalTime horaFim) {
        this.horaFim = horaFim;
    }

    public AgendaAtivo getAtivo() {
        return ativo;
    }

    public void setAtivo(AgendaAtivo ativo) {
        this.ativo = ativo;
    }

    public Profissional getCpfProfAgenda() {
        return cpfProfAgenda;
    }

    public void setCpfProfAgenda(Profissional profissional) {
        this.cpfProfAgenda = profissional;
    }
}
