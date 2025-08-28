package com.example.restservice.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.restservice.model.Book;
import com.example.restservice.repository.BookRepository;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookRepository repo;

    public BookController(BookRepository repo) { this.repo = repo; }

    @GetMapping
    public List<Book> list() { return repo.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Book> get(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Book> create(@RequestBody Book b) { return ResponseEntity.ok(repo.save(b)); }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
