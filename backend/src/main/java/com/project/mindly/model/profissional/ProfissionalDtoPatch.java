package com.project.mindly.model.profissional;

public record ProfissionalDtoPatch(String nome ,String crp, String email, String senha, String descricao,
                                   String especialidade, String endereco, String tel) {
}
