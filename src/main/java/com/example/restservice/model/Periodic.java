package com.example.restservice.model;

import jakarta.persistence.Entity;

@Entity
public class Periodic extends Work {
    private String tipo; // científico, informativo, diverso

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    @Override
    public String exibirInformacoes() {
        return "Periódico: " + getTitulo() + " - Tipo: " + tipo;
    }
}
