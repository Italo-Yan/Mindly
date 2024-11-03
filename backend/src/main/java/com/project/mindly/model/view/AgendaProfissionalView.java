package com.project.mindly.model.view;


import com.project.mindly.model.agendamento.AgendamentoStatus;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;


@Entity
@Table(name = "vw_agenda_profissional")
public class AgendaProfissionalView {

    @Id
    @Column(name = "cpf_prof",length = 11)
    private String cpfProf;

    @Column(name = "nome_prof",length = 155)
    private String nomeProf;

    @Column(name = "data_agendamento")
    private LocalDate dataAgendamento;

    @Column(name = "hora_inicio")
    private LocalTime horaInicio;

    @Column(name = "duracao")
    private int duracao;

    @Column(name = "status")
    private AgendamentoStatus status = AgendamentoStatus.PENDENTE;

    @Column(name = "status_agendamento")
    private String statusAgendamento;

    public String getCpfProf() {
        return cpfProf;
    }

    public void setCpfProf(String cpfProf) {
        this.cpfProf = cpfProf;
    }

    public String getNomeProf() {
        return nomeProf;
    }

    public void setNomeProf(String nomeProf) {
        this.nomeProf = nomeProf;
    }

    public LocalDate getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(LocalDate dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public AgendamentoStatus getStatus() {
        return status;
    }

    public void setStatus(AgendamentoStatus status) {
        this.status = status;
    }

    public String getStatusAgendamento() {
        return statusAgendamento;
    }

    public void setStatusAgendamento(String statusAgendamento) {
        this.statusAgendamento = statusAgendamento;
    }
}
