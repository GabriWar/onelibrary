package com.example.restservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.restservice.repository.BookRepository;
import com.example.restservice.model.Book;

@RestController
@RequestMapping("/api/loans")
public class LoanController {
    private final BookRepository bookRepo;

    public LoanController(BookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }

    @PostMapping("/borrow/{bookId}")
    public ResponseEntity<?> borrow(@PathVariable Long bookId, @RequestParam String userCpf) {
        Book b = bookRepo.findById(bookId).orElse(null);
        if (b == null) return ResponseEntity.notFound().build();
        if (!b.isDisponivel()) return ResponseEntity.status(409).body("Book already borrowed");
        b.emprestar(userCpf);
        bookRepo.save(b);
        return ResponseEntity.ok(b);
    }

    @PostMapping("/return/{bookId}")
    public ResponseEntity<?> ret(@PathVariable Long bookId) {
        Book b = bookRepo.findById(bookId).orElse(null);
        if (b == null) return ResponseEntity.notFound().build();
        if (b.isDisponivel()) return ResponseEntity.status(409).body("Book is not borrowed");
        b.devolver();
        bookRepo.save(b);
        return ResponseEntity.ok(b);
    }
}
