package com.project.mindly.dtos.profissional;

public record ProfissionalDto(String cpf , String nome , String crp, String email, String senha, String descricao,
                              String abordagemTeorica, String endereco, String tel) {
}

