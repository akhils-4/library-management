package com.jbiet.libraryBackend.repository;

import com.jbiet.libraryBackend.model.Borrower;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowerRepository extends JpaRepository<Borrower, Long> {

}
