package com.project.mindly.model.view;


import com.project.mindly.model.agendamento.AgendamentoStatus;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "vw_agendamentos_profissional")
public class AgendamentosProfissionalView {

    @Id
    @Column(name = "cpf_paciente",length = 11)
    private String cpfPaciente;

    @Column(name = "nome_paciente",length = 155)
    private String namePaciente;

    @Column(name = "email_paciente",length = 155)
    private String emailPaciente;

    @Column(name = "telefone_paciente",length = 20)
    private String telefonePaciente;

    @Column(name = "data_agendamento")
    private LocalDate dataAgendamento;

    @Column(name = "hora_inicio")
    private LocalTime horaInicio;

    @Column(name = "duracao")
    private int duracao;

    @Column(name = "link_video")
    private String linkVideo;

    @Column(name = "lembrete_enviado")
    private int lembreteEnviado;

    @Column(name = "observacoes",length = 500)
    private String observacoes;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private AgendamentoStatus status;

    @Column(name = "quantidade_total")
    private int quantidadeTotal;

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

    public String getEmailPaciente() {
        return emailPaciente;
    }

    public void setEmailPaciente(String emailPaciente) {
        this.emailPaciente = emailPaciente;
    }

    public String getTelefonePaciente() {
        return telefonePaciente;
    }

    public void setTelefonePaciente(String telefonePaciente) {
        this.telefonePaciente = telefonePaciente;
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

    public String getLinkVideo() {
        return linkVideo;
    }

    public void setLinkVideo(String linkVideo) {
        this.linkVideo = linkVideo;
    }

    public int getLembreteEnviado() {
        return lembreteEnviado;
    }

    public void setLembreteEnviado(int lembreteEnviado) {
        this.lembreteEnviado = lembreteEnviado;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public AgendamentoStatus getStatus() {
        return status;
    }

    public void setStatus(AgendamentoStatus status) {
        this.status = status;
    }

    public int getQuantidadeTotal() {
        return quantidadeTotal;
    }

    public void setQuantidadeTotal(int quantidadeTotal) {
        this.quantidadeTotal = quantidadeTotal;
    }
}
