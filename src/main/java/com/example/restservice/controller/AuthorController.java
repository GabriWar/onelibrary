package com.example.restservice.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.restservice.model.Author;
import com.example.restservice.repository.AuthorRepository;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorRepository repo;

    public AuthorController(AuthorRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Author> list() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> get(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Author> create(@RequestBody Author author) {
        Author saved = repo.save(author);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
