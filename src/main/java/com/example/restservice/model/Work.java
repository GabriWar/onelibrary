package com.example.restservice.model;

import java.util.List;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Work {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String idioma;
    private int anoEdicao;

    @ManyToMany
    private List<Author> autores;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getIdioma() { return idioma; }
    public void setIdioma(String idioma) { this.idioma = idioma; }

    public int getAnoEdicao() { return anoEdicao; }
    public void setAnoEdicao(int anoEdicao) { this.anoEdicao = anoEdicao; }

    public List<Author> getAutores() { return autores; }
    public void setAutores(List<Author> autores) { this.autores = autores; }

    public Publisher getPublisher() { return publisher; }
    public void setPublisher(Publisher publisher) { this.publisher = publisher; }

    // compatibility method used by the existing BulkDataLoader (it calls b.set(publisher))
    public void set(Publisher publisher) { this.publisher = publisher; }

    // Common contract for subclasses to present display information
    public abstract String exibirInformacoes();
}
