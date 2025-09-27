package com.jbiet.libraryBackend.controller;

import com.jbiet.libraryBackend.model.Book;
import com.jbiet.libraryBackend.service.LibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor

public class BookController {
    private final LibraryService service;

    @PatchMapping("/lend-book")
    public ResponseEntity<Book> lendBook(@RequestParam long bookId, @RequestParam long borrowerId){

        return ResponseEntity.ok(service.lendBook(bookId, borrowerId));
    }

    @PatchMapping("/return-book")
    public ResponseEntity<Book> returnBook(@RequestParam long bookId){

        return ResponseEntity.ok(service.returnBook(bookId));
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book created = service.addBook(book);
        return ResponseEntity.ok(created);
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<String> deleteBook(@PathVariable long id){
        service.deleteBook(id);
        return ResponseEntity.ok("Book is Deleted");
    }
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(service.getAllBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Long id) {
        return ResponseEntity.ok(service.getBook(id));
    }

    @GetMapping("/search")
    public ResponseEntity<Book> searchByTitle(@RequestParam String title) {
        return ResponseEntity.ok(service.findByTitle(title));
    }





}
