package com.project.mindly.model.view;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "vw_profissionais_cadastrados")
public class ProfissionaisCadastradosView {

    @Id
    @Column(name = "cpf_prof",length = 20)
    private String cpfProf;

    @Column(name = "nome_prof",length = 155)
    private String nomeProf;

    @Column(name = "abordagem_teorica")
    private String abordagemTeorica;

    @Column(name = "descricao_prof",length = 500)
    private String descricaoProf;

    @Column(name = "telefone_prof",length = 20)
    private String telefoneProf;

    @Column(name = "email_prof",length = 155)
    private String emailProf;

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

    public String getAbordagemTeorica() {
        return abordagemTeorica;
    }

    public void setAbordagemTeorica(String abordagemTecnica) {
        this.abordagemTeorica = abordagemTecnica;
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

    public String getEmailProf() {
        return emailProf;
    }

    public void setEmailProf(String emailProf) {
        this.emailProf = emailProf;
    }

    public String getCrp() {
        return crp;
    }

    public void setCrp(String crp) {
        this.crp = crp;
    }
}
