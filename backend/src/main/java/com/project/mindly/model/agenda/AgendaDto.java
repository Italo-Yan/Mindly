package com.project.mindly.model.agenda;

import java.time.LocalTime;

public record AgendaDto(String cpf_prof, String diaDaSemana,LocalTime horaInicio,LocalTime horaFim,int duracao,AgendaAtivo ativo) {


}
