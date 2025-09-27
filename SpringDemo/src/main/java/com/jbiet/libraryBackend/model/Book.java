package com.jbiet.libraryBackend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Book {
    @Id
    @GeneratedValue
            (strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String author;
    private boolean isAvailable;

    @ManyToOne
    @JoinColumn(name = "borrower_id")
    private Borrower borrower;


}
