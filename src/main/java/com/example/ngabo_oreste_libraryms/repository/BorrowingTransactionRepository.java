package com.example.ngabo_oreste_libraryms.repository;

import com.example.ngabo_oreste_libraryms.model.Book;
import com.example.ngabo_oreste_libraryms.model.BorrowingTransaction;
import com.example.ngabo_oreste_libraryms.model.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BorrowingTransactionRepository extends JpaRepository<BorrowingTransaction, Long> {
    List<BorrowingTransaction> findByBook(Book book);

    Optional<BorrowingTransaction> findByBookAndStatus(Book book, TransactionStatus status);

    List<BorrowingTransaction> findByBorrowerName(String borrowerName);
}