package com.jbiet.libraryBackend.service;

import com.jbiet.libraryBackend.exception.NotFoundException;
import com.jbiet.libraryBackend.model.Book;
import com.jbiet.libraryBackend.model.Borrower;
import com.jbiet.libraryBackend.repository.BookRepository;
import com.jbiet.libraryBackend.repository.BorrowerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LibraryService {
    private final BookRepository bookRepository;
    private final BorrowerRepository borrowerRepository;


    // Books
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new NotFoundException("Book not found"));
        bookRepository.delete(book);
    }

    public Book getBook(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new NotFoundException("Book not found"));
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book findByTitle(String title) {
        return bookRepository.findByTitleIgnoreCase(title).orElseThrow(() -> new NotFoundException("Book not found"));
    }

    // Borrowers
    public Borrower addBorrower(Borrower borrower) {
        return borrowerRepository.save(borrower);
    }

    public List<Borrower> getAllBorrowers() {
        return borrowerRepository.findAll();
    }

    public Borrower getBorrower(Long id) {
        return borrowerRepository.findById(id).orElseThrow(() -> new NotFoundException("Borrower not found"));
    }

    // Lend
    public Book lendBook(Long bookId, Long borrowerId) {
        Book book = getBook(bookId);
        if (!book.isAvailable()) {
            throw new IllegalStateException("Book is currently unavailable");
        }
        Borrower borrower = getBorrower(borrowerId);
        book.setAvailable(false);
        book.setBorrower(borrower);
        return bookRepository.save(book);
    }

    // Return
    public Book returnBook(Long bookId) {
        Book book = getBook(bookId);
        if (book.isAvailable()) {
            throw new IllegalStateException("Book was not lent out");
        }
        book.setAvailable(true);
        book.setBorrower(null);
        return bookRepository.save(book);
    }
}


