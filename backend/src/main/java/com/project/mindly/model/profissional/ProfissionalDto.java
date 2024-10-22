package com.project.mindly.model.profissional;

public record ProfissionalDto(String cpf ,String nome ,String crp, String email, String senha, String descricao,
                              String especialidade, String endereco, String tel) {
}
