package com.example.restservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.restservice.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {}
