package com.example.restservice.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.restservice.model.Periodic;
import com.example.restservice.repository.PeriodicRepository;

@RestController
@RequestMapping("/api/periodics")
public class PeriodicController {
    private final PeriodicRepository repo;

    public PeriodicController(PeriodicRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Periodic> list() { return repo.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Periodic> get(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Periodic> create(@RequestBody Periodic p) {
        return ResponseEntity.ok(repo.save(p));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
