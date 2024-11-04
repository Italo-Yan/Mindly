package com.project.mindly.dtos.agendamento;

import com.project.mindly.enums.AgendamentoStatus;

public record AgendamentoConfirmeDto(int idAgendamento, AgendamentoStatus status) {
}
