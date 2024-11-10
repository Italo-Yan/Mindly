package com.project.mindly.dtos.paciente;

import java.time.LocalDate;

public record PacienteDtoPatch(String nome_paciente, String email_paciente , String senha_paciente,
                               LocalDate nascimento_paciente, String medicacao, String endereco_paciente,
                               String telefone_paciente) {
}
