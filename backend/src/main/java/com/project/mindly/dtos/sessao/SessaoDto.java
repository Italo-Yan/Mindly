package com.project.mindly.dtos.sessao;

import java.time.LocalDate;

public record SessaoDto(String cpf_prof, String cpf_paciente, int id_agendamento, LocalDate data_sessao,
                        int quantidade_total) {
}
