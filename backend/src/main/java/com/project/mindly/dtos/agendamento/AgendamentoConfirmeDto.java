package com.project.mindly.dtos.agendamento;

import com.project.mindly.model.agendamento.AgendamentoStatus;

public record AgendamentoConfirmeDto(int idAgendamento, AgendamentoStatus status) {
}
