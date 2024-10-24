package com.project.mindly.model.agendamento;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.project.mindly.config.LocalTimeDeserializer;

import java.time.LocalDate;
import java.time.LocalTime;

public record AgendamentoDto(String cpf_prof, String cpf_paciente,
                             LocalDate data_agendamento,
                             @JsonDeserialize(using = LocalTimeDeserializer.class) LocalTime hora_agendamento,
                             int duracao, String link_video, int lembrete_enviado,
                             String observacoes, AgendamentoStatus status) {
}
