package com.project.mindly.model.view;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

@Entity
@Table(name ="vw_profissional_publico")
public class ProfissionalPublicoView {


    @Id
    @Column(name = "cpf_prof",length = 20)
    private String cpfProf;

    @Column(name = "nome_prof",length = 155)
    private String nomeProf;

    @Column(name = "email_prof",length = 155)
    @Email
    private String emailProf;

    @Column(name = "abordagem_teorica")
    private String abordagemTeorica;

    @Column(name = "descricao_prof",length = 500)
    private String descricaoProf;

    @Column(name = "telefone_prof",length = 20)
    private String telefoneProf;

    @Column(name = "crp",length = 10)
    private String crp;

    public String getCpfProf() {
        return cpfProf;
    }

    public void setCpfProf(String cpfProf) {
        this.cpfProf = cpfProf;
    }

    public String getNomeProf() {
        return nomeProf;
    }

    public void setNomeProf(String nomeProf) {
        this.nomeProf = nomeProf;
    }

    public String getEmailProf() {
        return emailProf;
    }

    public void setEmailProf(String emailProf) {
        this.emailProf = emailProf;
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

    public String getTelefoneProf() {
        return telefoneProf;
    }

    public void setTelefoneProf(String telefoneProf) {
        this.telefoneProf = telefoneProf;
    }

    public String getCrp() {
        return crp;
    }

    public void setCrp(String crp) {
        this.crp = crp;
    }
}
