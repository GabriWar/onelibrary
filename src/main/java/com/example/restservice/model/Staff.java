package com.example.restservice.model;

import jakarta.persistence.Entity;

@Entity
public class Staff extends User {
    private String departamento;

    public String getDepartamento() { return departamento; }
    public void setDepartamento(String departamento) { this.departamento = departamento; }

    @Override
    public String exibirDados() {
        return "Funcionário: " + getNome() + " - Departamento: " + departamento;
    }
}
