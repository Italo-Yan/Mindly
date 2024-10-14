package com.project.mindly.model.agenda;

public enum AgendaAtivo {

    ATIVO("ativo"),
    INATIVO("inativo");

    private String status;

    AgendaAtivo(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
