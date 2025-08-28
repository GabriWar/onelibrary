package com.example.restservice.model;

import jakarta.persistence.Entity;

@Entity
public class Student extends User {
    private String curso;

    public String getCurso() { return curso; }
    public void setCurso(String curso) { this.curso = curso; }

    @Override
    public String exibirDados() {
        return "Aluno: " + getNome() + " - Curso: " + curso;
    }
}
