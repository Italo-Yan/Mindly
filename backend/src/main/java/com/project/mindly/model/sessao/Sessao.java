package com.project.mindly.model.sessao;

import com.project.mindly.model.agendamento.Agendamento;
import com.project.mindly.model.paciente.Paciente;
import com.project.mindly.model.profissional.Profissional;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "tb_sessao")
public class Sessao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @NotNull
    private int id;

    @Column(name = "data_sessao", nullable = false)
    @NotNull
    private LocalDate dataSessao;

    @Column(name = "quantidade_total", nullable = false)
    @NotNull
    private int qnt_total = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cpf_prof", nullable = false)
    @NotNull
    private Profissional cpfProfSessao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cpf_paciente", nullable = false)
    @NotNull
    private Paciente cpfPacienteSessao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_agendamento", nullable = false)
    @NotNull
    private Agendamento agendamentoSessao;

    @NotNull
    public int getId() {
        return id;
    }

    public void setId(@NotNull int id) {
        this.id = id;
    }

    public @NotNull LocalDate getDataSessao() {
        return dataSessao;
    }

    public void setDataSessao(@NotNull LocalDate dataSessao) {
        this.dataSessao = dataSessao;
    }

    @NotNull
    public int getQnt_total() {
        return qnt_total;
    }

    public void setQnt_total(@NotNull int qnt_total) {
        this.qnt_total = qnt_total;
    }

    public @NotNull Profissional getCpfProfSessao() {
        return cpfProfSessao;
    }

    public void setCpfProfSessao(@NotNull Profissional cpfProfSessao) {
        this.cpfProfSessao = cpfProfSessao;
    }

    public @NotNull Paciente getCpfPacienteSessao() {
        return cpfPacienteSessao;
    }

    public void setCpfPacienteSessao(@NotNull Paciente cpfPacienteSessao) {
        this.cpfPacienteSessao = cpfPacienteSessao;
    }

    public @NotNull Agendamento getAgendamentoSessao() {
        return agendamentoSessao;
    }

    public void setAgendamentoSessao(@NotNull Agendamento agendamentoSessao) {
        this.agendamentoSessao = agendamentoSessao;
    }
}
