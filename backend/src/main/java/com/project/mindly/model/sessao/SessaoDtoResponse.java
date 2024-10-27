package com.project.mindly.model.sessao;

import com.project.mindly.model.agendamento.AgendamentoDtoResponse;

import java.time.LocalDate;

public class SessaoDtoResponse {
    private int id;
    private LocalDate dataSessao;
    private int quantidadeTotal;
    private String cpfProfissional;
    private String nomeProfissional;
    private String cpfPaciente;
    private String nomePaciente;
    private AgendamentoDtoResponse agendamento;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDataSessao() {
        return dataSessao;
    }

    public void setDataSessao(LocalDate dataSessao) {
        this.dataSessao = dataSessao;
    }

    public int getQuantidadeTotal() {
        return quantidadeTotal;
    }

    public void setQuantidadeTotal(int quantidadeTotal) {
        this.quantidadeTotal = quantidadeTotal;
    }

    public String getCpfProfissional() {
        return cpfProfissional;
    }

    public void setCpfProfissional(String cpfProfissional) {
        this.cpfProfissional = cpfProfissional;
    }

    public String getCpfPaciente() {
        return cpfPaciente;
    }

    public void setCpfPaciente(String cpfPaciente) {
        this.cpfPaciente = cpfPaciente;
    }

    public AgendamentoDtoResponse getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(AgendamentoDtoResponse agendamento) {
        this.agendamento = agendamento;
    }

    public String getNomeProfissional() {
        return nomeProfissional;
    }

    public void setNomeProfissional(String nomeProfissional) {
        this.nomeProfissional = nomeProfissional;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }
}
