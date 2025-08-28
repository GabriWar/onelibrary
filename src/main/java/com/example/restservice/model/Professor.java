package com.example.restservice.model;

import jakarta.persistence.Entity;

@Entity
public class Professor extends User {
    private String departamento;
    private String formacao;
    private String areaAtuacao;

    public String getDepartamento() { return departamento; }
    public void setDepartamento(String departamento) { this.departamento = departamento; }
    public String getFormacao() { return formacao; }
    public void setFormacao(String formacao) { this.formacao = formacao; }
    public String getAreaAtuacao() { return areaAtuacao; }
    public void setAreaAtuacao(String areaAtuacao) { this.areaAtuacao = areaAtuacao; }

    @Override
    public String exibirDados() {
        return "Professor: " + getNome() + " - Departamento: " + departamento;
    }
}
