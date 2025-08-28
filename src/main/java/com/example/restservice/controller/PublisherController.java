package com.example.restservice.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.restservice.model.Publisher;
import com.example.restservice.repository.PublisherRepository;

@RestController
@RequestMapping("/api/publishers")
public class PublisherController {
    private final PublisherRepository repo;

    public PublisherController(PublisherRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Publisher> list() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Publisher> get(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Publisher> create(@RequestBody Publisher publisher) {
        Publisher saved = repo.save(publisher);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
