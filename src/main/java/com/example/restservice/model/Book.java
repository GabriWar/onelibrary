package com.example.restservice.model;

import jakarta.persistence.Entity;

@Entity
public class Book extends Work implements Emprestavel {
    private String categoriaCientifica;

    // emprestimo tracking
    private boolean disponivel = true;
    private String emprestadoParaCpf;

    public String getCategoriaCientifica() { return categoriaCientifica; }
    public void setCategoriaCientifica(String categoriaCientifica) { this.categoriaCientifica = categoriaCientifica; }

    public String getEmprestadoParaCpf() { return emprestadoParaCpf; }

    @Override
    public String exibirInformacoes() {
        return "Livro: " + getTitulo() + " (" + getAnoEdicao() + ")";
    }

    @Override
    public boolean isDisponivel() { return disponivel; }

    @Override
    public void emprestar(String usuarioCpf) {
        if (!disponivel) throw new IllegalStateException("Obra não disponível");
        this.disponivel = false;
        this.emprestadoParaCpf = usuarioCpf;
    }

    @Override
    public void devolver() {
        this.disponivel = true;
        this.emprestadoParaCpf = null;
    }
}
