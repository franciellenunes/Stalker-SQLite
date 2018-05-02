package com.example.root.stalkersqlite;

public class Vitima {

    private long id;
    private String nome;
    private String emprego;
    private String dataNasc;
    private String telefone;
    private String descricao;

    public Vitima(long id, String nome){
        this.id = id;
        this.nome = nome;
    }

    public Vitima(long id, String nome, String emprego, String dataNasc, String telefone, String descricao) {
        this.id = id;
        this.nome = nome;
        this.emprego = emprego;
        this.dataNasc = dataNasc;
        this.telefone = telefone;
        this.descricao = descricao;
    }

    public Vitima (String nome, String emprego, String dataNasc, String telefone, String descricao) {
        this.nome = nome;
        this.emprego = emprego;
        this.dataNasc = dataNasc;
        this.telefone = telefone;
        this.descricao = descricao;
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmprego() {
        return emprego;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setId(long id) { this.id = id; }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmprego(String emprego) {
        this.emprego = emprego;
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
