package com.example.restservice.model;

import jakarta.persistence.Embedded;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

@MappedSuperclass
public abstract class User {
    private String nome;
    @Id
    @Column(name = "cpf_id")
    private String cpf;

    @Embedded
    private Address endereco;

    private String contato;

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public Address getEndereco() { return endereco; }
    public void setEndereco(Address endereco) { this.endereco = endereco; }
    public String getContato() { return contato; }
    public void setContato(String contato) { this.contato = contato; }

    public abstract String exibirDados();
}
