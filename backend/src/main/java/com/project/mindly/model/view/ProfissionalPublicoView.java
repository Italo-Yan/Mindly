package com.project.mindly.model.view;


import jakarta.persistence.*;

@Entity
@Table(name ="vw_profissional_publico")
public class ProfissionalPublicoView {


    @Id
    @Column(name = "crp",length = 10)
    private String crp;

    @Column(name = "nome_prof",length = 155)
    private String nomeProf;

    @Column(name = "abordagem_teorica")
    private String abordagemTeorica;

    @Column(name = "descricao_prof",length = 500)
    private String descricaoProf;



    public String getNomeProf() {
        return nomeProf;
    }

    public void setNomeProf(String nomeProf) {
        this.nomeProf = nomeProf;
    }

    public String getAbordagemTeorica() {
        return abordagemTeorica;
    }

    public void setAbordagemTeorica(String abordagemTeorica) {
        this.abordagemTeorica = abordagemTeorica;
    }

    public String getDescricaoProf() {
        return descricaoProf;
    }

    public void setDescricaoProf(String descricaoProf) {
        this.descricaoProf = descricaoProf;
    }

    public String getCrp() {
        return crp;
    }

    public void setCrp(String crp) {
        this.crp = crp;
    }
}
