package com.project.mindly.dtos.profissional;

public record ProfissionalDtoPatch(String nome , String crp, String email, String senha, String descricao,
                                   String abordagemTeorica, String endereco, String tel) {
}
