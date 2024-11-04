package com.project.mindly.dtos.agenda;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.project.mindly.config.LocalTimeDeserializer;
import com.project.mindly.enums.AgendaAtivo;

import java.time.LocalTime;

public record AgendaDto(String cpf_prof, String diaDaSemana,
                        @JsonDeserialize(using = LocalTimeDeserializer.class) LocalTime horaInicio,
                        @JsonDeserialize(using = LocalTimeDeserializer.class) LocalTime horaFim,
                        int duracao, AgendaAtivo ativo) {



}
