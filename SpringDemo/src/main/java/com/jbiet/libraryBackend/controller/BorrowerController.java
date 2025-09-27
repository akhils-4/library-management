package com.jbiet.libraryBackend.controller;

import com.jbiet.libraryBackend.model.Borrower;
import com.jbiet.libraryBackend.service.LibraryService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrowers")
@RequiredArgsConstructor

public class BorrowerController {
    private final LibraryService service;

    @PostMapping
    public ResponseEntity<Borrower> addBorrower(@RequestBody Borrower borrower) {
        return ResponseEntity.ok(service.addBorrower(borrower));
    }

    @GetMapping
    public ResponseEntity<List<Borrower>> getAllBorrowers() {
        return ResponseEntity.ok(service.getAllBorrowers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Borrower> getBorrower(@PathVariable Long id) {
        return ResponseEntity.ok(service.getBorrower(id));
    }
}
