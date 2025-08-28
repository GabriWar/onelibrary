package com.example.restservice.dto;

import java.util.List;

public class BookRequest {
    public String titulo;
    public String idioma;
    public Integer anoEdicao;
    public String categoriaCientifica;
    public List<Long> authorIds;
    public Long publisherId;
}
