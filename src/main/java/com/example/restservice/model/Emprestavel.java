package com.example.restservice.model;

public interface Emprestavel {
    boolean isDisponivel();
    void emprestar(String usuarioCpf);
    void devolver();
}
